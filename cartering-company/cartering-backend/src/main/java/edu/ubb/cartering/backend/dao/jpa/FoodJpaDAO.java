package edu.ubb.cartering.backend.dao.jpa;


import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import edu.ubb.cartering.backend.dao.DAOException;
import edu.ubb.cartering.backend.dao.FoodDAO;
import edu.ubb.cartering.backend.model.Food;


import java.util.List;

@Stateless
public class FoodJpaDAO extends JpaDAO<Food> implements FoodDAO {

	public FoodJpaDAO() {
		super(Food.class);
	}

	@Override
	public Food findByName(String name) {
		TypedQuery<Food> query = entityManager.createNamedQuery(Food.QUERY_FIND_BY_NAME, Food.class);
		query.setParameter("name", name);

		try {
			List<Food> foods = query.getResultList();
			if (foods.isEmpty()) {
				return null;
			}
			return foods.get(0);
		} catch(PersistenceException ex) {
			throw new DAOException("Food JPA Connection error!", ex);
		}
	}
}
