/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.controller;

import com.mycompany.finalproject.dao.CarDAO;
import com.mycompany.finalproject.dao.DAO;
import com.mycompany.finalproject.dao.DBConnection;
import com.mycompany.finalproject.exception.CarException;
import com.mycompany.finalproject.pojo.Car;
import com.mycompany.finalproject.pojo.Driver;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class CarController {
    
    @GetMapping("/addcar.htm") 
    public ModelAndView displayAddCarForm(Model model, Car car) throws CarException {
        return new ModelAndView("add-car-form");
    }
    
    @PostMapping("/addcar.htm")
    public ModelAndView addCar(@ModelAttribute("car") Car newCar, BindingResult result, SessionStatus status, HttpServletRequest request) throws CarException {
        HttpSession session = request.getSession();
        Driver driver = (Driver) session.getAttribute("driver");
        
        
        String make = newCar.getMake();
        String plate = newCar.getPlate();
        String color = newCar.getColor();
        
        try {
            
            CarDAO cars = new CarDAO();
            Car car = cars.create(make, plate, color, driver);
            
            DAO.close();
            status.setComplete();
            return new ModelAndView("car-success", "successMessage", "Car added successfully");
        } catch (CarException e) {
            System.out.println(e.getMessage());
        }
        return null; 
    }
    
     @GetMapping("/listcar.htm")
    public ModelAndView listCars(HttpServletRequest request){
        HttpSession session = request.getSession();
        Driver driver = (Driver) session.getAttribute("driver");
        
        CarDAO cars = new CarDAO();
        List<Car> ls = cars.getAllCarsByDriver(driver.getId());
        for (Car car : ls) {
            System.out.println(car.getId());
        }
        return new ModelAndView("view-car", "cars", ls);
    }
    
}
