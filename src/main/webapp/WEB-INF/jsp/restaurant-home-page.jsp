<%-- 
    Document   : restaurant-home-page
    Created on : 2021-4-2, 22:56:12
    Author     : Lenovo
--%>
<%@page import="com.mycompany.finalproject.pojo.Customer"%>
<%@page import="com.mycompany.finalproject.pojo.Car"%>
<%@page import="com.mycompany.finalproject.dao.CarDAO"%>
<%@page import="com.mycompany.finalproject.dao.DBConnection"%>
<%@page import="com.mycompany.finalproject.pojo.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.finalproject.pojo.Restaurant"%> 
<%@page import="com.mycompany.finalproject.pojo.Dish"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
        %>
        Restaurant ID: <%=res.getRestaurant_id() %>
        <br/>
        Username: <%=res.getUsername()%>
        <br/>
        Restaurant Name: <%=res.getRestaurant_name()%>
        <br/>
        Phone Number: <%=res.getPhone_number()%>
        <br/>
        Email: <%=res.getEmail()%>
        <br/>
        Address: <%=res.getAddress()%>
        <br/>
        Restaurant Category: <%=res.getRestaurant_category()%>
        <br/>
        Introduction: <%=res.getIntro()%>
        <br/>
        Balance(USD): <%=res.getBalance()%>
        <br/>
        
        <a href="/HibernateDemo/restaurant-edit.html">Edit Your Account</a>
        
        <br/>
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Dish ID</b></th> 
          <th><b>Dish Name</b></th> 
          <th><b>Dish Category</b></th>
          <th><b>Price</b></th>
          <th><b>Info</b></th>
         </tr> 
        <%
            List<Dish> ls = (List<Dish>) request.getSession().getAttribute("dishes");
            for (Dish dish : ls) {
        %>
            <tr>
                <td><%=dish.getDish_id() %></td>
                <td><%=dish.getDish_name() %></td>
                <td><%=dish.getDish_category() %></td>
                <td><%=dish.getPrice() %></td>
                <td><%=dish.getInfo() %></td>
            </tr>
        <%}%> 
        </table>  
        
        <br/>
        <a href="/HibernateDemo/dish-add.html">Add new dish</a>
        <a href="/HibernateDemo/dish-delete.html">Delete a dish</a>
        <a href="/HibernateDemo/dish-update.html">Update a dish</a>
        
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Order ID</b></th> 
          
          <th><b>Customer username</b></th> 
          <th><b>Customer address</b></th>
          <th><b>Customer phone number</b></th>
          
          <th><b>Driver username</b>
          <th><b>Driver phone number</b>
          
          
          <th><b>Car Make</b></th> 
          <th><b>Car Plate</b></th>
          <th><b>Car Color</b></th>
          
          <th><b>Dishes' ID</b></th> 
          <th><b>Food Price</b></th>
         </tr> 
        <%
            List<Order> ordersByRestaurant = (List<Order>) request.getSession().getAttribute("ordersByRestaurant");
            DBConnection db = new DBConnection();
            CarDAO cardao = new CarDAO();
            for (Order order : ordersByRestaurant) {
                
                Customer cust = db.getCustomer(order.getCustomer().getId());
                
                String driverUsername = null;
                String driverPhoneNumber = null;
                if (order.getDriver() != null) {
                    driverUsername = db.getDriver(order.getDriver().getId()).getUsername();    
                    driverPhoneNumber = db.getDriver(order.getDriver().getId()).getPhone_number(); 
                }
                
                
                String make = null;
                String plate = null;
                String color = null;
                if (order.getCar() != null) {
                    make = cardao.getCarById(order.getCar().getId()).getMake();
                    plate = cardao.getCarById(order.getCar().getId()).getPlate();
                    color = cardao.getCarById(order.getCar().getId()).getColor();
                }
                 if (make == null) {
        %>
        <tr>
        <%
                } else {
        %>
        <tr bgcolor="red">
        <%
                }
        %>
           
                <td><%=order.getId()%></td>
                
                <td><%=cust.getUsername() %></td>
                <td><%=cust.getAddress() %></td>
                <td><%=cust.getPhone_number() %></td>
                
                <td><%=driverUsername %></td>
                <td><%=driverPhoneNumber %></td>
                
                <td><%=make %></td>
                <td><%=plate %></td>
                <td><%=color %></td>
                
                <td><%=order.getDishes() %></td>
                <td><%=order.getFood_price() %></td>
                </tr>
        <%}%> 
        </table>  
    </body>
</html>
