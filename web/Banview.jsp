<%-- 
    Document   : Banview
    Created on : Nov 18, 2020, 1:31:01 PM
    Author     : handez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sorry You Can't Buy AnyThing Because You have been banned by Admin</h1>
         <form action="logoutServlet" method="POST">
            <input type="submit" value="Click Here to Login" name="btAction"/>   
        </form>
    </body>
</html>
