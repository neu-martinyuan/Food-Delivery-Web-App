/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.exception;

/**
 *
 * @author Lenovo
 */
public class CarException extends Exception{
    public CarException(String message) {
        super(message);
    }
    
    public CarException(String message, Throwable cause) {
        super(message, cause);
    }
}
