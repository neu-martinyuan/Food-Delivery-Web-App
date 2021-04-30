/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.dao;

import com.mycompany.finalproject.exception.CarException;
import com.mycompany.finalproject.pojo.Car;
import com.mycompany.finalproject.pojo.Driver;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Lenovo
 */
public class CarDAO extends DAO{
    
    public Car create(String make, String plate, String color, Driver driver) throws CarException {
        try {
            begin();
            Car car = new Car(make, plate, color, driver);
            getSession().save(car);
            commit();
            return car;
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Exception while creating car: " + e.getMessage());
        }
    }
    
    public void delete(Car car) throws CarException {
        try {
            begin();
            getSession().delete(car);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not delete car: " + e.getMessage());
        }
    }
    
    public List<Car> getAllCars() {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> critera = builder.createQuery(Car.class);
        critera.from(Car.class);
        List<Car> ls = session.createQuery(critera).getResultList();
        
        tx.commit();
        session.close();
        return ls;
    }
    
    public List<Car> getAllCarsByDriver(int driverId) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> critera = builder.createQuery(Car.class);
        critera.from(Car.class);
        List<Car> ls = session.createQuery(critera).getResultList();
        System.out.println(ls.size());
        List<Car> res = new ArrayList<>();
        for (Car car : ls) {
            if (car.getDriver().getId()== driverId) {
                res.add(car);
            }
        }
        System.out.println(res.size());
        //List<Dish> ls = session.createQuery("select * from menu where 'restaurant_id'=1", Dish.class);
        tx.commit();
        session.close();
        return res;
    }
    
    public Car getCarById(int car_id) throws CarException {
        try {
            begin();
            Car car = getSession().get(Car.class, car_id);
            commit();
            return car;
        } catch (HibernateException e) {
            rollback();
            throw new CarException("Could not delete car: " + e.getMessage());
        }
    }
}
