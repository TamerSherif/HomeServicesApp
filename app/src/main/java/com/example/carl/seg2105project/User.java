package com.example.carl.seg2105project;

public class User {
    public String email;
    public String accountType;

    public User(){

    }

    public User(String email, String accountType) {
        this.email = email;
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String type){
        this.accountType = type;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
}
