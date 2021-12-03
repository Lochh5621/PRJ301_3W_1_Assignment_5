<%-- 
    Document   : makeOrder.jsp
    Created on : Apr 25, 2021, 1:57:09 PM
    Author     : win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<html>
    <c:if test="${empty sessionScope.CUSTOMER}">
        <jsp:forward page="login.html"/>
    </c:if> 
    <head>
        <title>Order Page</title> 
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.CUSTOMER.contactName}
        </font>
        (<a href="logOut">Logout</a>) <br/>
        <h1>Your order include: </h1>
        <c:set var="lst" value="${sessionScope.cart}"/>
        <c:if test="${not empty lst}" >
        <table cellpadding="0" cellspacing="0" border="1" allign="center" width="75%">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Size</th>
                    <th>Product Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Sub Total</th>
                </tr>
            </thead>
            <tbody>
                    <c:forEach var="dt" items="${lst}">
                <tr>
                    <td>
                        ${dt.product.productID}
                    </td>
                    <td>${dt.product.productName}</td>
                    <td>${dt.product.size.sizeName}</td>
                    <td><img src="images/${dt.product.image}"></td>
                    <td>${dt.product.price}</td>
                    <td>${dt.quantity}</td>
                    <td>${dt.product.price * dt.quantity}</td>
                </tr>
                    </c:forEach>
                <tr>
                    <td colspan="6" align="right" >Total</td>
                    <td>${sessionScope.total}</td>
                </tr>
            </tbody>
        </table>
        <br/>
        <form action="cart">
            <input type="submit" value="Check out" name="action" onclick="alert('Your order was successful.')"/>
        </form>
        </c:if>       
        <footer> 
            <br/><a href="cart">Return To Your Cart</a><br/>
            <a href="product">View Product List</a> 
        </footer>
    </body>
</html>

