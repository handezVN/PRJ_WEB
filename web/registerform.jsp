<%-- 
    Document   : registerform
    Created on : Oct 25, 2020, 1:20:26 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <style title="text/css">
        body {
                background-image: url("https://worldstrides.com/wp-content/uploads/2018/02/iStock-155097702-min.jpg");
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                font-family: 'Montserrat', sans-serif;
                height: 100vh;
                margin: -20px 0 50px;
            }
            table {
                color: white;
            }
            .color{
                color: white;
            }
     </style>   
    <body>
        <p><a href="login.jsp" >Back to Login Page</a></p>
        <center>
            <h1 class="color">Register An Account</h1>
        <form action="RegisterServlet" method="POST">
            <table>
                <c:set var="err" value="${requestScope.ERRORS}"/>
                    <tr>
                        <td font="Color.White">Username: </td>
                        <td><input type="text" name="txtUn" value="${param.txtUn}"/>(6-20 chars)</td>
                    </tr>
                
                <c:if test="${not empty err.usernameErr}">
                    <tr>
                        <td><font color="red">${err.usernameErr}</font></td>
                    </tr>
                    
                </c:if>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="txtPw" value="${param.txtPw}"/>(6-20 chars)</td>
                    </tr>    
                
                <c:if test="${not empty err.passwordErr}">
                    <tr>
                        <td><font color="red">${err.passwordErr}</font></td>
                    </tr>
                    
                </c:if>
                    <tr>
                        <td>Number: </td>
                        <td><input type="text" name="txtNumber" value="${param.txtNumber}"/></td>
                    </tr>   
               
                <c:if test="${not empty err.confirmErr}">
                    <tr>
                        <td> <font color="red">${err.confirmErr}</font></td>
                    </tr>
                   
                </c:if>
                    <tr>
                        <td>Full name: </td>
                        <td><input type="text" name="txtFullname" value="${param.txtFullname}"/>(5-50 letters)</td>
                    </tr>   
                
                <c:if test="${not empty err.fullnameErr}">
                    <tr>
                        <td><font color="red">${err.fullnameErr}</font></td>
                    </tr>
                    
                </c:if>
                    
                
                <c:if test="${not empty err.duplicateUsername}">
                    <tr>
                        <td><font color="red">${err.duplicateUsername}</font></td>
                    </tr>
                    
                </c:if>    
        
            </table>
                    <br/>
                    <input type="submit" value="Register Account" name="btAction" />
                    
        </form>
        </center>        
    </body>
</html>
