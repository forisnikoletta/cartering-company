package edu.ubb.cartering.backend.dao.jpa;


import edu.ubb.cartering.backend.dao.StateDAO;
import edu.ubb.cartering.backend.model.State;

import javax.ejb.Stateless;

@Stateless
public class StateJpaDAO extends JpaDAO<State> implements StateDAO
{

	public StateJpaDAO() {
		super(State.class);
	}

}
