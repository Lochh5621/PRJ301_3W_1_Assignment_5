/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.cart;

import java.io.Serializable;
import lochh.products.ProductsDTO;

/**
 *
 * @author win 10
 */
public class ShoppingCartItem implements Serializable{
    private ProductsDTO product;
    private int quantity;

    public ProductsDTO getProduct() {
        return product;
    }

    public void setProduct(ProductsDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingCartItem(ProductsDTO product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
