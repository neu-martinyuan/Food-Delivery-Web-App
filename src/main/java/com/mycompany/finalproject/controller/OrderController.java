/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.controller;

import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.pojo.Customer;
import com.mycompany.finalproject.pojo.Dish;
import com.mycompany.finalproject.pojo.Order;
import com.mycompany.finalproject.pojo.Restaurant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class OrderController {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @GetMapping("/order/add.htm")
    public ModelAndView addOrder(Order order, HttpServletRequest request) {
        double food_price = 0;
        String str_dishes = "";
        String[] dishes = request.getParameterValues("dishes");
        if (dishes.length == 0)
            return new ModelAndView("error", "errorMsg", "Did not choose any dishes");
        DBConnection db = new DBConnection();
        for (String tmp : dishes) {
            int num = Integer.parseInt(request.getParameter(tmp));
            Dish dish = db.getDish(Integer.parseInt(tmp));
            for (int i=0; i<num; i++) {
                str_dishes += tmp + ",";
                food_price += dish.getPrice();
            }
        }
        str_dishes = str_dishes.substring(0, str_dishes.length()-1);
        
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurantChosenByCustomer");
        
        order.setRestaurant(restaurant);
        
        order.setCustomer(customer);
        
        order.setDishes(str_dishes);
        order.setFood_price(food_price);
        
        
        order.setDelivery_fee(food_price * 0.1 + 5);
        
        db.saveOrder(order);
        restaurant.setBalance(food_price + restaurant.getBalance());
        db.updateRestaurant(restaurant);
        
        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setTo(restaurant.getEmail());
        simpleMessage.setSubject("Hello, You Have an Order");
        simpleMessage.setText("You got an order from " + customer.getUsername() + ". And the dishes are " + order.getDishes());
        
        mailSender.send(simpleMessage);
        
        return new ModelAndView("customer-success", "successMessage", "Order created successfully!");
    }
}
