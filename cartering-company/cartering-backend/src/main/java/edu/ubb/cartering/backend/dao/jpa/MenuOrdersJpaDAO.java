package edu.ubb.cartering.backend.dao.jpa;

import edu.ubb.cartering.backend.dao.DAOException;
import edu.ubb.cartering.backend.dao.MenuOrdersDAO;
import edu.ubb.cartering.backend.model.MenuOrder;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
public class MenuOrdersJpaDAO extends JpaDAO<MenuOrder> implements MenuOrdersDAO {

	public MenuOrdersJpaDAO() {
		super(MenuOrder.class);
	}

	@Override
	public List<MenuOrder> findAllByUserId(long userId) {
		TypedQuery<MenuOrder> query = entityManager.createNamedQuery(MenuOrder.QUERY_FIND_BY_USER, MenuOrder.class);
		query.setParameter("userId", userId);
		
		try {
			return query.getResultList();
		} catch(PersistenceException ex) {
			throw new DAOException("MenuOrder connection error!", ex);
		}
	}

	@Override
	public List<MenuOrder> findAllByMenuId(long menuId) {
		TypedQuery<MenuOrder> query = entityManager.createNamedQuery(MenuOrder.QUERY_FIND_BY_MENU, MenuOrder.class);
		query.setParameter("menuId", menuId);

		try {
			return query.getResultList();
		} catch(PersistenceException ex) {
			throw new DAOException("MenuOrder connection error!", ex);
		}
	}

}
