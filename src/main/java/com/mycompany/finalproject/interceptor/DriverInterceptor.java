/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.interceptor;

import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.pojo.Driver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Lenovo
 */
public class DriverInterceptor extends HandlerInterceptorAdapter{

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("pre handle");
        Driver driver1 = (Driver) request.getSession().getAttribute("driver");
        DBConnection db = new DBConnection();
        Driver driver2 = db.getDriver(driver1.getId());
        if (driver1.getUsername().equals(driver2.getUsername()) 
                && driver1.getPassword().equals(driver2.getPassword())) 
            return true;
        System.out.println("Invalid to access to the page");
        return false;
    }
    
}
