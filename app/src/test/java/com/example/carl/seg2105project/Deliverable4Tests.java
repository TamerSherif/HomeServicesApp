package com.example.carl.seg2105project;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Deliverable4Tests {
    //these tests our new getters for our new class serviceProviderCondenser
    @Test
    public void gettingAddressTest(){
        String expectedAddress = "correct address!";
        boolean[] expectedAvailabilities = new boolean[0];
        String expectedBio = "my bio!";
        String expectedPhoneNumber = "6134567890";
        String expectedEmail = "myEmail@gmail.com";
        ArrayList<String> expectedServices = new ArrayList<>();

        serviceProviderCondenser test = new serviceProviderCondenser(expectedAddress,expectedAvailabilities,expectedBio,expectedPhoneNumber,expectedEmail,expectedServices);

        String actualAddress = test.getAddress();

        assertEquals("getting service rate failed", expectedAddress,actualAddress);
    }

    @Test
    public void gettingAvailabilitiesTest(){
        String expectedAddress = "correct address!";
        boolean[] expectedAvailabilities = new boolean[0];
        String expectedBio = "my bio!";
        String expectedPhoneNumber = "6134567890";
        String expectedEmail = "myEmail@gmail.com";
        ArrayList<String> expectedServices = new ArrayList<>();

        serviceProviderCondenser test = new serviceProviderCondenser(expectedAddress,expectedAvailabilities,expectedBio,expectedPhoneNumber,expectedEmail,expectedServices);

        boolean[] actualAvailabilities = test.getAvailabilities();

        assertEquals("getting service rate failed", expectedAvailabilities,actualAvailabilities);
    }

    @Test
    public void gettingPhoneNumberTest(){
        String expectedAddress = "correct address!";
        boolean[] expectedAvailabilities = new boolean[0];
        String expectedBio = "my bio!";
        String expectedPhoneNumber = "6134567890";
        String expectedEmail = "myEmail@gmail.com";
        ArrayList<String> expectedServices = new ArrayList<>();

        serviceProviderCondenser test = new serviceProviderCondenser(expectedAddress,expectedAvailabilities,expectedBio,expectedPhoneNumber,expectedEmail,expectedServices);

        String actualPhoneNumber = test.getPhoneNumber();

        assertEquals("getting service rate failed", expectedPhoneNumber,actualPhoneNumber);
    }

    @Test
    public void gettingServicesTest(){
        String expectedAddress = "correct address!";
        boolean[] expectedAvailabilities = new boolean[0];
        String expectedBio = "my bio!";
        String expectedPhoneNumber = "6134567890";
        String expectedEmail = "myEmail@gmail.com";
        ArrayList<String> expectedServices = new ArrayList<>();

        serviceProviderCondenser test = new serviceProviderCondenser(expectedAddress,expectedAvailabilities,expectedBio,expectedPhoneNumber,expectedEmail,expectedServices);

        ArrayList<String> actualServices = test.getServices();

        assertEquals("getting service rate failed", expectedServices,actualServices);
    }

    @Test
    public void gettingBioTest(){
        String expectedAddress = "correct address!";
        boolean[] expectedAvailabilities = new boolean[0];
        String expectedBio = "my bio!";
        String expectedPhoneNumber = "6134567890";
        String expectedEmail = "myEmail@gmail.com";
        ArrayList<String> expectedServices = new ArrayList<>();

        serviceProviderCondenser test = new serviceProviderCondenser(expectedAddress,expectedAvailabilities,expectedBio,expectedPhoneNumber,expectedEmail,expectedServices);

        String actualBio = test.getBio();

        assertEquals("getting service rate failed", expectedBio,actualBio);
    }

    @Test
    public void gettingEmailTest(){
        String expectedAddress = "correct address!";
        boolean[] expectedAvailabilities = new boolean[0];
        String expectedBio = "my bio!";
        String expectedPhoneNumber = "6134567890";
        String expectedEmail = "myEmail@gmail.com";
        ArrayList<String> expectedServices = new ArrayList<>();

        serviceProviderCondenser test = new serviceProviderCondenser(expectedAddress,expectedAvailabilities,expectedBio,expectedPhoneNumber,expectedEmail,expectedServices);

        String actualEmail = test.getEmail();

        assertEquals("getting service rate failed", expectedEmail,actualEmail);
    }

    @Test
    public void settingEmailTest(){
        serviceProviderCondenser test = new serviceProviderCondenser();

        String expectedEmail = "newEmail@gmail.com";
        test.setEmail("newEmail@gmail.com");
        String actualEmail = test.getEmail();

        assertEquals("getting service rate failed", expectedEmail,actualEmail);
    }

    @Test
    public void settingBioTest(){
        serviceProviderCondenser test = new serviceProviderCondenser();

        String expectedBio = "new Bio!";
        test.setBio("new Bio!");
        String actualBio = test.getBio();

        assertEquals("getting service rate failed", expectedBio,actualBio);
    }

    @Test
    public void settingAddressTest(){
        serviceProviderCondenser test = new serviceProviderCondenser();

        String expectedAddress = "new Address!";
        test.setAddress("new Address!");
        String actualAddress = test.getAddress();

        assertEquals("getting service rate failed", expectedAddress,actualAddress);
    }
}