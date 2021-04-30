/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.controller;

import com.mycompany.finalproject.dao.CarDAO;
import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.dao.DriverDAO;
import com.mycompany.finalproject.exception.CarException;
import com.mycompany.finalproject.pojo.Car;
import com.mycompany.finalproject.pojo.Customer;
import com.mycompany.finalproject.pojo.Driver;
import com.mycompany.finalproject.pojo.Order;
import com.mycompany.finalproject.pojo.Restaurant;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class DriverController {
    
     @Autowired
    private JavaMailSender mailSender;
    
    @PostMapping("/driver/edit.htm")
    public ModelAndView editDriver(HttpServletRequest request) throws CarException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone_number = request.getParameter("phone_number");
        
        Driver driver = (Driver) request.getSession().getAttribute("driver");
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setPhone_number(phone_number);
        
        DriverDAO db = new DriverDAO();
        db.update(driver);
        HttpSession session = request.getSession();
        session.setAttribute("driver", driver);
        return new ModelAndView("driver-success", "successMessage", "Edit account successfully");

    }
    
    
    @PostMapping("/driver/add.htm")
    public ModelAndView addDriver(Driver driver, HttpServletRequest request) {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone_number = request.getParameter("phone_number");
        String password = request.getParameter("password");
        
        driver.setUsername(username);
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setBalance(0);
        driver.setPhone_number(phone_number);
        driver.setPassword(password);
        driver.setCar_id(-1);
        
        DBConnection db = new DBConnection();
        if (request.getSession().getAttribute("driver") == null)
            db.saveDriver(driver);
        
        driver = db.getDriver(username);
        List<Order> allNoDriverOrders = db.getAllNoDriverOrder();
        //List<Restaurant> restaurants = db.getAllRestaurant();
        
        HttpSession session = request.getSession();
        session.setAttribute("driver", driver);
        //session.setAttribute("dishes", ls);
        session.setAttribute("allNoDriverOrders", allNoDriverOrders);
        
        List<Order> ordersByDriver = db.getAllOrderByUsername(username, 2);
        session.setAttribute("ordersByDriver", ordersByDriver);
        
        return new ModelAndView("driver-home-page" /*, "restaurant", restaurant*/);
    }
    
    @PostMapping("/driver/login.htm")
    public ModelAndView login(Driver driver, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        DBConnection db = new DBConnection();
        if (!db.driverLogin(username, password))
            return new ModelAndView("error", "errorMsg", "Incorrect password");
        
        driver = db.getDriver(username);
        List<Order> allNoDriverOrders = db.getAllNoDriverOrder();
        //List<Restaurant> restaurants = db.getAllRestaurant();
        
        HttpSession session = request.getSession();
        session.setAttribute("driver", driver);
        //session.setAttribute("dishes", ls);
        session.setAttribute("allNoDriverOrders", allNoDriverOrders);
        
        List<Order> ordersByDriver = db.getAllOrderByUsername(username, 2);
        session.setAttribute("ordersByDriver", ordersByDriver);
        
        return new ModelAndView("driver-home-page" /*, "restaurant", restaurant*/);
    }
    
    @GetMapping("/driver/chooseorder.htm")
    public ModelAndView chooseOrder(HttpServletRequest request) throws CarException {
        int driverChosenId = Integer.parseInt(request.getParameter("order_id"));
        DBConnection db = new DBConnection();
        
        HttpSession session = request.getSession();
        Driver driver = (Driver) session.getAttribute("driver");
        
        if (driver.getCar_id() == -1) {
            return new ModelAndView("error", "errorMsg", "Did not select your car.");
        }
        
        Order order = db.getOrder(driverChosenId);
        
        order.setDriver(driver);
        
        CarDAO cars = new CarDAO();
        Car car = cars.getCarById(driver.getCar_id());
        order.setCar(car);
        // update order with driver's information
        db.updateOrder(order);
        
        driver.setBalance(driver.getBalance() + order.getDelivery_fee());
        db.updateDriver(driver);
        session.setAttribute("driver", driver);
        
        SimpleMailMessage simpleMessage1 = new SimpleMailMessage();
        Restaurant restaurant = db.getRestaurant(order.getRestaurant().getRestaurant_id());
        simpleMessage1.setTo(restaurant.getEmail());
        simpleMessage1.setSubject("Hello, Order ID " + driverChosenId + " has been picked by " + driver.getUsername());
        simpleMessage1.setText("The Order has been picked by "  + driver.getUsername() 
                                + " with a " + car.getColor() + " " + car.getMake() 
                                + " car. The plate is " + car.getPlate());
        
        SimpleMailMessage simpleMessage2 = new SimpleMailMessage();
        Customer customer = db.getCustomer(order.getCustomer().getId());
        simpleMessage2.setTo(customer.getEmail());
        simpleMessage2.setSubject("Hello, Order ID " + driverChosenId + " will deliver to you by " + driver.getUsername());
        simpleMessage2.setText("The Order will deliver to you by "  + driver.getUsername() 
                                + " with a " + car.getColor() + " " + car.getMake() 
                                + " car. The plate is " + car.getPlate());
        
        mailSender.send(simpleMessage1);
        mailSender.send(simpleMessage2);
        
        
        
        return new ModelAndView("driver-success", "successMessage", "Order Accepted Successfully");
    }
    
    @PostMapping("/driver/choosecar.htm")
    public ModelAndView chooseCar(HttpServletRequest request) {
        int chosenCarId = Integer.parseInt(request.getParameter("chosencar"));
        DBConnection db = new DBConnection();
        
        HttpSession session = request.getSession();
        Driver driver = (Driver) session.getAttribute("driver");
        
        driver.setCar_id(chosenCarId);
        db.updateDriver(driver);
        session.setAttribute("driver", driver);
        return new ModelAndView("driver-success", "successMessage", "Car Selected Successfully");
    }

}
