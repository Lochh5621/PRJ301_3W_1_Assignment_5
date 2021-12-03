<%-- 
    Document   : cart
    Created on : Apr 20, 2021, 7:38:50 AM
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
        <title>Cart Page</title> 
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.CUSTOMER.contactName}
        </font>
        (<a href="logOut">Logout</a>) <br/>
        <h1>Your cart include: </h1>
        <form action="cart" id="deleteForm"></form>
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
                    <th colspan="2">Option</th>
                </tr>
            </thead>
            <tbody>
                    <c:forEach var="dt" items="${lst}">
                    <form action="cart" name="updateCart" method="POST">
                <tr>
                    <td>
                        ${dt.product.productID}
                        <input type="hidden" name="pid" value="${dt.product.productID}" />
                    </td>
                    <td>${dt.product.productName}</td>
                    <td>${dt.product.size.sizeName}</td>
                    <td><img src="images/${dt.product.image}"></td>
                    <td>${dt.product.price}</td>
                    <td><input type="number" name="txtQuantity" value="${dt.quantity}" min="1"/></td>
                    <td>${dt.product.price * dt.quantity}</td>
                    <td align="center">
                        <input type="checkbox" name="chkItem" 
                                               value="${dt.product.productID}" form="deleteForm"/>
                    </td>
                    <td align="center"><input type="submit" value="Update" name="action" /></td>
                </tr>
                    </form>
                    </c:forEach>
                <tr>
                    <td colspan="6" align="right" >Total</td>
                    <td>${sessionScope.total}</td>
                    <td colspan="2" align="center"> <input type="submit" value="Remove Selected Product(s)" name="action" form="deleteForm" onclick="return confirm('Are you sure?')"/> </td>
                </tr>
            </tbody>
        </table>
        <br/>
        <form action="makeOrder.jsp">
            <input type="submit" value="Order" name="action" />
        </form>
                </c:if>
                <c:if test="${empty lst}">
                    <h2>Your cart is empty.</h2>
                </c:if>              
        <footer> <br/><br/><a href="product">View Product List</a> </footer>
    </body>
</html>



