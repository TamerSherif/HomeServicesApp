package com.example.carl.seg2105project;
import com.example.carl.seg2105project.User;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    //this tests the userType getter method which is used frequently in our app to make sure that it works
    @Test
    public void userTypeGetterTest(){
        String expectedUserType = "unitTestType";
        String expectedEmail = "unitTest@gmail.com";
        User testUser = new User(expectedEmail, expectedUserType);
        String actualUserType = testUser.getAccountType();
        assertEquals("getting service rate failed", expectedUserType,actualUserType);
    }

    //this tests the email address getter method which is used frequently in our app to make sure that it works
    @Test
    public void userEmailGetterTest(){
        String expectedUserType = "unitTestType";
        String expectedEmail = "unitTest@gmail.com";
        User testUser = new User(expectedEmail, expectedUserType);
        String actualEmail = testUser.getEmail();
        assertEquals("getting service rate failed", expectedEmail,actualEmail);
    }

    //this tests the userType setter method which is used frequently in our app to make sure that it works
    @Test
    public void userTypeSetterTest(){
        String expectedUserType = "unitTestType";
        String expectedEmail = "unitTest@gmail.com";
        User testUser = new User(expectedEmail, "wrong");
        testUser.setAccountType(expectedUserType);
        String actualUserType = testUser.getAccountType();
        assertEquals("getting service rate failed", expectedUserType,actualUserType);
    }

    //this tests the email setter method which is used frequently in our app to make sure that it works
    @Test
    public void emailGetterTest(){
        String expectedUserType = "unitTestType";
        String expectedEmail = "unitTest@gmail.com";
        User testUser = new User("wrong@gmail.com", expectedUserType);
        testUser.setEmail(expectedEmail);
        String actualEmail = testUser.getEmail();
        assertEquals("getting service rate failed", expectedEmail,actualEmail);
    }


}
