/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.AccountDAO;
import DTO.DetailHistory;
import DTO.HistoryOrder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@WebServlet(name = "HistoryServlet", urlPatterns = {"/HistoryServlet"})
public class HistoryServlet extends HttpServlet {

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
        
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            System.out.println("acb"+action);
            if(action.equals("History")){
            HttpSession s = request.getSession();
            String username = (String) s.getAttribute("us");
            ArrayList<HistoryOrder> his = AccountDAO.getHistory(username);
            request.setAttribute("History", his);
            request.getRequestDispatcher("viewHistory.jsp").forward(request, response);
            }else if (action.equals("AllHistory")){
                
                    request.setAttribute("history", "History");
                    
                 
                request.getRequestDispatcher("adminView.jsp").forward(request, response);
            }else if (action.equals("detail")){
                String orderid = request.getParameter("orderid");
                ArrayList<DetailHistory> list = AccountDAO.getDetailHistory(orderid);
                request.setAttribute("Detail", list);
                request.setAttribute("orderid", orderid);
                request.getRequestDispatcher("adminView.jsp").forward(request, response);
            }else if (action.equals("Search")){
                String date = request.getParameter("SearchHistory");
                if(date.equals("")) date="1900-01-01";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ParsePosition position = new ParsePosition(0);
                formatter.setLenient(false);
                if (formatter.parse(date, position)==null){
                    request.setAttribute("err", "Formart: YYYY-MM-DD");
                }else{
                ArrayList<DTO.AdmimHistory> list = AccountDAO.SearchHistory(date);
                request.setAttribute("ListSearch", list);
                }
                request.setAttribute("history", "Search");
                
                request.getRequestDispatcher("adminView.jsp").forward(request, response);
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
            Logger.getLogger(HistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(HistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
