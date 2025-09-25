package com.project.userservice.session;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession implements Serializable {
	private static final long serialVersionUID = 1L;
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userPhone;
    
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserPassword() {
        return userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserPhone() {
        return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    public boolean isLoggedIn() {
        return userId != null;
    }
    
    public void clearSession() {
        this.userId = null;
        this.userName = null;
        this.userEmail = null;
        this.userPassword=null;
        this.userPhone = null;
    }
}