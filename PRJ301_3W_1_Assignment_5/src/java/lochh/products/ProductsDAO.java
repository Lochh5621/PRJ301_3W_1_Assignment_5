/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.products;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lochh.categories.CategoriesDAO;
import lochh.categories.CategoriesDTO;
import lochh.size.SizeDAO;
import lochh.size.SizeDTO;
import lochh.utils.DBConnect;

/**
 *
 * @author win 10
 */
public class ProductsDAO implements Serializable{
    
    CategoriesDAO cateDao = new CategoriesDAO();
    SizeDAO sizeDao = new SizeDAO();
    
    public List<ProductsDTO> getAllProducts() 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductsDTO> list = new ArrayList<>();
        
        try{
            con=DBConnect.makeConnection();
            if(con!=null){
                String sql= "Select * "
                        + "From Products ";
                
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while(rs.next()){
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    SizeDTO size = sizeDao.getSizeByID(rs.getInt("size"));
                    CategoriesDTO cate = cateDao.getCategoryByID(rs.getInt("CategoryID"));
                    BigDecimal price = rs.getBigDecimal("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    
                    ProductsDTO dto = new ProductsDTO(
                            productID, productName, size, cate, price, quantity, image, status);
                    
                    list.add(dto);
                }//end while traversion is executed
                return list;
            }//end if con is opened
            return null;
        } finally{ 
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
             if(con != null){
                con.close();
            }
        }
        
    }
    
    public List<ProductsDTO> searchName (String searchValue) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductsDTO> list = null;
        
        try{
        //1.Connect DB
            con=DBConnect.makeConnection();
            if(con!=null){
                //2. Create SQL String
                String sql= "Select * "
                        + "From Products "
                        + "Where productName Like ?";
                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                while(rs.next()){
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    SizeDTO size = sizeDao.getSizeByID(rs.getInt("size"));
                    CategoriesDTO cate = cateDao.getCategoryByID(rs.getInt("CategoryID"));
                    BigDecimal price = rs.getBigDecimal("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    ProductsDTO dto = new ProductsDTO(
                            productID, productName, size, cate, price, quantity, image, status);
                    
                    if(list == null) {
                        list = new ArrayList<>();
                        
                    }
                    list.add(dto);
                }
                return list;
            }
            return null;
        } finally{
            if(con != null){
                con.close();
            }
            if(stm != null){
                stm.close();
            }
            if(rs != null){
                rs.close();
            }
        }
    }
        
    public ProductsDTO getProductByProductID(String id) 
        throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductsDTO dto = null;
        
        try{

            con=DBConnect.makeConnection();
            if(con!=null){

                String sql= "Select * "
                        + "From Products "
                        + "Where productID = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
 
                rs = stm.executeQuery();

                if (rs.next()){
                    String productName = rs.getString("productName");
                    SizeDTO size = sizeDao.getSizeByID(rs.getInt("size"));
                    CategoriesDTO cate = cateDao.getCategoryByID(rs.getInt("CategoryID"));
                    BigDecimal price = rs.getBigDecimal("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    
                    dto = new ProductsDTO(
                            Integer.parseInt(id), productName, size, cate, price, quantity, image, status);
                }
                return dto;
            }
            return null;
        } finally{ 
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
             if(con != null){
                con.close();
            }
        }
    }
}
