<%-- 
    Document   : confirmview
    Created on : Oct 26, 2020, 10:35:46 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.orderid}">
        <center>
            <form action="HistoryServlet" method="POST">
                <h1><font color="silver">Thanks,${sessionScope.fn}</font></h1><br/>
                <h2>Your order <u>${sessionScope.orderid}</u> is generated.</h2><br/>
                
                <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
                  <tr>

                   
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Sub Total</th>
                    
                </tr>
              <c:forEach var="item" items="${Detail}">
                    
                        <tr>
                               
                       
                        <td>${item.product} </td>
                        <td>
                            <img src="${pageContext.request.contextPath }/images/${item.photo}" width="100">
                        </td>
                        <td>${item.price }</td>
                        <td>${item.quantity }</td>    
                         <td>${item.quantity*item.price }</td>   
                    </tr>
                </c:forEach>
              </table>
                <a href="${pageContext.request.contextPath }/index.jsp">Buy More</a>
                <br/>
                <input type="submit" name="action" value="History"/><br/>
            </form>
        </center>
        </c:if>
        
    </body>
</html>
