/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.dao;

import com.mycompany.finalproject.exception.CarException;
import com.mycompany.finalproject.pojo.Dish;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

/**
 *
 * @author Lenovo
 */
public class DishDAO extends DAO{
    public Dish create(String dish_name, String dish_category, Double price, String info, int restaurant_id) throws CarException {
        try {
            begin();
            Dish dish = new Dish(dish_name, dish_category, price, info, restaurant_id);
            getSession().save(dish);
            commit();
            return dish;
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Exception while creating dish: " + e.getMessage());
        }
    }
    
    public void delete(Dish dish) throws CarException {
        try {
            begin();
            getSession().delete(dish);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not delete dish: " + e.getMessage());
        }
    }
    
    public void update(Dish dish) throws CarException {
        try {
            begin();
            getSession().update(dish);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not update the dish", e);
        }
    }
    
    public List<Dish> getByRestaurant(int restaurant_id) throws CarException {
        try {
            begin();
            Query q = getSession().createQuery("from Dish where restaurant_id = :restaurant_id");
            q.setInteger("restaurant_id", restaurant_id);
            List<Dish> dishes = (List<Dish>) q.getResultList();
            commit();
            return dishes;
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not obtain the dishes with restaurant id " + e.getMessage());
        }
    }
}
