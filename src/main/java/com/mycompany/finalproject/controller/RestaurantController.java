/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.controller;

import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.dao.RestaurantDAO;
import com.mycompany.finalproject.exception.CarException;
import com.mycompany.finalproject.pojo.Dish;
import com.mycompany.finalproject.pojo.Order;
import com.mycompany.finalproject.pojo.Restaurant;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class RestaurantController {
    
    @PostMapping("/restaurant/edit.htm")
    public ModelAndView editRestaurant(HttpServletRequest request) throws CarException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String category = request.getParameter("category");
        String intro = request.getParameter("intro");
        
        Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
        restaurant.setRestaurant_name(name);
        restaurant.setPhone_number(phone);
        restaurant.setEmail(email);
        restaurant.setAddress(address);
        restaurant.setRestaurant_category(category);
        restaurant.setIntro(intro);
        
        RestaurantDAO db = new RestaurantDAO();
        db.update(restaurant);
        HttpSession session = request.getSession();
        session.setAttribute("restaurant", restaurant);
        return new ModelAndView("restaurant-success", "successMessage", "Edit account successfully");

    }
    
    @PostMapping("/restaurant/add.htm")
    public ModelAndView addRestuarant(Restaurant restaurant, HttpServletRequest request) {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String category = request.getParameter("category");
        String intro = request.getParameter("intro");
        String password = request.getParameter("password");
        
        restaurant.setUsername(username);
        restaurant.setRestaurant_name(name);
        restaurant.setPhone_number(phone);
        restaurant.setEmail(email);
        restaurant.setAddress(address);
        restaurant.setRestaurant_category(category);
        restaurant.setIntro(intro);
        restaurant.setPassword(password);
        restaurant.setBalance(0);
        
        Restaurant session_restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
        
        DBConnection connection = new DBConnection();
        if (request.getSession().getAttribute("restaurant") == null)
            connection.saveRestaurant(restaurant);
        
        restaurant = connection.getRestaurant(username);
        List<Dish> ls = connection.getAllDish(restaurant.getRestaurant_id());
        
        HttpSession session = request.getSession();
        session.setAttribute("restaurant", restaurant);
        session.setAttribute("dishes", ls);
        
        List<Order> ordersByRestaurant = connection.getAllOrderByUsername(username, 0);
        session.setAttribute("ordersByRestaurant", ordersByRestaurant);
        
        return new ModelAndView("restaurant-home-page" /*, "restaurant", restaurant*/);
    }
    
    @PostMapping("/restaurant/login.htm")
    public ModelAndView login(Restaurant restaurant, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        DBConnection db = new DBConnection();
        if (!db.restaurantLogin(username, password))
            return new ModelAndView("error", "errorMsg", "Incorrect password");
        
        restaurant = db.getRestaurant(username);
        List<Dish> ls = db.getAllDish(restaurant.getRestaurant_id());
        
        HttpSession session = request.getSession();
        session.setAttribute("restaurant", restaurant);
        session.setAttribute("dishes", ls);
        
        List<Order> ordersByRestaurant = db.getAllOrderByUsername(username, 0);
        session.setAttribute("ordersByRestaurant", ordersByRestaurant);
        
        return new ModelAndView("restaurant-home-page");
    }
}
