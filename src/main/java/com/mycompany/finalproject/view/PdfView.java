/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.view;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.finalproject.pojo.Dish;
import com.mycompany.finalproject.pojo.Restaurant;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author Lenovo
 */
public class PdfView extends AbstractPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Restaurant restaurantChosenByCustomer = (Restaurant) request.getSession().getAttribute("restaurantChosenByCustomer");
        List<Dish> dishesFromRestaurant = (List<Dish>) request.getSession().getAttribute("dishesFromRestaurant");
            
        Paragraph p = new Paragraph("Menu", FontFactory.getFont(FontFactory.TIMES_ROMAN, 40, Font.BOLD, new Color(255, 0, 0)));
        p.setAlignment(1);
        doc.add(p);
        Table table1 = new Table(3);
        Chunk name = new Chunk("Restaurant Name: " + restaurantChosenByCustomer.getRestaurant_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
        Chunk address = new Chunk("Address: " + restaurantChosenByCustomer.getAddress(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
        Chunk phone_number = new Chunk("Phone Number: " + restaurantChosenByCustomer.getPhone_number(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
        
        
        Cell cellname = new Cell(name);
        Cell celladdress = new Cell(address);
        Cell cellphone_number = new Cell(phone_number);
        
        table1.addCell(cellname);
        table1.addCell(celladdress);
        table1.addCell(cellphone_number);
        
        /*
        //Table table2 = new Table(dishesFromRestaurant.size());
        Table table2 = new Table(2);
        for (Dish dish : dishesFromRestaurant) {
            table2.addCell(new Cell(new Chunk(dish.getDish_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
            table2.addCell(new Cell(new Chunk("" + dish.getPrice(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
        }*/
        
        Table appetizer = new Table(2);
        Table salad = new Table(2);
        Table entree = new Table(2);
        Table seafood = new Table(2);
        Table dessert = new Table(2);
        Table drink = new Table(2);
        
        for (Dish dish : dishesFromRestaurant) {
            System.out.println(dish.getDish_category());
            if (dish.getDish_category().equals("Appetizer")) {
                appetizer.addCell(new Cell(new Chunk(dish.getDish_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
                appetizer.addCell(new Cell(new Chunk("" + dish.getPrice(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
            } else if (dish.getDish_category().equals("Soup&Salad")) {
                salad.addCell(new Cell(new Chunk(dish.getDish_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
                salad.addCell(new Cell(new Chunk("" + dish.getPrice(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
            } else if (dish.getDish_category().equals("Entree")) {
                entree.addCell(new Cell(new Chunk(dish.getDish_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
                entree.addCell(new Cell(new Chunk("" + dish.getPrice(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
            } else if (dish.getDish_category().equals("Seafood")) {
                seafood.addCell(new Cell(new Chunk(dish.getDish_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
                seafood.addCell(new Cell(new Chunk("" + dish.getPrice(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
            } else if (dish.getDish_category().equals("Dessert")) {
                dessert.addCell(new Cell(new Chunk(dish.getDish_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
                dessert.addCell(new Cell(new Chunk("" + dish.getPrice(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
            } else {
                drink.addCell(new Cell(new Chunk(dish.getDish_name(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
                drink.addCell(new Cell(new Chunk("" + dish.getPrice(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10))));
            }
        }
        
        table1.setPadding(3);
        table1.setAlignment(Element.ALIGN_LEFT);
        table1.setBorderWidth(0);
        //cellname.setBorderWidth(0);
        //celladdress.setBorderWidth(0);
        table1.setWidth(100);
        
        /*
        table2.setPadding(2);
        table2.setAlignment(Element.ALIGN_LEFT);
        table2.setBorderWidth(0);
        //cellname.setBorderWidth(0);
        //celladdress.setBorderWidth(0);
        table2.setWidth(100);
        */
        
        doc.add(table1);
        //doc.add(table2);
    
        Paragraph p2 = new Paragraph("Appetizer", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new Color(0, 255, 0)));
        p2.setAlignment(1);
        doc.add(p2);
        appetizer.setPadding(2);
        appetizer.setAlignment(Element.ALIGN_LEFT);
        appetizer.setBorderWidth(0);
        appetizer.setWidth(100);
        doc.add(appetizer);

        Paragraph p3 = new Paragraph("Soup&Salad", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new Color(0, 255, 0)));
        p3.setAlignment(1);
        doc.add(p3);
        salad.setPadding(2);
        salad.setAlignment(Element.ALIGN_LEFT);
        salad.setBorderWidth(0);
        salad.setWidth(100);
        doc.add(salad);

        Paragraph p4 = new Paragraph("Entree", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new Color(0, 255, 0)));
        p4.setAlignment(1);
        doc.add(p4);
        entree.setPadding(2);
        entree.setAlignment(Element.ALIGN_LEFT);
        entree.setBorderWidth(0);
        entree.setWidth(100);
        doc.add(entree);

        Paragraph p5 = new Paragraph("Seafood", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new Color(0, 255, 0)));
        p5.setAlignment(1);
        doc.add(p5);
        seafood.setPadding(2);
        seafood.setAlignment(Element.ALIGN_LEFT);
        seafood.setBorderWidth(0);
        seafood.setWidth(100);
        doc.add(seafood);

        Paragraph p6 = new Paragraph("Dessert", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new Color(0, 255, 0)));
        p6.setAlignment(1);
        doc.add(p6);
        dessert.setPadding(2);
        dessert.setAlignment(Element.ALIGN_LEFT);
        dessert.setBorderWidth(0);
        dessert.setWidth(100);
        doc.add(dessert);

        Paragraph p7 = new Paragraph("Drink", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new Color(0, 255, 0)));
        p7.setAlignment(1);
        doc.add(p7);
        drink.setPadding(2);
        drink.setAlignment(Element.ALIGN_LEFT);
        drink.setBorderWidth(0);
        drink.setWidth(100);
        doc.add(drink);
        
    }
    
}
