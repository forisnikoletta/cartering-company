package edu.ubb.cartering.backend.services;

import edu.ubb.cartering.backend.model.User;

import java.util.List;

import javax.ejb.Local;



@Local
public interface AuthenticationService {
	void register(User newUser) throws ServiceException;

	User validatePassword(String email, String password) throws ServiceException;

	List<User> findAllUsers() throws ServiceException;

	User findByEmail(String username) throws ServiceException;
}
