package com.caliope.data.entities;

import org.springframework.stereotype.Component;

@Component
public class OldNewPassword {

    private String oldPassword;
    private String newPassword;

    public OldNewPassword() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
