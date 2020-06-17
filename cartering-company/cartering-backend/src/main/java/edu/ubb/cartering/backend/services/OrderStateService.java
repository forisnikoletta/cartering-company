package edu.ubb.cartering.backend.services;

import edu.ubb.cartering.backend.enums.ORDER_STATES;
import edu.ubb.cartering.backend.model.MenuOrder;

public interface OrderStateService {

    boolean createState(MenuOrder order, ORDER_STATES state) throws ServiceException;

    ORDER_STATES getLastState(int orderId) throws ServiceException;
}
