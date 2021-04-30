/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.interceptor;

import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.pojo.Restaurant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Lenovo
 */
public class RestaurantInterceptor extends HandlerInterceptorAdapter{

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("after concurrent handling started");
        super.afterConcurrentHandlingStarted(request, response, handler); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after completion");
        super.afterCompletion(request, response, handler, ex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post handle");
        super.postHandle(request, response, handler, modelAndView); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("pre handle");
        Restaurant res1 = (Restaurant) request.getSession().getAttribute("restaurant");
        DBConnection db = new DBConnection();
        Restaurant res2 = db.getRestaurant(res1.getRestaurant_id());
        if (res1.getUsername().equals(res2.getUsername()) 
                && res1.getPassword().equals(res2.getPassword())) 
            return true;
        System.out.println("Invalid to access to the page");
        return false;
        //return super.preHandle(request, response, handler); //To change body of generated methods, choose Tools | Templates.
    }
    
}
