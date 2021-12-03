/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lochh.customers;

import java.io.Serializable;

/**
 *
 * @author win 10
 */
public class CustomersDTO implements Serializable{
    private int customerID;
    private String username;
    private String password;
    private String contactName;
    private String address;
    private String phone;

    public CustomersDTO(int customerID, String username, String password, String contactName, String address, String phone) {
        this.customerID = customerID;
        this.username = username;
        this.password = password;
        this.contactName = contactName;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
   
}
