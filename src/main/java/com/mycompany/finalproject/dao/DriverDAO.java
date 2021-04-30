/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.dao;

import com.mycompany.finalproject.exception.CarException;
import com.mycompany.finalproject.pojo.Driver;
import org.hibernate.HibernateException;

/**
 *
 * @author Lenovo
 */
public class DriverDAO extends DAO{
    public Driver create(String username, String firstName, String lastName, String password, String phone_number, int car_id, double balance) throws CarException {
        try {
            begin();
            Driver driver = new Driver(username, firstName, lastName, password, phone_number, car_id, balance);
            getSession().save(driver);
            commit();
            return driver;
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Exception while creating driver: " + e.getMessage());
        }
    }
    
    public void delete(Driver driver) throws CarException {
        try {
            begin();
            getSession().delete(driver);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not delete driver: " + e.getMessage());
        }
    }
    
    public void update(Driver driver) throws CarException {
        try {
            begin();
            getSession().update(driver);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not update the driver", e);
        }
    }
}
