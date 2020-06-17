package edu.ubb.cartering.backend.services.implementations;

import edu.ubb.cartering.backend.dao.*;
import edu.ubb.cartering.backend.model.Food;
import edu.ubb.cartering.backend.model.Menu;
import edu.ubb.cartering.backend.services.MenuService;
import edu.ubb.cartering.backend.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Stateless
public class MenuServiceBean implements MenuService {

    private static final Logger LOG = LoggerFactory.getLogger(MenuServiceBean.class);

    @EJB
    private MenuDAO menuDAO;

    @EJB
    private FoodDAO foodDAO;

    @Override
    public boolean createMenu(Menu menu) throws ServiceException {
        if (menu.getName().isEmpty() || menu.getFoods().isEmpty()) return false;
        Set<Food> foods = menu.getFoods();
        Iterator<Food> iterator = foods.iterator();
        Set<Food> foodSaved = new HashSet<>();
        try {
            while (iterator.hasNext()) {
                Food fd = iterator.next();
                Food food2 = foodDAO.findByName(fd.getName());
                if (food2 == null) {
                    foodDAO.create(fd);
                    food2 = foodDAO.findByName(fd.getName());
                    if (food2 != null) {
                        foodSaved.add(food2);
                    }
                } else {
                    foodSaved.add(food2);
                }
            }
            menu.setFoods(foodSaved);
            menuDAO.create(menu);
            return true;
        } catch (DAOException ex) {
            LOG.info("Menu Service exception");
            throw new ServiceException("Menu Service exception", ex);
        }
    }

    @Override
    public Menu getMenu(long menuId) throws ServiceException {

        try {
            return menuDAO.findById(menuId);
        } catch (DAOException ex) {
            LOG.info("Menu Service exception");
            throw new ServiceException("Menu Service exception", ex);
        }
    }

    @Override
    public List<Menu> getAllMenu() throws ServiceException {
        try {
            return menuDAO.findAll();
        } catch (DAOException ex) {
            LOG.info("Menu Service exception");
            throw new ServiceException("Menu Service exception", ex);
        }
    }
}
