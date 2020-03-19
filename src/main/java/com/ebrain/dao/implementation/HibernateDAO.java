package com.ebrain.dao.implementation;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
;

public abstract class HibernateDAO<K extends Serializable, T extends Serializable> extends HibernateDaoSupport implements InitializingBean {
	private Logger logger = LoggerFactory.getLogger(HibernateDAO.class);
	public static final int MAX_RETRY = 5;
	
    protected Class<T> domainClass = getDomainClass();
    
	public Object retryRun(RetriableTask task) {
		Exception exception = null;
		for (int i = 0; i < MAX_RETRY; i++) {
			try {
				return task.action();
			} catch (Exception e) {
				exception = e;
				boolean deadlock = false;
				for (StackTraceElement element: e.getStackTrace()) {
					if (element.toString().toLowerCase().contains("deadlock")) {
						deadlock = true;
					}
				}
				if (e.getMessage() != null && deadlock) {
					try {
						Thread.sleep(8000);
					} catch (Exception ex) {}
					logger.warn("Deadlock detected. Retrying " + i + "/" + MAX_RETRY);
				} else {
					throw new RuntimeException(e);
				}
			}
		}
		
		throw new RuntimeException(exception);
	}


    public T get(final K id) {
    	return (T)retryRun(new RetriableTask() {

			public Object action() throws Exception {
		        return (T)getCachedHibernateTemplate().get(domainClass, id);
			}
    	});
    }

    public Collection<T> getAll() {
    	return (Collection<T>)retryRun(new RetriableTask() {

			public Object action() throws Exception {
	            return getCachedHibernateTemplate().loadAll(domainClass);
			}
    	});
    }

    public K save(final T entity) {
    	return (K)retryRun(new RetriableTask() {

			public Object action() throws Exception {
	            return (K)(getCachedHibernateTemplate().save(entity));
			}
    	});
    }

    public void saveOrUpdate(final T entity) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
		        getCachedHibernateTemplate().saveOrUpdate(entity);
		        getCachedHibernateTemplate().flush();
		        return null;
			}
    	});
    }

    public void update(final T entity) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
	            getCachedHibernateTemplate().update(entity);
	            getCachedHibernateTemplate().flush();
		        return null;
			}
    	});
    }

    public void delete(final T entity) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
	            getCachedHibernateTemplate().delete(entity);
		        return null;
			}
    	});
    }
    
    public void saveOrUpdateAll(final Collection<T> list) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
		    	getCachedHibernateTemplate().saveOrUpdateAll(list);
		    	getCachedHibernateTemplate().flush();
		        return null;
			}
    	});
    }

    public void deleteById(final K id) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
		        T obj = get(id);
		        getCachedHibernateTemplate().delete(obj);
		        getCachedHibernateTemplate().flush();
		        return null;
			}
    	});
    }
    
    protected HibernateTemplate getCachedHibernateTemplate() {
    	HibernateTemplate template = getHibernateTemplate();
    	template.setCacheQueries(true);
    	return template;
    }

    protected Class getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();

            // get T, so the 2nd class
            domainClass = (Class) thisType.getActualTypeArguments()[1];
        }
        return domainClass;
    }

    public List find(final String query) {
    	return (List)retryRun(new RetriableTask() {
			public Object action() throws Exception {
		    	return getCachedHibernateTemplate().find(query);       
			}
    	});
    }

    public List find(final String query, final Object[] params) {
    	return (List)retryRun(new RetriableTask() {
			public Object action() throws Exception {
		    	return getCachedHibernateTemplate().find(query, params);
			}
    	});
    }

    public T findSingle(final String query, final Object[] params) {
    	return (T)retryRun(new RetriableTask() {
			public Object action() throws Exception {
		    	List<T> list = getCachedHibernateTemplate().find(query, params);
		    	if (list.isEmpty()) {
		    		return null;
		    	}
		    	
		    	return list.get(0);
			}
    	});
    }

    public void bulkUpdate(final String query, final Object[] params) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
		    	getCachedHibernateTemplate().bulkUpdate(query, params);
		    	getCachedHibernateTemplate().flush();
		    	return null;
			}
    	});
    }

    public void bulkUpdate(final String query) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
		    	getCachedHibernateTemplate().bulkUpdate(query);
		    	getCachedHibernateTemplate().flush();
		    	return null;
			}
    	});
    }

    public void saveAll(final Collection<T> entities) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
		    	_saveAll(entities);
		    	return null;
			}
    	});
    }

    private void _saveAll(final Collection<T> entities) {
    	getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				try {
					// consider packets of 1000 max
					int counter = 0;
					session.beginTransaction();
					for (T entity: entities) {
						session.save(entity);
					}
					session.getTransaction().commit();
					session.flush();
					session.clear();
				} catch (Exception e) {
					session.getTransaction().rollback();
					e.printStackTrace();
				}
				return null;
			}
    		
    	});
    }

    public boolean updateAll(final Collection<T> entities) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
				return _updateAll(entities);
		    	
			}
    	});    	
    	return true;
    }

    private boolean _updateAll(final Collection<T> entities) {
    	getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				try {
					session.beginTransaction();
					for (T entity: entities) {
						session.update(entity);
					}
					session.getTransaction().commit();
					session.flush();
					session.clear();
				} catch (Exception e) {
					session.getTransaction().rollback();
					e.printStackTrace();
					return false;
				}
				return true;
			}
    		
    	});
		return true;
    }

    public void deleteAll(final Collection<T> entities) {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
				_deleteAll(entities);
		    	return null;
			}
    	});    	
    }

    private void _deleteAll(final Collection<T> entities) {
    	getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				try {
					session.beginTransaction();
					for (T entity: entities) {
						session.delete(entity);
					}
					session.getTransaction().commit();
					session.flush();
					session.clear();
				} catch (Exception e) {
					session.getTransaction().rollback();
					e.printStackTrace();
				}
				return null;
			}
    		
    	});
    }

    public void flush() {
    	retryRun(new RetriableTask() {
			public Object action() throws Exception {
		    	getCachedHibernateTemplate().flush();
		    	return null;
			}
    	});    	
    }
    
    public interface RetriableTask {
    	public Object action() throws Exception;
    }

	
}