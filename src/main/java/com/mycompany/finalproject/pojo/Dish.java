/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.pojo;

import org.springframework.stereotype.Component;

/**
 *
 * @author Lenovo
 */
@Component
public class Dish {
    private int dish_id;
    private String dish_name;
    private String dish_category;
    private Double price;
    private String info;
    private int restaurant_id;

    public Dish() {
    }

    public Dish(String dish_name, String dish_category, Double price, String info, int restaurant_id) {
        this.dish_name = dish_name;
        this.dish_category = dish_category;
        this.price = price;
        this.info = info;
        this.restaurant_id = restaurant_id;
    }

    public Dish(int dish_id, String dish_name, String dish_category, Double price, String info, int restaurant_id) {
        this.dish_id = dish_id;
        this.dish_name = dish_name;
        this.dish_category = dish_category;
        this.price = price;
        this.info = info;
        this.restaurant_id = restaurant_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_category() {
        return dish_category;
    }

    public void setDish_category(String dish_category) {
        this.dish_category = dish_category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    
}
