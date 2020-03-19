package com.ebrain.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class Role implements Serializable{
 
	 private Integer id;
	 private String name; 
	 private String description;
	 private String status;
	 
	 
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	 public Integer getId() {
	  return id;
	 }
	 public void setId(Integer id) {
	  this.id = id;
	 }
	 
	 @Column(name="role_name")
	 public String getName() {
	  return name;
	 }
	 public void setName(String name) {
	  this.name = name;
	 }
	 
	 @Column(name="role_description")
	 public String getDescription() {
	  return description;
	 }
	 public void setDescription(String description) {
	  this.description = description;
	 }
	 @Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
 
}