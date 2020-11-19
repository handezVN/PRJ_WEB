<%-- 
    Document   : viewHistory
    Created on : Nov 3, 2020, 12:54:07 AM
    Author     : Handez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <footer> <br><br><a href="${pageContext.request.contextPath }/index.jsp">View Product List</a></footer>
    <body>
         <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
                <tr>
                    <th>OrderID<th>
                    <th>Date</th>
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Sub Total</th>
                    
                </tr>
                <c:set var="total" value="0"></c:set>
                <c:forEach var="item" items="${History}">
                    
                        <tr>
                            <td>${item.orderid}</td>   
                            <td>${item.date }</td>
                        <td>${item.product} </td>
                        <td>
                            <img src="${pageContext.request.contextPath }/images/${item.photo}" width="100">
                        </td>
                        <td>${item.price }</td>
                        <td>${item.quantity }</td>    
                         <td>${item.quantity*item.price }</td>   
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6" align="right">Total</td>
                    <td>${total }</td>
                </tr>
            </table>
    </body>
</html>
