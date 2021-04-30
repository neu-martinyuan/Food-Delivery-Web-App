/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.controller;

import com.mycompany.finalproject.view.PdfView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;

/**
 *
 * @author Lenovo
 */
@Controller
public class PdfController {
    
    @GetMapping("/menu.pdf")
    protected View handleRequest() {
        View view = new PdfView();
        return view;
    }
}
