/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.AccountDAO;
import DTO.ShoppingCartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Handez
 */
@WebServlet(name = "PayServlet", urlPatterns = {"/PayServlet"})
public class PayServlet extends HttpServlet {

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
         Random rd = new Random(); 
         int random = rd.nextInt(1000);
         String idOrder = "OD"+random;
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.util.Date d = new java.util.Date();
            String date = df.format(d);
            HttpSession s = request.getSession();
         String user = (String) s.getAttribute("us");
         if(user!=null){
         ArrayList<ShoppingCartItem> list = (ArrayList<ShoppingCartItem>) s.getAttribute("cart");
         if(list!=null){
         double total = 0;
         for(ShoppingCartItem sp : list){
                  total =total + sp.getProduct().getPrice()*sp.getQuantity();
                }

            int i = -1;
            int i2 = -1;
            i=AccountDAO.payment(idOrder, user, date,total);
            if(i!=-1){
                
                for(ShoppingCartItem sp : list){
                    System.out.println(sp.getProduct().getId());
                    i2 = AccountDAO.Detailpayment(idOrder, sp.getProduct().getId(), sp.getProduct().getPrice(), sp.getQuantity(), sp.getProduct().getPhoto());
                    
                }
            }
            if (i2!=-1){
                s.setAttribute("Detail", AccountDAO.getDetailHistory(idOrder));
                s.setAttribute("orderid", idOrder);
                s.removeAttribute("cart");
                response.sendRedirect("confirmview.jsp");
            }
        }
        }else response.sendRedirect("login.jsp");
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
            Logger.getLogger(PayServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PayServlet.class.getName()).log(Level.SEVERE, null, ex);
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
