<%-- 
    Document   : driver-home-page
    Created on : 2021-4-6, 16:09:12
    Author     : Lenovo
--%>

<%@page import="com.mycompany.finalproject.pojo.Customer"%>
<%@page import="com.mycompany.finalproject.pojo.Restaurant"%>
<%@page import="com.mycompany.finalproject.dao.DBConnection"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.finalproject.pojo.Order"%>
<%@page import="com.mycompany.finalproject.pojo.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            DBConnection db = new DBConnection();
            List<Order> allNoDriverOrders = (List<Order>) request.getSession().getAttribute("allNoDriverOrders");
        %>
        <%
            Driver driver = (Driver) request.getSession().getAttribute("driver");
        %>
        Driver ID: <%=driver.getId() %>
        <br/>
        Username: <%=driver.getUsername()%>
        <br/>
        First Name: <%=driver.getFirstName()%>
        <br/>
        Last Name: <%=driver.getLastName()%>
        <br/>
        Phone Number: <%=driver.getPhone_number()%>
        <br/>
        Car ID: <%=driver.getCar_id()%>
        <br/>
        Balance: <%=driver.getBalance()%>
        <br/>
        
        <a href="/HibernateDemo/driver-edit.html">Edit Your Account</a>
        
        <br/>
        
        <form action="/HibernateDemo/driver/chooseorder.htm" method="GET">
            Choose Order:
            <select name="order_id">
                <%
                    for (int i=0; i < allNoDriverOrders.size(); i++) {
                        Restaurant rest = db.getRestaurant(allNoDriverOrders.get(i).getRestaurant().getRestaurant_id());
                        Customer cust = db.getCustomer(allNoDriverOrders.get(i).getCustomer().getId());
                    %>
                    <option value="<%=allNoDriverOrders.get(i).getId()%>">Restaurant Address: <%=rest.getAddress() %> Customer Address: <%=cust.getAddress()%> Fee: <%=allNoDriverOrders.get(i).getDelivery_fee()%></option>
                <%
                    }
                %>
            </select>
            <br/>
            <input type="submit" value="Submit">
        </form>
            
            </br>    
        <a href="/HibernateDemo/addcar.htm">Add a car</a>
        <a href="/HibernateDemo/listcar.htm">Garage</a>
            </br>  
             
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Order ID</b></th> 
          <th><b>Restaurant username</b></th> 
          <th><b>Restaurant address</b></th>
          <th><b>Restaurant phone number</b></th>
          
          <th><b>Customer username</b></th> 
          <th><b>Customer address</b></th>
          <th><b>Customer phone number</b></th>
          
          
          
          <th><b>Dishes' ID</b></th> 
          <th><b>Food Price</b></th>
          <th><b>Dilivery Price</b></th>
         </tr> 
        <%
            List<Order> ordersByDriver = (List<Order>) request.getSession().getAttribute("ordersByDriver");
            
            for (Order order : ordersByDriver) {
                Restaurant rest = db.getRestaurant(order.getRestaurant().getRestaurant_id());
                Customer cust = db.getCustomer(order.getCustomer().getId());
        %>
            <tr>
                <td><%=order.getId()%></td>
                <td><%=rest.getUsername() %></td>
                <td><%=rest.getAddress() %></td>
                <td><%=rest.getPhone_number() %></td>
                
                <td><%=cust.getUsername() %></td>
                <td><%=cust.getAddress() %></td>
                <td><%=cust.getPhone_number() %></td>
                
                
                <td><%=order.getDishes() %></td>
                <td><%=order.getFood_price() %></td>
                <td><%=order.getDelivery_fee()%></td>
        <%}%> 
        </table> 
    </body>
</html>
