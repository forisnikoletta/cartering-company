package edu.ubb.cartering.backend.services.implementations;

import edu.ubb.cartering.backend.dao.DAOException;
import edu.ubb.cartering.backend.dao.MenuOrdersDAO;
import edu.ubb.cartering.backend.dao.StateDAO;
import edu.ubb.cartering.backend.model.Menu;
import edu.ubb.cartering.backend.model.MenuOrder;
import edu.ubb.cartering.backend.model.User;
import edu.ubb.cartering.backend.services.MenuOrderService;
import edu.ubb.cartering.backend.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class MenuOrderServiceBean implements MenuOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(MenuOrderServiceBean.class);

    @EJB
    private MenuOrdersDAO ordersDAO;

    @EJB
    private StateDAO stateDAO;

    @Override
    public boolean orderMenu(User user, Menu menu) throws ServiceException {
        try {
            MenuOrder menuOrder = new MenuOrder();
            menuOrder.setMenu(menu);
            menuOrder.setUser(user);
            menuOrder.setDate(new Date());
            ordersDAO.create(menuOrder);
            return true;
        } catch (DAOException ex) {
            LOG.error("Order exception!");
            throw new ServiceException("Order exception", ex);
        }
    }

    @Override
    public List<MenuOrder> getAllMenuOrders(User user) throws ServiceException {
        try {
           return ordersDAO.findAllByUserId(user.getId());
        } catch (DAOException ex) {
            LOG.error("Order exception!");
            throw new ServiceException("Order exception", ex);
        }
    }

    @Override
    public List<MenuOrder> getOrders(Menu menu) throws ServiceException {
        try {
            return ordersDAO.findAllByMenuId(menu.getId());
        } catch (DAOException ex) {
            LOG.error("Order exception!");
            throw new ServiceException("Order exception", ex);
        }
    }

    @Override
    public MenuOrder getOrder(long orderId) throws ServiceException {
        try {
            return ordersDAO.findById(orderId);
        } catch (DAOException ex) {
            LOG.error("Order exception!");
            throw new ServiceException("Order exception", ex);
        }
    }
}
