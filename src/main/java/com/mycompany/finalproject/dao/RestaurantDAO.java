/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.dao;

import com.mycompany.finalproject.exception.CarException;
import com.mycompany.finalproject.pojo.Restaurant;
import org.hibernate.HibernateException;

/**
 *
 * @author Lenovo
 */
public class RestaurantDAO extends DAO{
    public void update(Restaurant restaurant) throws CarException {
        try {
            begin();
            getSession().update(restaurant);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not update the restaurant", e);
        }
    }
}
