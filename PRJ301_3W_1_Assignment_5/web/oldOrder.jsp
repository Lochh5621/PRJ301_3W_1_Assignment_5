<%-- 
    Document   : oldOrder
    Created on : Apr 25, 2021, 2:49:27 PM
    Author     : win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:if test="${empty sessionScope.CUSTOMER}">
        <jsp:forward page="login.html"/>
    </c:if> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Of Order(s) Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.CUSTOMER.contactName}
        </font>
        (<a href="logOut">Logout</a>) <br/>
        <h1>History Of Order(s) Page</h1> <br/>
        <c:set var="orderList" value="${requestScope.ORDER_LIST}"/>
        <c:if test="${not empty orderList}">
            <c:forEach var="order" items="${orderList}">
                <div>Date: ${order.date}</div>
                <c:set var="cart" value="${order.cart}"/>
                <c:if test="${not empty cart}"> 
                    <table border="1">
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
                        <c:forEach var="dt" items="${cart}">
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
                                <td>${order.totalMoney}</td>
                            </tr>
                    </tbody>
                    </table>
                </c:if>
                </br>
            </c:forEach>
        </c:if>
    </body>
</html>
