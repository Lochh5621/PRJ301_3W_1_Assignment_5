/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lochh.cart.OrdersDAO;
import lochh.cart.OrdersDTO;
import lochh.cart.ShoppingCartItem;
import lochh.customers.CustomersDTO;
import lochh.products.ProductsDAO;

/**
 *
 * @author win 10
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {
    
    public CartServlet() {
        super();
    }
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
        String action = request.getParameter("action");
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
        } else {
            if (action == null) {
                displayCart(request,response);
            } else if (action.equalsIgnoreCase("add")) {
                addToCart(request, response);
            } else if (action.equalsIgnoreCase("Remove Selected Product(s)")) {
                removeItems(request, response);
            } else if (action.equalsIgnoreCase("update")) {
                updateItem(request, response);
            } else if (action.equalsIgnoreCase("Check Out")) {
                checkOut(request, response);
            }
        }
    }
    
    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
        if (cart != null) 
            session.setAttribute("total", totalPrice(cart));
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
    
    protected void removeItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
            if (session != null) {
                List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
                if (cart != null) {
                        String[] removedItems = request.getParameterValues("chkItem");
                        if (removedItems != null){
                            for (String productID : removedItems) {
                                int index = isExisting(productID, cart);
                                cart.remove(index);
                            }
                        request.setAttribute("cart", cart);
                    }
                }
            }
        response.sendRedirect("cart");
    }
    
    protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductsDAO proDao = new ProductsDAO();
        
        HttpSession session = request.getSession();
        try {
            
            if (session.getAttribute("cart") == null) {
                List<ShoppingCartItem> cart =  new ArrayList<>();
                cart.add(new ShoppingCartItem(proDao.getProductByProductID(request.getParameter("pid")), 1));
                session.setAttribute("cart", cart);
            } else {
                List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
                int index = isExisting(request.getParameter("pid"), cart);
                if (index == -1) {
                    cart.add(new ShoppingCartItem(proDao.getProductByProductID(request.getParameter("pid")), 1));
                } else {
                    int quantity = cart.get(index).getQuantity() +1;
                    cart.get(index).setQuantity(quantity);
                }
                session.setAttribute("cart", cart);
            }
        } catch (NamingException e) {
            log("CartServlet - Naming: " + e.getMessage());
        } catch (SQLException e) {
            log("CartServlet - SQL: " + e.getMessage());
        }
        response.sendRedirect("product");
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
    
    private int isExisting(String id, List<ShoppingCartItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getProductID() == Integer.parseInt(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public BigDecimal totalPrice(List<ShoppingCartItem> cart) {
        BigDecimal count = new BigDecimal(0);
        for (ShoppingCartItem cartItem : cart) {
            BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
            BigDecimal subtotal = cartItem.getProduct().getPrice().multiply(quantity);
            count = count.add(subtotal);
        }
        return count;
    }
    
    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
       
        List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
        String quantity = request.getParameter("txtQuantity");
        int index = isExisting(request.getParameter("pid"), cart);
        cart.get(index).setQuantity(Integer.parseInt(quantity));
        
        session.setAttribute("cart", cart);
        
        response.sendRedirect("cart");
    }
    
    protected void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
                OrdersDTO order = new OrdersDTO();
                order.setCustomer((CustomersDTO)session.getAttribute("CUSTOMER"));
                long millis= System.currentTimeMillis();  
                Date date=new Date(millis);  
                order.setDate(date);
                order.setTotalMoney((BigDecimal)session.getAttribute("total"));
                order.setCart(cart);
                OrdersDAO orderDao = new OrdersDAO();
                orderDao.writeCartToDatabase(order);
                
                session.removeAttribute("cart");
            }
        } catch (NamingException e) {
            log("CheckOut - Naming: " + e.getMessage());
        } catch (SQLException e) {
            log("CheckOut - SQL: " + e.getMessage());
        }
        response.sendRedirect("product");
    }
}
