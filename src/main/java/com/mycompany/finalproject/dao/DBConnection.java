/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.dao;

import com.mycompany.finalproject.pojo.Admin;
import com.mycompany.finalproject.pojo.Customer;
import com.mycompany.finalproject.pojo.Dish;
import com.mycompany.finalproject.pojo.Driver;
import com.mycompany.finalproject.pojo.Order;
import com.mycompany.finalproject.pojo.Restaurant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Lenovo
 */
public class DBConnection {

    public DBConnection() {
        
    }
    
    public void saveDish(Dish dish) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(dish);
        tx.commit();
        session.close();
    }
    
    public void updateDish(Dish dish) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
                    //get movie object from database with movieId = 35
        
        Transaction tx = session.beginTransaction();
                    //update movie
        session.update(dish);

        tx.commit();
        session.close();
    }
    
    public void deleteDish(int id) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        Dish dish = session.get(Dish.class, id);
        Transaction tx = session.beginTransaction();
        if(dish != null){
                            //delete movie
            session.delete(dish);
        }

        tx.commit();
        session.close();
    }
    
    
    public List<Dish> getAllDish(int restaurant_id) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Dish> critera = builder.createQuery(Dish.class);
        critera.from(Dish.class);
        List<Dish> ls = session.createQuery(critera).getResultList();
        
        List<Dish> res = new ArrayList<>();
        for (Dish dish : ls) {
            if (dish.getRestaurant_id() == restaurant_id) {
                res.add(dish);
            }
        }
        
        //List<Dish> ls = session.createQuery("select * from menu where 'restaurant_id'=1", Dish.class);
            tx.commit();
            session.close();
        return res;
    }
    
    public Dish getDish(int dish_id) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        Dish dish = session.get(Dish.class, dish_id);
        
        tx.commit();
        session.close();
        return dish;
    }
    
    public void saveRestaurant(Restaurant restaurant) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       // SessionFactory sf = cfg.configure("hibernate.cfg.xml").addResource("Restaurant.hbm.xml").buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(restaurant);
        tx.commit();
        session.close();
    }
    
    public Restaurant getRestaurant(String username) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Restaurant> critera = builder.createQuery(Restaurant.class);
        critera.from(Restaurant.class);
        List<Restaurant> ls = session.createQuery(critera).getResultList();
        
        Restaurant res = new Restaurant();
        for (Restaurant restaurant : ls) {
            if (restaurant.getUsername().equals(username)) {
                res = restaurant;
                break;
            }
        }
        
        tx.commit();
        session.close();
        return res;
    }
    
    public Restaurant getRestaurant(int restaurant_id) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        Restaurant res = session.get(Restaurant.class, restaurant_id);
        
        tx.commit();
        session.close();
        return res;
    }
    
    public List<Restaurant> getAllRestaurant() {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Restaurant> critera = builder.createQuery(Restaurant.class);
        critera.from(Restaurant.class);
        List<Restaurant> ls = session.createQuery(critera).getResultList();
        
        tx.commit();
        session.close();
        return ls;
    }
    
    public void updateRestaurant(Restaurant restaurant) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
                    //get movie object from database with movieId = 35
        
        Transaction tx = session.beginTransaction();
                    //update movie
        session.update(restaurant);

        tx.commit();
        session.close();
    }
    
    public boolean restaurantLogin(String username, String password) {
        Restaurant restaurant = getRestaurant(username);
        if (!restaurant.getPassword().equals(password))
            return false;
        return true;
    }
    
    
    
    public void saveCustomer(Customer customer) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       // SessionFactory sf = cfg.configure("hibernate.cfg.xml").addResource("Restaurant.hbm.xml").buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(customer);
        tx.commit();
        session.close();
    }
    
    public Customer getCustomer(String username) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> critera = builder.createQuery(Customer.class);
        critera.from(Customer.class);
        List<Customer> ls = session.createQuery(critera).getResultList();
        
        Customer res = new Customer();
        for (Customer customer : ls) {
            if (customer.getUsername().equals(username)) {
                res = customer;
                break;
            }
        }
        
        tx.commit();
        session.close();
        return res;
    }
    
    public List<Customer> getAllCustomer() {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> critera = builder.createQuery(Customer.class);
        critera.from(Customer.class);
        List<Customer> ls = session.createQuery(critera).getResultList();
        
        tx.commit();
        session.close();
        return ls;
    }
    
    public Customer getCustomer(int customer_id) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        Customer res = session.get(Customer.class, customer_id);
        
        tx.commit();
        session.close();
        return res;
    }
    
    public boolean customerLogin(String username, String password) {
        Customer customer = getCustomer(username);
        if (!customer.getPassword().equals(password))
            return false;
        return true;
    }
    
    public void saveOrder(Order order) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(order);
        tx.commit();
        session.close();
    }
    
    public Order getOrder(int order_id) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        Order res = session.get(Order.class, order_id);
        
        tx.commit();
        session.close();
        return res;
    }
    
    public List<Order> getAllOrder() {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> critera = builder.createQuery(Order.class);
        critera.from(Order.class);
        List<Order> ls = session.createQuery(critera).getResultList();
        
        tx.commit();
        session.close();
        return ls;
    }
    
    public void updateOrder(Order order) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
                    //get movie object from database with movieId = 35
        
        Transaction tx = session.beginTransaction();
                    //update movie
        session.update(order);

        tx.commit();
        session.close();
    }
    
    public void deleteOrder(int id) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        Order order = session.get(Order.class, id);
        Transaction tx = session.beginTransaction();
        if(order != null){
                            //delete movie
            session.delete(order);
        }

        tx.commit();
        session.close();
    }
    
    /**
     * 
     * @param username  you can implement restaurant or customer or driver username
     * @param role  0 is restaurant, 1 is customer, 2 is driver
     * @return 
     */
    public List<Order> getAllOrderByUsername(String username, int role) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> critera = builder.createQuery(Order.class);
        critera.from(Order.class);
        List<Order> ls = session.createQuery(critera).getResultList();
        
        List<Order> res = new ArrayList<>();
        for (Order order : ls) {
            if (role == 0) {
                if (order.getRestaurant().getUsername().equals(username)) {
                    res.add(order);
                }  
            } else if (role == 1) {
                if (order.getCustomer().getUsername().equals(username)) {
                    res.add(order);
                }   
            } else if (role == 2) {
                if (order.getDriver()!= null && order.getDriver().getUsername().equals(username)) {
                    res.add(order);
                }  
            }
        }
        
        tx.commit();
        session.close();
        return res;
    }
    
    public void saveDriver(Driver driver) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       // SessionFactory sf = cfg.configure("hibernate.cfg.xml").addResource("Restaurant.hbm.xml").buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(driver);
        tx.commit();
        session.close();
    }
    
    public Driver getDriver(String username) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Driver> critera = builder.createQuery(Driver.class);
        critera.from(Driver.class);
        List<Driver> ls = session.createQuery(critera).getResultList();
        
        Driver res = new Driver();
        for (Driver driver : ls) {
            if (driver.getUsername().equals(username)) {
                res = driver;
                break;
            }
        }
        
        tx.commit();
        session.close();
        return res;
    }
    
    public List<Driver> getAllDriver() {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Driver> critera = builder.createQuery(Driver.class);
        critera.from(Driver.class);
        List<Driver> ls = session.createQuery(critera).getResultList();
        
        tx.commit();
        session.close();
        return ls;
    }
    
    public Driver getDriver(int driver_id) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        Driver res = session.get(Driver.class, driver_id);
        
        tx.commit();
        session.close();
        return res;
    }
    
    public boolean driverLogin(String username, String password) {
        Driver driver = getDriver(username);
        if (!driver.getPassword().equals(password))
            return false;
        return true;
    }
    
    
    public List<Order> getAllNoDriverOrder() {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> critera = builder.createQuery(Order.class);
        critera.from(Order.class);
        List<Order> ls = session.createQuery(critera).getResultList();
        
        List<Order> res = new ArrayList<>();
        for (Order order : ls) {
            if (order.getDriver()== null) {
                res.add(order);
            }
        }
        
        tx.commit();
        session.close();
        return res;
    }
    
    public void updateDriver(Driver driver) {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
                    //get movie object from database with movieId = 35
        
        Transaction tx = session.beginTransaction();
                    //update movie
        session.update(driver);

        tx.commit();
        session.close();
    }
    
    public boolean adminLogin(String username, String password) {
        Admin admin = getAdmin();
        if (!admin.getUsername().equals(username) || !admin.getPassword().equals(password))
            return false;
        return true;
    }
    
    public Admin getAdmin() {
        Configuration cfg = new Configuration();
        SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        
        Admin admin = session.get(Admin.class, 1);
        
        tx.commit();
        session.close();
        return admin;
    }
}
