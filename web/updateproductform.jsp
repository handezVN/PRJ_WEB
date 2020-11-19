<%-- 
    Document   : updateproductform
    Created on : Oct 29, 2020, 9:31:48 PM
    Author     : DELL
--%>

<%@page import="DTO.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product Page</title>
    </head>
    <body>
        <p><a href="adminView.jsp">Back</a></p>
        <center>
        <h1>${action} Product Form</h1>
        <p><i>You need fill up following to update.</i></p> 
                <%
            Product p = (Product)request.getAttribute("Product");
            String action = (String)request.getAttribute("action");
                %>
        <form action="${action}Servlet" method="POST">
            <c:set var="err" value="${requestScope.ERRORS}"/>
            <table>
                <c:if test="${action eq 'Update'}">
                    <tr>
                        <td>ID: </td>
                        <td><input type="txt" name="productID1" value="${Product.id} ${param.productID}" disabled=""/></td>
                        <td><input type="hidden" name="productID" value="${Product.id}${param.productID}"/></td>
                    </tr>
                </c:if>
                <c:if test="${action eq 'ADD'}">
                    <tr>
                        <td>ID: </td>
                        <td><input type="txt" name="productID" value="${Product.id}${param.productID}" /></td>    
                    </tr>
                </c:if>
                    <c:if test="${not empty err.iderr}">
                    <tr>
                        <td><font color="red">${err.iderr}</font></td>
                    </tr>
                    
                    </c:if>
                    <tr>
                        <td>Name: </td>
                        <td><input type="txt" name="productname" value="${Product.name}${param.productname}"/>(2-20 chars)</td>
                    </tr>    
                
                <c:if test="${not empty err.nameerr}">
                    <tr>
                        <td><font color="red">${err.nameerr}</font></td>
                    </tr>
                    
                </c:if>
                    <tr>
                        <td>Price: </td>
                        <td><input type="text" name="productprice" value="${Product.price}${param.productprice}"/></td>
                    </tr>   
               
                <c:if test="${not empty err.priceerr}">
                    <tr>
                        <td> <font color="red">${err.priceerr}</font></td>
                    </tr>
                   
                </c:if>
                    <tr>
                        <td>Img URL: </td>
                        
                        <td><input type="file" name="productimg" value="${Product.photo}${param.productimg}" /></td>
                            <input type="hidden" name="productimg1" value="${Product.photo}${param.productimg}"/>
                    </tr>   
                
                <c:if test="${not empty err.imgerr}">
                    <tr>
                        <td><font color="red">${err.imgerr}</font></td>
                    </tr>
                    
                </c:if>
                    
                  
                 </table>
                    <br/>
                    <input type="submit" value="${action}" name="btAction" />
                    
        </form>
        </center>        
    </body>
</html>
