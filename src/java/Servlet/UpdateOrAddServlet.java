/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author handez
 */
@WebServlet(name = "UpdateOrAddServlet", urlPatterns = {"/UpdateOrAddServlet"})
public class UpdateOrAddServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             String action = request.getParameter("action");
             
             System.out.println(action);
             if(action.equals("Update")){
                 
                 try {
                     String id = request.getParameter("id").trim();
                     DTO.Product p = ProductDAO.ProductByID(id);
                     request.setAttribute("Product", p);
                     request.setAttribute("action", "Update");
                 } catch (SQLException ex) {
                     Logger.getLogger(UpdateOrAddServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 request.getRequestDispatcher("updateproductform.jsp").forward(request, response);
                        
                 
             }else if (action.equals("Add")){
                 request.setAttribute("action", "ADD");
                 request.getRequestDispatcher("updateproductform.jsp").forward(request, response);
             }else if (action.equals("Delete")){
                 int result = 0;
                 String id = request.getParameter("id").trim();
                 result=ProductDAO.DeleteProduct(id);
                 if (result>0){
                     String b="Suscess Deleted!";
                    request.setAttribute("ActionRequest", b);
                     request.getRequestDispatcher("adminView.jsp").forward(request, response);
                 }
             }
           
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateOrAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateOrAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
