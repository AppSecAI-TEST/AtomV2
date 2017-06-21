package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/6/16.
 */

public class LoginModel {
    private String username;
    private String password;

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
