package com.example.carl.seg2105project;
import com.example.carl.seg2105project.Service;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ServiceTest {


    //this tests the service rate getter method which is used frequently in our app to make sure that it works
    @Test
    public void rateGetterTest(){
        String expectedName = "unitTestService";
        String expectedRate = "20";
        Service testService = new Service(expectedName, expectedRate);
        String actualRate = testService.getServiceRate();
        assertEquals("getting service rate failed", expectedRate,actualRate);
    }

    //this tests the service rate setter method which is used frequently in our app to make sure that it works
    @Test
    public void rateSetterTest(){
        String expectedName = "unitTestService";
        String expectedRate = "20";
        Service testService = new Service(expectedName, "30");
        testService.setServiceRate(expectedRate);
        String actualRate = testService.getServiceRate();
        assertEquals("getting service rate failed", expectedRate,actualRate);
    }

    //this tests the service name getter method which is used frequently in our app to make sure that it works
    @Test
    public void nameGetterTest(){
        String expectedName = "unitTestService";
        String expectedRate = "20";
        Service testService = new Service(expectedName, expectedRate);
        String actualName = testService.getServiceName();
        assertEquals("getting service name failed", expectedName,actualName);
    }

    //this tests the service name setter method which is used frequently in our app to make sure that it works
    @Test
    public void nameSetterTest(){
        String expectedName = "unitTestService";
        String expectedRate = "20";
        Service testService = new Service("test", expectedRate);
        testService.setServiceName(expectedName);
        String actualName = testService.getServiceName();
        assertEquals("getting service name failed", expectedName, actualName);
    }



}
