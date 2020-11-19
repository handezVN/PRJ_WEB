<%-- 
    Document   : adminView
    Created on : Nov 3, 2020, 2:52:56 PM
    Author     : handez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DAO.AccountDAO"%>
<%@page import="DAO.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
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
        <%
            String user = ""+request.getAttribute("us");
            System.out.println(user);
            if(user.equals("")) request.getRequestDispatcher("login.jsp").forward(request, response);
        %>
        <c:if test="${ActionRequest!=null}">
            <title></title>
           <script language="javascript">
                   confirm("${ActionRequest}");
           </script>
        </c:if>
           
        <section>
           <h3><font color="blue">Welcome, ${sessionScope.fn}</font></h3>

         <form action="logoutServlet" method="POST">
            <input type="submit" value="Logout" name="btAction" onclick="return confirm('Are you sure to Logout ?')"/>   
        </form>
         <form action="ViewServlet" method="POST">
               <input type="submit" value ="ViewProduct" name="btAction">  
               <input type="submit" value ="ViewUser"name="btAction">  
         </form>
        <form action="HistoryServlet" method="POST">
             <input type="Submit" value="AllHistory" name="action">
         </form>
           <%
                ArrayList<DTO.AdmimHistory> adhis=AccountDAO.getAllHistory();
                request.setAttribute("adhis", adhis);
            %>
         <c:if test="${history eq 'History'}">
             <form action="HistoryServlet" method="POST">
                 Nhập ngày từ ngày<input type="text" name="SearchHistory" value="${param.SearchHistory}"/>Tới ngày hiện tại
                 <input type="Submit" value="Search" name="action"/>
             </form>
                 <c:if test="${not empty err}">
                     <a><font color="RED">${err}</font></a>
                 </c:if>    
             <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
          <c:set var="sum" value="0"/>   
          <c:set var="count" value="0"/>
          <c:forEach var="item" items="${adhis}">
                <c:set var="sum" value="${sum + item.total}"/>  
                <c:set var="count" value="${count+1}"/>
                        <tr>
                        <td>${count}</td>      
                        <td>${item.id }</td>
                        <td>${item.orderid} </td>
                        <td>${item.date }</td>
                        <td>${item.total }</td> 
                        
                        <td><a href="${pageContext.request.contextPath }/HistoryServlet?action=detail&orderid=${item.orderid}"
                               >Detail</a></td>
                       
                    </tr>
          </c:forEach> 
             </table> 
            <h2><font color="Black"><b>Total: ${sum} VND.</b></font></h2>  
          </c:if>          
            
          <c:if test="${history eq 'Search'}">
              <form action="HistoryServlet" method="POST">
                 Nhập ngày từ ngày<input type="text" name="SearchHistory" value="${param.SearchHistory}"/>Tới ngày hiện tại
                 <input type="Submit" value="Search" name="action"/>
             </form>
                 <c:if test="${not empty err}">
                     <a><font color="RED">ABC${err}</font></a>
                 </c:if>
           <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
          <c:set var="sum" value="0"/>   
          <c:set var="count" value="0"/>
          <c:forEach var="item" items="${ListSearch}">
                <c:set var="sum" value="${sum + item.total}"/>  
                <c:set var="count" value="${count+1}"/>
                        <tr>
                        <td>${count}</td>      
                        <td>${item.id }</td>
                        <td>${item.orderid} </td>
                        <td>${item.date }</td>
                        <td>${item.total }</td> 
                        
                        <td><a href="${pageContext.request.contextPath }/HistoryServlet?action=detail&orderid=${item.orderid}"
                               >Detail</a></td>
                       
                    </tr>
          </c:forEach>
                    
             </table> 
          <h2><font color="Black"><b>Total: ${sum} VND.</b></font></h2>  
          </c:if> 
           
       
             <c:if test="${not empty Detail}">
                 <form action="HistoryServlet" method="POST">
                 Nhập ngày từ ngày<input type="text" name="SearchHistory" value="${param.SearchHistory}"/>Tới ngày hiện tại
                 <input type="Submit" value="Search" name="action"/>
             </form>
                 <c:if test="${not empty err}">
                     <a><font color="RED">${err}</font></a>
                 </c:if>
                 <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
          <c:forEach var="item" items="${adhis}">
                    
                        <tr>
                               
                        <td>${item.id }</td>
                        <td>${item.orderid} </td>
                        <td>${item.date }</td>
                        <td>${item.total }</td> 
                        
                        <td><a href="${pageContext.request.contextPath }/HistoryServlet?action=detail&orderid=${item.orderid}"
                               >Detail</a></td>
                        
                    </tr>
          </c:forEach> 
             </table> 
              <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
                  <tr>

                    <th>OrderID</th>
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Sub Total</th>
                    
                </tr>
              <c:forEach var="item" items="${Detail}">
                    
                        <tr>
                               
                        <td>${orderid}</td>
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
             </c:if>    
           <br>
         
        <%
        String view = (String)request.getAttribute("view");
        request.setAttribute("listUser", AccountDAO.getAllAccount());
        String us= (String)request.getAttribute("us");
        %> 
           <c:if test="${view eq 'viewUser'}">
               
           <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
                <tr>

                    <th>User</th>
                    <th>Password</th>
                    <th>FullName</th>
                    <th>isAdmin</th>
                    <th>Number</th>
                    <th>Action </th>
                    <th></th>
                </tr>
                <c:set var="total" value="0"></c:set>
                <c:forEach var="item" items="${listUser}">
                        
                        <tr>
                               
                        <td>${item.username }</td>
                        <td>${item.password} </td>
                        <td>${item.fullname }</td>
                        <td>${item.isAdmin}</td>    
                        <td>${item.number }</td>
                        <c:if test="${item.username ne us}">
                        <td><c:if test="${item.isAdmin == true}">
                            <form action="ViewServlet" method="POST">
                                <input type="hidden" value="${item.username}" name="name">
                                <input type="Submit" value="Remove Admin" name="btAction">    
                            </form>
                            </c:if>
                            <c:if test="${item.isAdmin == false}">
                            <form action="ViewServlet" method="POST">
                                <input type="hidden" value="${item.username}" name="name">
                                <input type="Submit" value="Set Admin" name="btAction">    
                            </form>
                            </c:if>
                        </td>
                        <td><c:if test="${item.isBan==true}">
                                <form action="" method="POST">
                                    <input type="hidden" value="${item.username}" name="name">
                                    <input type="Submit" value="Remove Ban" name="btAction">   
                                </form>
                            </c:if>
                            <c:if test="${item.isBan == false}">
                            <form action="ViewServlet" method="POST">
                                <input type="hidden" value="${item.username}" name="name">
                                <input type="Submit" value="Ban" name="btAction">    
                            </form>
                            </c:if>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
               
            </table>
           </c:if>
            <%
                ProductDAO product = new ProductDAO();
                request.setAttribute("listproduct", product.findAll());
            %>
           <c:if test="${view eq 'viewProduct'}">
               <a href="${pageContext.request.contextPath }/UpdateOrAddServlet?&action=Add">Add New Product</a> <br> 
           <c:forEach var="product" items="${listproduct}">
                <div class="img">
                    <form action="UpdateOrAddServlet" method="POST">
                        <input type="Submit" name="action" value="Update">
                        <input type="Submit" name="action" value="Delete" onclick="return confirm('Are you sure?')">
                        <input type="hidden" name="id" value="${product.id}">
                    
                    </form>
                    ${product.id } - ${product.name } <br> <img
                        src="${pageContext.request.contextPath }/images/${product.photo }" width="120"> <br> 
                    ${product.price } <br>
                    
                </div>
            </c:forEach>
           </c:if>
        </section>
    </body>
</html>
<!--<a href="${pageContext.request.contextPath }/UpdateOrAddServlet?&action=Update&id=${product.id }">Edit</a><br>
                    <a href="${pageContext.request.contextPath }/UpdateOrAddServlet?&action=Delete&id=${product.id }">Delete</a>-->