/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.controller;

import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.pojo.Dish;
import com.mycompany.finalproject.pojo.Restaurant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class DishController {
    
    //@Autowired
    //Dish dish;
    
    
    @GetMapping("/dish/add.htm")
    public ModelAndView addDish(Dish dish, HttpServletRequest request) {
        //Dish dish = new Dish();
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        Double price = Double.parseDouble(request.getParameter("price"));
        String info = request.getParameter("info");
        
        Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
        
        dish.setDish_name(name);
        dish.setDish_category(category);
        dish.setPrice(price);
        dish.setInfo(info);
        dish.setRestaurant_id(restaurant.getRestaurant_id());

        DBConnection connection = new DBConnection();
        connection.saveDish(dish);
        
        return new ModelAndView("restaurant-success", "successMessage","Dish added successfully!");
    }
    
    @GetMapping("/dish/update.htm")
    public ModelAndView updateDish(Dish dish, HttpServletRequest request){
        //Dish dish = new Dish();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        Double price = Double.parseDouble(request.getParameter("price"));
        String info = request.getParameter("info");
        
        Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
        
        dish.setDish_id(id);
        dish.setDish_name(name);
        dish.setDish_category(category);
        dish.setPrice(price);
        dish.setInfo(info);
        dish.setRestaurant_id(restaurant.getRestaurant_id());

        DBConnection connection = new DBConnection();
        connection.updateDish(dish);
        return new ModelAndView("restaurant-success", "successMessage","Dish updated successfully!");
    }

    @GetMapping("/dish/delete.htm")
    public ModelAndView deleteDish(HttpServletRequest request) {
        int dish_id = Integer.parseInt(request.getParameter("dish_id"));
        DBConnection connection = new DBConnection();
        connection.deleteDish(dish_id);
        return new ModelAndView("restaurant-success", "successMessage","Dish deleted successfully!");
    }
    
    
    @GetMapping("/dish/getall.htm")
    public ModelAndView getAllDish() {
        DBConnection connection = new DBConnection();
        List<Dish> ls = new ArrayList<>();
        // insert restaurant_id to get all dishes as a menu
        ls = connection.getAllDish(1);
        return new ModelAndView("restaurant-success", "successMessage",ls.get(0).getInfo());
    }
}
