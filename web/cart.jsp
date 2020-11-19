<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            table{
                background: white;
            }
            .img {
                text-align: center;
                border: 1px solid green;
                padding: 5px; 
                margin: 5px;
                height : 250px;
                width: 200px;
                float: left;
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
        <section>
            <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
                <tr>

                    <th>Id</th>
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Sub Total</th>
                    <th>Option</th>
                </tr>
                <c:set var="total" value="0"></c:set>
                <c:forEach var="item" items="${sessionScope.cart}">
                    <c:set var="total" value="${total + item.product.price * item.quantity}"></c:set>
                        <tr>

                            <td>${item.product.id }</td>
                        <td>${item.product.name }</td>
                        <td>
                            <img src="${pageContext.request.contextPath }/images/${item.product.photo}" width="100">
                        </td>
                        <td>${item.product.price }</td>
                        <td>${item.quantity }</td>
                        <td>${item.product.price * item.quantity }</td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath }/CartServlet?action=remove&id=${item.product.id }"
                               onclick="return confirm('Are you sure?')">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6" align="right">Total</td>
                    <td>${total }</td>
                </tr>
            </table>
                
                
        </section>
                <c:if test="${sessionScope.ban==null}">
                <c:if test="${sessionScope.cart!=null}">
        <form action="PayServlet" method="POST">
            <input type="Submit" name="pay" value="PayMent" onclick="return confirm('Total All: ${total}')">
        </form>    
                </c:if>
                </c:if>
                <c:if test="${sessionScope.ban!=null}">
                <form action="Banview.jsp" method="POST">
                <input type="Submit" name="payBan" value="PayMent">
                </form>   
                </c:if>
                <footer> <br><br><a href="${pageContext.request.contextPath }/index.jsp">View Product List</a></footer>
    </body>
</html>




