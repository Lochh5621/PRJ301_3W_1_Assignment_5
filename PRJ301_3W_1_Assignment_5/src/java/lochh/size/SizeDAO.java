/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.size;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lochh.utils.DBConnect;

/**
 *
 * @author win 10
 */
public class SizeDAO implements Serializable{
    
    public List<SizeDTO> getSizeList() 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<SizeDTO> list = null;
        
        try{
        //1.Connect DB
            con=DBConnect.makeConnection();
            if(con!=null){
                //2. Create SQL String
                String sql= "Select * "
                        + "From Size ";
                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                while(rs.next()){
                    int sizeID = rs.getInt("sizeID");
                    String sizeName = rs.getString("sizeName");
                    SizeDTO supplier = new SizeDTO(sizeID, sizeName);
                    
                    if(list == null) {
                        list = new ArrayList<>();
                        
                    }
                    list.add(supplier);
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
    
    public SizeDTO getSizeByID(int id) 
                        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        SizeDTO size = null;
        
        try{
        //1.Connect DB
            con=DBConnect.makeConnection();
            if(con!=null){
                //2. Create SQL String
                String sql= "Select * "
                        + "From Size "
                        + "Where sizeID = ?";
                //3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if(rs.next()){
                    int sizeID = rs.getInt("sizeID");
                    String sizeName = rs.getString("sizeName");
                    size = new SizeDTO(sizeID, sizeName);
                    
                }
                return size;
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
