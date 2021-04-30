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
public class Restaurant {
    private int restaurant_id;
    private String username;
    private String restaurant_name;
    private String phone_number;
    private String email;
    private String address;
    private String restaurant_category;
    private String intro;
    private String password;
    private double balance;

    public Restaurant() {
    }

    public Restaurant(String username, String restaurant_name, String phone_number, String email, String address, String restaurant_category, String intro, String password, double balance) {
        this.username = username;
        this.restaurant_name = restaurant_name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.restaurant_category = restaurant_category;
        this.intro = intro;
        this.password = password;
        this.balance = balance;
    }
    
    public Restaurant(int restaurant_id, String username, String restaurant_name, String phone_number, String address, String restaurant_category, String intro, String password, double balance) {
        this.restaurant_id = restaurant_id;
        this.username = username;
        this.restaurant_name = restaurant_name;
        this.phone_number = phone_number;
        this.address = address;
        this.restaurant_category = restaurant_category;
        this.intro = intro;
        this.password = password;
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurant_category() {
        return restaurant_category;
    }

    public void setRestaurant_category(String restaurant_category) {
        this.restaurant_category = restaurant_category;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    
}
