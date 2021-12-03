/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lochh.customers.CustomerCreateError;
import lochh.customers.CustomersDAO;
import lochh.customers.CustomersDTO;

/**
 *
 * @author win 10
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String ERROR_PAGE = "createNewAccount.jsp";
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
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String contactName = request.getParameter("txtContactName");
        String address = request.getParameter("txtAddress");
        String phone = request.getParameter("txtPhone");
        
        CustomerCreateError errors = new CustomerCreateError();
        boolean foundErr = false;
        String url = ERROR_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect("login.html");
            } else {
                if (username.trim().length() < 6 || username.trim().length() > 20) {
                    foundErr = true;
                    errors.setUsernameLengthError("Username is required input from 6 to 20 chars");
                }
                if (password.trim().length() <6 || password.trim().length() > 30 ){
                    foundErr = true;
                    errors.setPasswordLengthError("Password is required input from 6 to 30 chars");
                } else if (!password.trim().equals(confirm.trim())){
                    foundErr = true;
                    errors.setConfirmNotMatch("Confirm is not matched");
                }
                if (contactName.trim().length() <2 || contactName.trim().length() > 50 ){
                    foundErr = true;
                    errors.setContactNameLengthError("Contact Name is required input from 2 to 50 chars");
                }
                if (address.trim().length() <2 || address.trim().length() > 50){
                    foundErr = true;
                    errors.setAddressLengthError("Address is required input from 2 to 50 chars");
                }
                if (phone.trim().length() <6 || phone.trim().length() > 20 || !phone.matches("[0-9]+")){
                    foundErr = true;
                    errors.setPhoneLengthError("Phone is required input from 6 to 20 chars and be numeric");
                }
                if (foundErr) {
                    //2.1 caching errors, then forward to report errs
                    request.setAttribute("CREATE_ERROR", errors);
                } else {
                    //2.2 call DAO
                    CustomersDAO dao = new CustomersDAO();
                    CustomersDTO customer = new CustomersDTO(0, username, password, contactName, address, phone);
                    boolean result = dao.createAccount(customer);

                    if (result) {
                        url = LOGIN_PAGE;
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request,response);
        } catch (SQLException e) {
            String errMsg = e.getMessage();
            log("CreateAccountServlet _ SQL: " + errMsg);
            if (errMsg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (NamingException e) {
            log("CreateAccountServlet _ Naming: " + e.getMessage());
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