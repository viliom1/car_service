package com.app.models.binding;

/**
 * Created by vilimir on 28.04.17.
 */
public class ChangePasswordBinding {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    public ChangePasswordBinding() {
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

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
