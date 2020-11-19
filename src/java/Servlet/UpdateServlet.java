/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;


import DAO.ProductDAO;
import DTO.Product;
import DTO.ProductErr;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    private final String error = "updateproductform.jsp";

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("productID");
            String name = request.getParameter("productname").trim();
            String price = request.getParameter("productprice").trim();
            String photo = request.getParameter("productimg").trim();
            String ab = request.getParameter("productimg1").trim();
            System.out.println(id);
            System.out.println(name);
            System.out.println(price);
            System.out.println(photo);
            boolean err = false;
            ProductErr ape = new ProductErr();
            ProductDAO dao = new ProductDAO();
           
            
            if(!price.matches("^[0-9]+(\\.[0-9]){0,1}$")||Double.parseDouble(price)<0){
                err=true;
                ape.setPriceerr("Price must be number");
            }
            if(name.isEmpty()||name==null){
                err=true;
                ape.setNameerr("Please not null");
            }
            
            if(photo.isEmpty()){
                photo=ab;
            }
            
            request.setAttribute("ERRORS", ape);
            String url = error;
            if(err==false){
                int res = ProductDAO.updateProduct(name,Double.parseDouble(price),photo,id);
                System.out.println(res);
                if(res>0){
                    url="adminView.jsp";
                    String b="Suscess Updated!";
                    request.setAttribute("ActionRequest", b);
                }
            }
            request.setAttribute("action", "Update");
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
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
