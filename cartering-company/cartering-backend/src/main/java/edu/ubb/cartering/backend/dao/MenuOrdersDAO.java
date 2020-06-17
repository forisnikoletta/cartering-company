package edu.ubb.cartering.backend.dao;

import edu.ubb.cartering.backend.model.MenuOrder;

import javax.ejb.Local;
import java.util.List;


@Local
public interface MenuOrdersDAO extends DAO<MenuOrder> {
	List<MenuOrder> findAllByUserId(long userId);

	List<MenuOrder> findAllByMenuId(long menuId);
}
