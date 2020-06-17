package edu.ubb.cartering.backend.services;

import edu.ubb.cartering.backend.model.Menu;

import java.util.List;
public interface MenuService {

    boolean createMenu(Menu menu
    ) throws ServiceException;

    Menu getMenu(long menuId) throws ServiceException;

    List<Menu> getAllMenu() throws ServiceException;
}
