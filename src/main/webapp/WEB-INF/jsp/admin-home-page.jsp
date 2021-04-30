<%-- 
    Document   : admin-home-page
    Created on : 2021-4-6, 21:22:43
    Author     : Lenovo
--%>

<%@page import="com.mycompany.finalproject.pojo.Car"%>
<%@page import="com.mycompany.finalproject.dao.CarDAO"%>
<%@page import="com.mycompany.finalproject.dao.DBConnection"%>
<%@page import="com.mycompany.finalproject.pojo.Admin"%>
<%@page import="com.mycompany.finalproject.pojo.Order"%>
<%@page import="com.mycompany.finalproject.pojo.Driver"%>
<%@page import="com.mycompany.finalproject.pojo.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.finalproject.pojo.Restaurant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            List<Restaurant> restaurants = (List<Restaurant>) request.getSession().getAttribute("restaurants");
            List<Customer> customers = (List<Customer>) request.getSession().getAttribute("customers");
            List<Driver> drivers = (List<Driver>) request.getSession().getAttribute("drivers");
            List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
            List<Car> cars = (List<Car>) request.getSession().getAttribute("cars");
        %>
        
        Admin ID: <%=admin.getId() %>
        <br/>
        Username: <%=admin.getUsername()%>
        <br/>
        <br/>
        
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Restaurant ID</b></th> 
          <th><b>Restaurant username</b></th> 
          <th><b>Restaurant restaurant name</b></th>
          <th><b>Restaurant phone number</b></th>
          <th><b>Restaurant email</b></th>
          <th><b>Restaurant address</b></th>
          <th><b>Restaurant category</b></th>
          <th><b>Restaurant intro</b></th>
          <th><b>Restaurant balance</b></th>
         </tr> 
        <%
            for (Restaurant rest : restaurants) {
        %>
            <tr>
                <td><%=rest.getRestaurant_id()%></td>
                <td><%=rest.getUsername()%></td>
                <td><%=rest.getRestaurant_name()%></td>
                <td><%=rest.getPhone_number()%></td>
                <td><%=rest.getEmail() %></td>
                <td><%=rest.getAddress()%></td>
                <td><%=rest.getRestaurant_category()%></td>
                <td><%=rest.getIntro()%></td>
                <td><%=rest.getBalance()%></td>
        <%}%> 
        </table> 
        <br/>
        <br/>
        
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Customer ID</b></th> 
          <th><b>Customer username</b></th> 
          <th><b>Customer first name</b></th> 
          <th><b>Customer last name</b></th> 
          <th><b>Customer age</b></th>
          <th><b>Customer email</b></th>
          <th><b>Customer address</b></th>
          <th><b>Customer phone number</b></th>
         </tr> 
        <%
            for (Customer customer : customers) {
        %>
            <tr>
                <td><%=customer.getId()%></td>
                <td><%=customer.getUsername()%></td>
                <td><%=customer.getFirstName()%></td>
                <td><%=customer.getLastName()%></td>
                <td><%=customer.getAge()%></td>
                <td><%=customer.getEmail()%></td>
                <td><%=customer.getAddress()%></td>
                <td><%=customer.getPhone_number()%></td>
        <%}%> 
        </table> 
        <br/>
        <br/>
        
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Driver ID</b></th> 
          <th><b>Driver username</b></th> 
          <th><b>Driver first name</b></th> 
          <th><b>Driver last name</b></th> 
          <th><b>Driver phone number</b></th>
          <th><b>Driver balance</b></th>
         </tr> 
        <%
            for (Driver driver : drivers) {
        %>
            <tr>
                <td><%=driver.getId()%></td>
                <td><%=driver.getUsername()%></td>
                <td><%=driver.getFirstName()%></td>
                <td><%=driver.getLastName()%></td>
                <td><%=driver.getPhone_number()%></td>
                <td><%=driver.getBalance()%></td>
        <%}%> 
        </table> 
        <br/>
        <br/>
        
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Order ID</b></th> 
          <th><b>Restaurant username</b></th> 
          <th><b>Restaurant address</b></th>
          <th><b>Restaurant phone number</b></th>
          
          <th><b>Customer username</b></th> 
          <th><b>Customer address</b></th>
          <th><b>Customer phone number</b></th>
          
          <th><b>Dirver username</b></th> 
          <th><b>Driver phone number</b></th>
          
          <th><b>Car Make</b></th> 
          <th><b>Car Plate</b></th>
          <th><b>Car Color</b></th>
          
          <th><b>Dishes' ID</b></th> 
          <th><b>Food Price</b></th>
          <th><b>Dilivery Price</b></th>
         </tr> 
        <%
            DBConnection db = new DBConnection();
            CarDAO cardao = new CarDAO();
            for (Order order : orders) {
                Restaurant rest = db.getRestaurant(order.getRestaurant().getRestaurant_id());
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
                <td><%=rest.getUsername() %></td>
                <td><%=rest.getAddress() %></td>
                <td><%=rest.getPhone_number() %></td>
                
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
                <td><%=order.getDelivery_fee()%></td>
        <%}%> 
        </table> 
        
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Car ID</b></th> 
          <th><b>Make</b></th> 
          <th><b>Plate</b></th>
          <th><b>Color</b></th>
          <th><b>Driver</b></th>
         </tr> 
        <%
            for (Car car : cars) {
        %>
            <tr>
                <td><%=car.getId() %></td>
                <td><%=car.getMake() %></td>
                <td><%=car.getPlate() %></td>
                <td><%=car.getColor()%></td>
                <td><%=car.getDriver().getId() %></td>
        <%}%> 
        </table> 
    </body>
</html>
