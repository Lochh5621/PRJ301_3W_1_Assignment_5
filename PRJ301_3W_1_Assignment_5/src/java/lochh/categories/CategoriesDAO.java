/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.categories;

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
public class CategoriesDAO {
    public CategoriesDTO getCategoryByID(int id) 
                        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CategoriesDTO category = null;
        
        try{
        //1.Connect DB
            con=DBConnect.makeConnection();
            if(con!=null){
                //2. Create SQL String
                String sql= "Select * "
                        + "From Categories "
                        + "Where categoryID = ?";
                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if(rs.next()){
                    int categoryID = rs.getInt("categoryID");
                    String categoryName = rs.getString("categoryName");
                    category = new CategoriesDTO(categoryID, categoryName);
                    
                }
                return category;
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
}
