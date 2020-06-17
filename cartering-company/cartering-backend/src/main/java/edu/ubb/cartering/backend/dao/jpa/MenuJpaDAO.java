package edu.ubb.cartering.backend.dao.jpa;


import edu.ubb.cartering.backend.dao.MenuDAO;
import edu.ubb.cartering.backend.model.Menu;

import javax.ejb.Stateless;

@Stateless
public class MenuJpaDAO extends JpaDAO<Menu> implements MenuDAO
{

	public MenuJpaDAO() {
		super(Menu.class);
	}

}
