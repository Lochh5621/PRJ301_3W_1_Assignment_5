<%-- 
    Document   : product
    Created on : Apr 13, 2021, 8:14:39 PM
    Author     : win 10
--%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Product Page</title>
        <style type="text/css">
            header {
                height: 120px;
                align-items: center;
            }
            .img {
                text-align: center;
                border: 1px solid green;
                padding: 5px;
                height: 250px;
                width: 200px;
                float: left;
            }
        </style>
    </head>
    <body>
        <header><img alt="" src="images/brand.png"></header>
        <c:if test="${empty sessionScope.CUSTOMER}">
            <jsp:forward page="login.html"/>
        </c:if> 
        <font color="red">
        Welcome, ${sessionScope.CUSTOMER.contactName}
        </font>
        (<a href="logOut">Logout</a>) <br/>
        <h1>Product Page</h1> <br/>
        <form action="product" >
        Search Value: <input type="text" name="txtSearchValue" 
                            value="${param.txtSearchValue}" />
        <input type="submit" name="action" value="Search" />
        </form><br/>
        <a href="cart">View Your Cart</a> <br/>
        <a href="oldOrder">View history of old order(s)</a><br/>
        <c:set var="listProduct" value="${requestScope.PRODUCTS_LIST}"/>
        <c:if test="${not empty listProduct}"> 
            <c:forEach var="product" items="${listProduct}">
                <div class="img">
                    ${product.productID} - ${product.productName} - ${product.size.sizeName} <br/> 
                    <img src="images/${product.image}" width="120"> <br/>
                    Price: ${product.price} <br/>
                    Status: <c:if test="${product.status}">
                        <font color="green">In stock</font> <br/>
                        <a href="cart?action=add&pid=${product.productID}">Add To Your Cart</a>
                    </c:if>
                    <c:if test="${not product.status}"><font color="red">Out of stock</font> </c:if><br/>
                </div>
            </c:forEach> 
        </c:if> 
    </body>
</html>
