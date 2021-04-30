/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.controller;

import com.mycompany.finalproject.dao.CustomerDAO;
import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.exception.CarException;
import org.springframework.stereotype.Controller;
import com.mycompany.finalproject.pojo.Customer;
import com.mycompany.finalproject.pojo.Dish;
import com.mycompany.finalproject.pojo.Order;
import com.mycompany.finalproject.pojo.Restaurant;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class CustomerController {
    
    
    @PostMapping("/customer/add.htm")
    public ModelAndView addCustomer(Customer customer, HttpServletRequest request) {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone_number = request.getParameter("phone_number");
        String password = request.getParameter("password");
        
        customer.setUsername(username);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAge(age);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPhone_number(phone_number);
        customer.setPassword(password);
        
        DBConnection db = new DBConnection();
        if (request.getSession().getAttribute("customer") == null)
            db.saveCustomer(customer);
        
        customer = db.getCustomer(username);
        //List<Order> ls = db.getAllOrder(customer.getId());
        List<Restaurant> restaurants = db.getAllRestaurant();
        
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);
        //session.setAttribute("dishes", ls);
        session.setAttribute("restaurants", restaurants);
        
        List<Order> ordersByCustomer = db.getAllOrderByUsername(username, 1);
        session.setAttribute("ordersByCustomer", ordersByCustomer);
        
        return new ModelAndView("customer-home-page" /*, "restaurant", restaurant*/);
    }
    
    @PostMapping("/customer/login.htm")
    public ModelAndView login(Customer customer, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        DBConnection db = new DBConnection();
        if (!db.customerLogin(username, password))
            return new ModelAndView("error", "errorMsg", "Incorrect password");
        
        customer = db.getCustomer(username);
        //List<Dish> ls = db.getAllDish(restaurant.getRestaurant_id());
        List<Restaurant> restaurants = db.getAllRestaurant();
        
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);
        //session.setAttribute("dishes", ls);
        session.setAttribute("restaurants", restaurants);
        List<Order> ordersByCustomer = db.getAllOrderByUsername(username, 1);
        session.setAttribute("ordersByCustomer", ordersByCustomer);
        
        return new ModelAndView("customer-home-page");
    }
    
    @GetMapping("/customer/chooserestaurant.htm")
    public ModelAndView chooseRestaurant(HttpServletRequest request) {
        int restaurantChosenId = Integer.parseInt(request.getParameter("res_id"));
        DBConnection db = new DBConnection();
        Restaurant restaurantChosenByCustomer = db.getRestaurant(restaurantChosenId);
        List<Dish> dishesFromRestaurant = db.getAllDish(restaurantChosenByCustomer.getRestaurant_id());
        HttpSession session = request.getSession();
        session.setAttribute("restaurantChosenId", restaurantChosenId);
        session.setAttribute("restaurantChosenByCustomer", restaurantChosenByCustomer);
        session.setAttribute("dishesFromRestaurant", dishesFromRestaurant);
        return new ModelAndView("customer-order-dish");
    }
    
    @PostMapping("/customer/edit.htm")
    public ModelAndView editCustomer(HttpServletRequest request) throws CarException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone_number = request.getParameter("phone_number");
        
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAge(age);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPhone_number(phone_number);
        
        CustomerDAO db = new CustomerDAO();
        
        db.update(customer);
        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);
        return new ModelAndView("customer-success", "successMessage", "Edit account successfully");
    }
}
