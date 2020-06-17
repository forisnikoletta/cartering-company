package edu.ubb.cartering.backend.services;

import edu.ubb.cartering.backend.model.Menu;
import edu.ubb.cartering.backend.model.MenuOrder;
import edu.ubb.cartering.backend.model.User;

import java.util.List;

public interface MenuOrderService {

    boolean orderMenu(User user, Menu menu) throws ServiceException;

    List<MenuOrder> getAllMenuOrders(User user) throws ServiceException;

    MenuOrder getOrder(long orderId) throws ServiceException;

    List<MenuOrder> getOrders(Menu menu) throws ServiceException;
}
