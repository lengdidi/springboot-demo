package com.ld.bean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1453161141461511148L;
    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + ", passWord=" + passWord + "]";
    }

}
