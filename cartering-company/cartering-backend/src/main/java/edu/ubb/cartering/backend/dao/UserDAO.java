package edu.ubb.cartering.backend.dao;

import edu.ubb.cartering.backend.model.User;

import javax.ejb.Local;


@Local
public interface UserDAO extends DAO<User> {
	User findByEmail(String username);
}
