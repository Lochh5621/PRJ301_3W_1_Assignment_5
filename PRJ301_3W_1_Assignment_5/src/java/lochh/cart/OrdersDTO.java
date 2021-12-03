/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lochh.customers.CustomersDTO;

/**
 *
 * @author win 10
 */
public class OrdersDTO implements Serializable{
    private int id;
    private CustomersDTO customer;
    private BigDecimal totalMoney;
    private Date date;
    private List<ShoppingCartItem> cart;

    public OrdersDTO() {
        this.id = 0;
        this.customer = null;
        this.totalMoney = null;
        this.date = null;
        this.cart = new ArrayList<>();
    }

    public OrdersDTO(int id, CustomersDTO customer, BigDecimal totalMoney, Date date, List<ShoppingCartItem> cart) {
        this.id = id;
        this.customer = customer;
        this.totalMoney = totalMoney;
        this.date = date;
        this.cart = cart;
    }

    public List<ShoppingCartItem> getCart() {
        return cart;
    }

    public void setCart(List<ShoppingCartItem> cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomersDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomersDTO customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
