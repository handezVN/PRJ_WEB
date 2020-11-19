<%@page import="DAO.ProductDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Product Page</title>
        <style type="text/css">
            body{
                 background-image: url("https://i.insider.com/5e3b2de1d9db1d53404d1872?width=750&format=jpeg&auto=webp");
            }
            header {
                height: 170px;
                align-items: center;
            }
            section {
            }
            .img {
                text-align: center;
                border: 1px solid green;
                padding: 5px; 
                margin: 5px;
                height : 200px;
                width: 200px;
                float: left;
                color:black;
                background: paleturquoise;
            }
            footer {
                clear: both;
                height: 120px;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <header>
           
        </header>
         <h3><font color="blue">Welcome, ${sessionScope.fn}</font></h3>
         <h2><font color="Red">${sessionScope.ban}</font></h2>
         <a>
         <c:if test="${sessionScope.fn != null}">  
         <form action="HistoryServlet" method="POST">
             <input type="Submit" value="History" name="action">
         </form>
           
         <form action="logoutServlet" method="POST">
            <input type="submit" value="Logout" name="btAction" onclick="return confirm('Are you sure to Logout ?')"/>   
        </form>
        </c:if>
        <c:if test="${sessionScope.fn == null}">  
            <form action="loginServlet" method="POST">
            <input type="submit" value="Login" />   
        </form>
         </c:if>   
        </a>     
        <section>
            <%
                ProductDAO product = new ProductDAO();
                request.setAttribute("listproduct", product.findAll());
            %>
          
            <c:forEach var="product" items="${listproduct}">
                <div class="img">
                    <a class="abc" href="${pageContext.request.contextPath }/CartServlet?&action=buy&id=${product.id }">Buy</a><br>
                    ${product.price}<br>
                    ${product.id } - ${product.name } <br> <img
                        src="${pageContext.request.contextPath }/images/${product.photo }" width="120"> <br> 
                    
                   
                   
                   </div>
                
                    
                    
            </c:forEach>
            
        </section>

        <footer> <br><br><a href="${pageContext.request.contextPath }/CartServlet">View Shopping Cart</a> </footer>
        
    </body>
</html>