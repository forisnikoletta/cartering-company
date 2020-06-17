package edu.ubb.cartering.backend.services.implementations;

import edu.ubb.cartering.backend.dao.DAOException;
import edu.ubb.cartering.backend.dao.MenuOrdersDAO;
import edu.ubb.cartering.backend.dao.StateDAO;
import edu.ubb.cartering.backend.enums.ORDER_STATES;
import edu.ubb.cartering.backend.model.MenuOrder;
import edu.ubb.cartering.backend.model.State;
import edu.ubb.cartering.backend.services.OrderStateService;
import edu.ubb.cartering.backend.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class OrderServiceBean implements OrderStateService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceBean.class);

    @EJB
    private MenuOrdersDAO ordersDAO;

    @EJB
    private StateDAO stateDAO;

    @Override
    public boolean createState(MenuOrder order, ORDER_STATES state) throws ServiceException {
        State state1 = new State();
        state1.setDate(new Date());
        state1.setState(state);
        state1.setMenuOrder(order);
        try {
            stateDAO.create(state1);
            return true;
        } catch ( DAOException ex) {
            LOG.error("State create error!");
            throw new ServiceException("State create error!", ex);
        }
    }

    @Override
    public ORDER_STATES getLastState(int orderId) throws ServiceException {
        try {
        MenuOrder order = ordersDAO.findById(orderId);
            List<State> states = order.getStates();
            if(!states.isEmpty()) {
                return ORDER_STATES.getState(states.get(states.size() - 1).getState());
            } else {
                return null;
            }
        } catch(DAOException ex) {
            LOG.error("Find by orderId failed!");
            throw new ServiceException("Find by orderId failed!", ex);
        }
    }
}
