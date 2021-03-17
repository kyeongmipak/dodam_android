package com.android.dodam.SqLite;

import java.sql.Blob;

public class User {

    // Field
    private int userNo;
    private String userName;
    private String userBirth;
    private Blob userImage;

    // Constructor
    public User(int userNo, String userName, String userBirth, Blob userImage) {
        this.userNo = userNo;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userImage = userImage;
    }


    // Getter & Setter
    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public Blob getUserImage() {
        return userImage;
    }

    public void setUserImage(Blob userImage) {
        this.userImage = userImage;
    }
}
