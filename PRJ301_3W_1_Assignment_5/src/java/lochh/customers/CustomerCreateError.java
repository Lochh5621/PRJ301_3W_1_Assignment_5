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
public class CustomerCreateError implements Serializable{
    private String usernameLengthError;
    private String passwordLengthError;
    private String confirmNotMatch;
    private String contactNameLengthError;
    private String addressLengthError;
    private String phoneLengthError;
    private String usernameIsExisted;

    public CustomerCreateError(String usernameLengthError, String passwordLengthError, String confirmNotMatch, String contactNameLengthError, String addressLengthError, String phoneLengthError, String usernameIsExisted) {
        this.usernameLengthError = usernameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.confirmNotMatch = confirmNotMatch;
        this.contactNameLengthError = contactNameLengthError;
        this.addressLengthError = addressLengthError;
        this.phoneLengthError = phoneLengthError;
        this.usernameIsExisted = usernameIsExisted;
    }

    public CustomerCreateError() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getContactNameLengthError() {
        return contactNameLengthError;
    }

    public void setContactNameLengthError(String contactNameLengthError) {
        this.contactNameLengthError = contactNameLengthError;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
