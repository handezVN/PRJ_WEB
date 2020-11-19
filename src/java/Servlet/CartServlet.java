package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.ShoppingCartItem;
import DAO.ProductDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/CartServlet")  
public class CartServlet extends HttpServlet {

    public CartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            displayCart(request, response);
        } else {
            if (action.equalsIgnoreCase("buy")) {
                try {
                    buyItem(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equalsIgnoreCase("remove")) {
                removeItem(request, response);
            }
        }
    }

    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void removeItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); 
        
        List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"), cart);
        cart.remove(index);
        
        session.setAttribute("cart", cart);
        
        response.sendRedirect("CartServlet");
    }

    protected void buyItem(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
        ProductDAO prodDao = new ProductDAO();
        
        HttpSession session = request.getSession();
        String ab = request.getParameter("id");
        if (session.getAttribute("cart") == null) { 
            List<ShoppingCartItem> cart = new ArrayList<ShoppingCartItem>();
            
            System.out.println(ab+"1234");
            System.out.println(prodDao.ProductByID(ab));
            cart.add(new ShoppingCartItem(prodDao.ProductByID(ab), 1));
            session.setAttribute("cart", cart); 
            
        } else {
            List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
            int index = isExisting(request.getParameter("id").trim(), cart);
            if (index == -1) {
                cart.add(new ShoppingCartItem(prodDao.ProductByID(ab), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("CartServlet");
    }

    private int isExisting(String id, List<ShoppingCartItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
