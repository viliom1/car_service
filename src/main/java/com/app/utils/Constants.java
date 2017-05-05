package com.app.utils;

/**
 * Created by vilimir on 09.04.17.
 */
public class Constants {
    public static final String ROLE_CLIENT = "ROLE_CLIENT";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MECHANIC = "ROLE_MECHANIC";
    public static final String ERROR_INVALID_CREDENTIALS = "Invalid credentials!";
    public static final String ERROR_INVALID_PASSWORD = "Password must be between 5 and 32 characters and must contain atlest 1 digit and 1 alphabetical.";
    public static final String ERROR_INVALID_EMAIL = "E-mail is not valid!";
    public static final String ERROR_FIRST_NAME = "First name must be at least 3 characters long!";
    public static final String ERROR_LAST_NAME = "Last name must be at least 3 characters long!";
    public static final int REPAIR_IN_PROCESS = -1;
    public static final int REPAIR_NO_VOTE = 0;
    public static final int REPAIR_UP_VOTE = 1;
    public static final int REPAIR_DOWN_VOTE = 2;


}
