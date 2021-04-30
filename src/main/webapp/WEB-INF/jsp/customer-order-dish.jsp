<%-- 
    Document   : customer-order-dish
    Created on : 2021-4-4, 14:39:49
    Author     : Lenovo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.finalproject.pojo.Dish"%>
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
            Restaurant restaurantChosenByCustomer = (Restaurant) request.getSession().getAttribute("restaurantChosenByCustomer");
            List<Dish> dishesFromRestaurant = (List<Dish>) request.getSession().getAttribute("dishesFromRestaurant");
        %>
        
        Restaurant ID: <%=restaurantChosenByCustomer.getRestaurant_id() %>
        <br/>
        Username: <%=restaurantChosenByCustomer.getUsername()%>
        <br/>
        Restaurant Name: <%=restaurantChosenByCustomer.getRestaurant_name()%>
        <br/>
        Phone Number: <%=restaurantChosenByCustomer.getPhone_number()%>
        <br/>
        Email: <%=restaurantChosenByCustomer.getEmail()%>
        <br/>
        Address: <%=restaurantChosenByCustomer.getAddress()%>
        <br/>
        Restaurant Category: <%=restaurantChosenByCustomer.getRestaurant_category()%>
        <br/>
        Introduction: <%=restaurantChosenByCustomer.getIntro()%>
        <br/>
        <br/>
        
        <form action="/HibernateDemo/menu.pdf" method="GET">
            <input type="submit" value="View PDF Menu">
        </form>
        <br/>
        <br/>
        
        <%
            List<Dish> appetizer = new ArrayList<>();
            List<Dish> salad = new ArrayList<>();
            List<Dish> entree = new ArrayList<>();
            List<Dish> seafood = new ArrayList<>();
            List<Dish> dessert = new ArrayList<>();
            List<Dish> drink = new ArrayList<>();
            for (Dish dish : dishesFromRestaurant) {
                if (dish.getDish_category().equals("Appetizer"))
                    appetizer.add(dish);
                else if (dish.getDish_category().equals("Soup&Salad")) 
                    salad.add(dish);
                else if (dish.getDish_category().equals("Entree")) 
                    entree.add(dish);
                else if (dish.getDish_category().equals("Seafood")) 
                    seafood.add(dish);
                else if (dish.getDish_category().equals("Dessert")) 
                    dessert.add(dish);
                else  
                    drink.add(dish);
            }
        %>
        <form action="/HibernateDemo/order/add.htm" method="GET">
            Appetizer:
            <br/>
            <%
                for (int i=0; i<appetizer.size(); i++) {
            %>
            <input type="checkbox" name="dishes" value="<%=appetizer.get(i).getDish_id() %>" />
            <%=appetizer.get(i).getDish_name()%>, <%=appetizer.get(i).getPrice()%>, <%=appetizer.get(i).getInfo()%>
            Number:
            <input type="text" name="<%=appetizer.get(i).getDish_id() %>">
            <br/>
            <%
                }
            %>
            <br/>
            
            Soup&Salad:
            <br/>
            <%
                for (int i=0; i<salad.size(); i++) {
            %>
            <input type="checkbox" name="dishes" value="<%=salad.get(i).getDish_id() %>" />
            <%=salad.get(i).getDish_name()%>, <%=salad.get(i).getPrice()%>, <%=salad.get(i).getInfo()%>
            Number:
            <input type="text" name="<%=salad.get(i).getDish_id() %>">
            <br/>
            <%
                }
            %>
            <br/>
            
            Entree:
            <br/>
            <%
                for (int i=0; i<entree.size(); i++) {
            %>
            <input type="checkbox" name="dishes" value="<%=entree.get(i).getDish_id() %>" />
            <%=entree.get(i).getDish_name()%>, <%=entree.get(i).getPrice()%>, <%=entree.get(i).getInfo()%>
            Number:
            <input type="text" name="<%=entree.get(i).getDish_id() %>">
            <br/>
            <%
                }
            %>
            <br/>
            
            Seafood:
            <br/>
            <%
                for (int i=0; i<seafood.size(); i++) {
            %>
            <input type="checkbox" name="dishes" value="<%=seafood.get(i).getDish_id() %>" />
            <%=seafood.get(i).getDish_name()%>, <%=seafood.get(i).getPrice()%>, <%=seafood.get(i).getInfo()%>
            Number:
            <input type="text" name="<%=seafood.get(i).getDish_id() %>">
            <br/>
            <%
                }
            %>
            <br/>
            
            Dessert:
            <br/>
            <%
                for (int i=0; i<dessert.size(); i++) {
            %>
            <input type="checkbox" name="dishes" value="<%=dessert.get(i).getDish_id() %>" />
            <%=dessert.get(i).getDish_name()%>, <%=dessert.get(i).getPrice()%>, <%=dessert.get(i).getInfo()%>
            Number:
            <input type="text" name="<%=dessert.get(i).getDish_id() %>">
            <br/>
            <%
                }
            %>
            <br/>
            
            Drink:
            <br/>
            <%
                for (int i=0; i<drink.size(); i++) {
            %>
            <input type="checkbox" name="dishes" value="<%=drink.get(i).getDish_id() %>" />
            <%=drink.get(i).getDish_name()%>, <%=drink.get(i).getPrice()%>, <%=drink.get(i).getInfo()%>
            Number:
            <input type="text" name="<%=drink.get(i).getDish_id() %>">
            <br/>
            <%
                }
            %>
            <br/>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
