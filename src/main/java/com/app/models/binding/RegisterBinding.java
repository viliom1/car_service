package com.app.models.binding;

import com.app.utils.Constants;
import com.app.utils.annotatitons.IsPasswordMatching;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by vilimir on 03.04.17.
 */
@IsPasswordMatching
public class RegisterBinding {
    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$"
            ,message = Constants.ERROR_INVALID_EMAIL)
    private String username;
    @Size(min = 3, message = Constants.ERROR_FIRST_NAME)
    private String firstName;
    @Size(min = 3, message = Constants.ERROR_LAST_NAME)
    private String lastName;
    @Pattern(regexp = "(?=.*\\d.*)(?=.*\\w.*)[\\w\\d]{5,32}",message = Constants.ERROR_INVALID_PASSWORD)
    private String password;
    private String confirmPassword;
    public RegisterBinding() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
