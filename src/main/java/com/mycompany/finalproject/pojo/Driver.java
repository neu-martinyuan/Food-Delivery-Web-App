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
public class Driver {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phone_number;
    private int car_id;
    private double balance;

    public Driver() {
    }

    public Driver(String username, String firstName, String lastName, String password, String phone_number, int car_id, double balance) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone_number = phone_number;
        this.car_id = car_id;
        this.balance = balance;
    }

    public Driver(int id, String username, String firstName, String lastName, String password, String phone_number, int car_id, double balance) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone_number = phone_number;
        this.car_id = car_id;
        this.balance = balance;
    }

    public Driver(int id, String username, String firstName, String lastName, String password, String phone_number, double balance) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone_number = phone_number;
        this.balance = balance;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
