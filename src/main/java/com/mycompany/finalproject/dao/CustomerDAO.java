/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.dao;

import com.mycompany.finalproject.exception.CarException;
import com.mycompany.finalproject.pojo.Customer;
import org.hibernate.HibernateException;

/**
 *
 * @author Lenovo
 */
public class CustomerDAO extends DAO{
    public void update(Customer customer) throws CarException {
        try {
            begin();
            getSession().update(customer);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not update the customer", e);
        }
    }
}
