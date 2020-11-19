/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.AccountDAO;
import DAO.ProductDAO;
import DTO.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {
    private final String invalidPage="login.jsp";
    private final String adminview="adminView.jsp";
    private final String customerview="index.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        String url=invalidPage;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
           
            Account a = AccountDAO.getAccount(username, password);
            HttpSession s = request.getSession();
            if (a != null) {
                
                    if (a.isIsAdmin() == true) {
                        ProductDAO ab = new ProductDAO();
                        s.setAttribute("listproduct", ab.findAll());
                        ArrayList<Account> ac = new ArrayList<>();
                        ac = AccountDAO.getAllAccount();
                        System.out.println(ac.get(1).getFullname());
                        s.setAttribute("listUser", ac);
                        url = adminview;
                    } else {
                        ProductDAO ab = new ProductDAO();
                        s.setAttribute("listproduct", ab.findAll());
                        if(a.isIsBan()==true) {s.setAttribute("ban", "You was banned");}
                        url = customerview;
                    }
                    
                    s.setAttribute("us", username);
                    s.setAttribute("pw", password);
                    s.setAttribute("fn", a.getFullname());
                
               
            } else {
                request.setAttribute("err", "Username or Password is not valid.");
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
