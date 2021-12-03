/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.products;

import java.io.Serializable;
import java.math.BigDecimal;
import lochh.categories.CategoriesDTO;
import lochh.size.SizeDTO;

/**
 *
 * @author win 10
 */
public class ProductsDTO implements Serializable{
    private int productID;
    private String productName;
    private SizeDTO size;
    private CategoriesDTO category;
    private BigDecimal price;
    private int quantity;
    private String image;
    private boolean status;

    public ProductsDTO() {
        this.productID = 0;
        this.productName = "";
        this.size = null;
        this.category = null;
        this.price = new BigDecimal(0);
        this.quantity = 0;
        this.image = "";
        this.status = false;
    }

    public ProductsDTO(int productID, String productName, SizeDTO size, CategoriesDTO category, BigDecimal price, int quantity, String image, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.size = size;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public SizeDTO getSize() {
        return size;
    }

    public void setSize(SizeDTO size) {
        this.size = size;
    }

    public CategoriesDTO getCategory() {
        return category;
    }

    public void setCategory(CategoriesDTO category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    
    
}