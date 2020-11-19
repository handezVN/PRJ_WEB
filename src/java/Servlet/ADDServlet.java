/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ProductDAO;
import DTO.ProductErr;
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
@WebServlet(name = "ADDServlet", urlPatterns = {"/ADDServlet"})
public class ADDServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private final String error = "updateproductform.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = request.getParameter("productID").trim();
            String name = request.getParameter("productname").trim();
            String price = request.getParameter("productprice").trim();
            String photo = request.getParameter("productimg").trim();
            System.out.println(id);
            System.out.println(name);
            System.out.println(price);
            System.out.println(photo);
            boolean check  = ProductDAO.checkIDProduct(id);
            System.out.println("abc"+check);
             boolean err = false;
            ProductErr ape = new ProductErr();
            
            if(!price.matches("^[0-9]+(\\.[0-9]){0,1}$")){
                err=true;
                ape.setPriceerr("Price must be number");
            }
             if(name.isEmpty()||name==null){
                err=true;
                ape.setNameerr("Not Null");
            }
            if(check){
                err=true;
                ape.setIderr("ID Exist");
            }
            if(id.isEmpty()){
                err=true;
                ape.setIderr("Id not Null");
            }
            if(photo.isEmpty()){
                err=true;
                ape.setImgerr("Please Select A Picture!");
            }
            request.setAttribute("ERRORS", ape);
            request.setAttribute("action", "ADD");
            String url = error;
            if(err==false){
                int res;
                try {
                    res = ProductDAO.ADDProduct(id, name, Double.parseDouble(price), photo);
                    if(res>0){
                    url="adminView.jsp";
                }
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
                
                
            }
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
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ADDServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ADDServlet.class.getName()).log(Level.SEVERE, null, ex);
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
