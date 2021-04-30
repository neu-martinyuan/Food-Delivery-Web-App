/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.pojo;

/**
 *
 * @author Lenovo
 */
public class Order {
    private int id;
    private Restaurant restaurant;
    private Customer customer;
    private Driver driver;
    private Car car;
    private String dishes;
    private double food_price;
    private double delivery_fee;

    public Order() {
    }

    public Order(Restaurant restaurant, Customer customer, Driver driver, Car car, String dishes, double food_price, double delivery_fee) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.driver = driver;
        this.car = car;
        this.dishes = dishes;
        this.food_price = food_price;
        this.delivery_fee = delivery_fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public double getFood_price() {
        return food_price;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    
}
