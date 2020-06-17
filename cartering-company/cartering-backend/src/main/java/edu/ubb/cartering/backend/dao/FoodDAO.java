package edu.ubb.cartering.backend.dao;

import javax.ejb.Local;

import edu.ubb.cartering.backend.model.Food;

@Local
public interface FoodDAO extends DAO<Food> {

    Food findByName(String name);
}
