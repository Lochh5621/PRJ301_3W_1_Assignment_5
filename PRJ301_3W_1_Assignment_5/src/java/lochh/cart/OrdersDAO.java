/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lochh.customers.CustomersDTO;
import lochh.products.ProductsDAO;
import lochh.utils.DBConnect;

/**
 *
 * @author win 10
 */
public class OrdersDAO implements Serializable{
    public boolean writeCartToDatabase(OrdersDTO order) 
	    throws SQLException, NamingException{
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    con = DBConnect.makeConnection();
	    if (con != null){
                String sql = "INSERT INTO Orders (customerID, totalMoney, date) "
			    + "VALUES(?, ?, ?)";
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                stm.setInt(1, order.getCustomer().getCustomerID());
                stm.setBigDecimal(2, order.getTotalMoney());
                stm.setDate(3, order.getDate());
                
                stm.execute(); 
                rs = stm.getGeneratedKeys();
                if (rs.next()){
                    int orderID = rs.getInt(1);

                    sql = "INSERT INTO orderDetail (orderID, productID,quantity) "
                                + "VALUES(?, ?, ?)";
                    stm = con.prepareStatement(sql);

                    int row = 0;	 

                    for (ShoppingCartItem item : order.getCart()) {
                        stm.setInt(1, orderID);
                        stm.setInt(2, item.getProduct().getProductID());
                        stm.setInt(3, item.getQuantity());
                        row += stm.executeUpdate();
                    } 
                    
                    for (ShoppingCartItem item : order.getCart()) {            
                        sql = "UPDATE Products "
                                + "SET quantity = ? "
                                + "WHERE productID = ? ";
                        stm = con.prepareStatement(sql);
                        stm.setInt(1, item.getProduct().getQuantity() - item.getQuantity());
                        stm.setInt(2, item.getProduct().getProductID());
                        stm.executeUpdate();
                    }

                    if (row == order.getCart().size()){
                        return true;
                    }
                }
	    } //end if con is null
	} finally {
            if (rs != null) {
                rs.close();
            }
	    if (stm != null){
		stm.close();
	    }
	    if (con != null){
		con.close();
	    }
	}
	return false;
    }
    
    public List<OrdersDTO> getOrderHistory(CustomersDTO customer) 
            throws NamingException, SQLException{
        Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs1 = null;
        ResultSet rs2 = null;
        List<OrdersDTO> list = new ArrayList<>();
        
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM Orders "
                        + "WHERE customerID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, customer.getCustomerID());
                rs1 = stm.executeQuery();
                while (rs1.next()) {
                    int id = rs1.getInt("id");
                    BigDecimal totalMoney = rs1.getBigDecimal("totalMoney");
                    Date date = rs1.getDate("date");

                    OrdersDTO order = new OrdersDTO();
                    order.setTotalMoney(totalMoney);
                    order.setCustomer(customer);
                    order.setDate(date);

                    sql = "SELECT * "
                            + "FROM OrderDetail "
                            + "WHERE orderID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, id);
                    rs2 = stm.executeQuery();
                    while (rs2.next()) {
                        int productID = rs2.getInt("productID");
                        int quantity = rs2.getInt("quantity");
                        ProductsDAO dao = new ProductsDAO();
                        order.getCart().add(new ShoppingCartItem(dao.getProductByProductID(String.valueOf(productID)), quantity));
                    }
                    
                    list.add(order);
                }
                return list;
            } 
        }  finally {
            if (rs2 != null) {
                rs2.close();
            }
            if (rs1 != null) {
                rs1.close();
            }
	    if (stm != null){
		stm.close();
	    }
	    if (con != null){
		con.close();
	    }
	}
	return null;
    }
}
