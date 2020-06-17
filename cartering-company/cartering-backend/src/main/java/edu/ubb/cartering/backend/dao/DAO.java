package edu.ubb.cartering.backend.dao;

import java.util.List;

import edu.ubb.cartering.backend.model.BaseEntity;

public interface DAO<T extends BaseEntity> {
	List<T> findAll();
	T findById(long id);
	void create(T t);
}
