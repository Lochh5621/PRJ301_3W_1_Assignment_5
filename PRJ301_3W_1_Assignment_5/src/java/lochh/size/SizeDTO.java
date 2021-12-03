/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.size;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import lochh.products.ProductsDAO;
import lochh.products.ProductsDTO;

/**
 *
 * @author win 10
 */
public class SizeDTO implements Serializable{
    private int sizeID;
    private String sizeName;

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public SizeDTO(int sizeID, String sizeName) {
        this.sizeID = sizeID;
        this.sizeName = sizeName;
    }

    
    
}
