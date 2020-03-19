package com.ebrain.dao.services;

import java.io.Serializable;
import java.util.Collection;

public interface RootDAO <K extends Serializable, T extends Serializable> {
	T get(K id);
    void saveOrUpdate(T entity);
    void saveAll(Collection<T> list);
    boolean updateAll(Collection<T> list);
    Collection<T> getAll();
    void saveOrUpdateAll(Collection<T> list);
    void deleteAll(Collection<T> List);
    void delete(T entity);
}
