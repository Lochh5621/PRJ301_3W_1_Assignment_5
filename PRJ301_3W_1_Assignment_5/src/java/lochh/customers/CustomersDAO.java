/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.customers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import lochh.utils.DBConnect;

/**
 *
 * @author win 10
 */
public class CustomersDAO implements Serializable{
    public CustomersDTO checkLogin(String username, String password) 
    throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBConnect.makeConnection();
            String sql = "SELECT customerID, username, password, contactName, address, phone "
                    + "FROM Customers "
                    + "WHERE username = ? and password = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("customerID");
                String contactName = rs.getString("contactName");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                CustomersDTO dto = new CustomersDTO(customerID, username, password, contactName, address, phone);
                return dto;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return null;
    }
    
    public boolean createAccount (CustomersDTO customer) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
        //1.Connect DB
            con=DBConnect.makeConnection();
            if(con!=null){
                //2. Create SQL String
                String sql= "Insert into "
                        + "Customers(username, password, contactName, address, phone) "
                        + "Values(?, ?, ?, ?, ?) ";
                    
                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, customer.getUsername());
                stm.setString(2, customer.getPassword());
                stm.setString(3, customer.getContactName());
                stm.setString(4, customer.getAddress());
                stm.setString(5, customer.getPhone());
                //4. Execute Quer
                int rowEffect = stm.executeUpdate();
                
                if(rowEffect > 0){
                    return true;
                }
            }//end if con is opened
        } finally{
            if(con != null){
                con.close();
            }
            if(stm != null){
                stm.close();
            }
        }
        return false;
    }
}
