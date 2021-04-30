/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.controller;

import com.mycompany.finalproject.dao.CarDAO;
import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.pojo.Admin;
import com.mycompany.finalproject.pojo.Car;
import com.mycompany.finalproject.pojo.Customer;
import com.mycompany.finalproject.pojo.Driver;
import com.mycompany.finalproject.pojo.Order;
import com.mycompany.finalproject.pojo.Restaurant;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class AdminController {
    
    @PostMapping("/admin/login.htm")
    public ModelAndView login(Admin admin, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        DBConnection db = new DBConnection();
        CarDAO cardao = new CarDAO();
        if (!db.adminLogin(username, password))
            return new ModelAndView("error", "errorMsg", "Admin username or password wrong");
        
        admin = db.getAdmin();
        List<Restaurant> restaurants = db.getAllRestaurant();
        List<Customer> customers = db.getAllCustomer();
        List<Driver> drivers = db.getAllDriver();
        List<Order> orders = db.getAllOrder();
        List<Car> cars = cardao.getAllCars();
        
        HttpSession session = request.getSession();
        session.setAttribute("admin", admin);
        session.setAttribute("restaurants", restaurants);
        session.setAttribute("customers", customers);
        session.setAttribute("drivers", drivers);
        session.setAttribute("orders", orders);
        session.setAttribute("cars", cars);
        
        return new ModelAndView("admin-home-page");
        
    }
}
