
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>New Account</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="createAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}" />
            Username* <input type="text" name="txtUsername"  
                             value="${param.txtUsername}" />(6-20 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}" >
            <font color="red">
                ${errors.usernameLengthError}
            </font>
            <br/>
            </c:if>
            Password* <input type="password" name="txtPassword"  
                             value="${param.txtPassword}" />(6-30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}" >
            <font color="red">
                ${errors.passwordLengthError}
            </font>
            <br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm"  
                            value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatch}">
		<font color="red">
		    ${errors.confirmNotMatch}
		</font> 
                <br/>
	    </c:if>
                
            Contact Name* <input type="text" name="txtContactName"  
                              value="${param.txtContactName}" /><2-50 chars)<br/>
             <c:if test="${not empty errors.contactNameLengthError}">
		<font color="red">
		    ${errors.contactNameLengthError}
		</font> 
                <br/>
	    </c:if>
	    
            Address* <input type="text" name="txtAddress"  
                              value="${param.txtAddress}" />(2-50 chars)<br/>
             <c:if test="${not empty errors.addressLengthError}">
		<font color="red">
		    ${errors.addressLengthError}
		</font> 
                <br/>
	    </c:if>
            
            Phone* <input type="text" name="txtPhone"  
                              value="${param.txtPhone}" />(6-20 chars, numeric)<br/>
             <c:if test="${not empty errors.phoneLengthError}">
		<font color="red">
		    ${errors.phoneLengthError}
		</font> 
                <br/>
	    </c:if>
            <input type="submit" value="Create New Account" name="action"/>
            <input type="reset" value="Reset" /><br/>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font>
            </c:if>
        </form>
    </body>
</html>
