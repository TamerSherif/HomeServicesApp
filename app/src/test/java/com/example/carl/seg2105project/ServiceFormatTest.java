package com.example.carl.seg2105project;

import com.example.carl.seg2105project.Service;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceFormatTest {
        //this tests that the number of services in our services array is correct as we use it for service providers
        @Test
        public void rateServicesArray(){
            Service[] services;
            Service service1 = new Service("service1", "1");
            Service service2 = new Service("service2", "2");
            services = new Service[]{service1,service2};

            StringBuilder serv = new StringBuilder();

            for (int j = 0; j < services.length; j++) {
                serv.append(services[j].getServiceName());
                if (j == (services.length - 1)) {
                    serv.append(".");
                } else {
                    serv.append(", ");
                }
            }
            String actualOutput = "You are registered for the following services: " + serv.toString();
            String expectedOutput = "You are registered for the following services: service1, service2.";
            assertEquals("getting service rate failed", expectedOutput, actualOutput);
        }

        //this tests that the availabilities array gets formated properly for the service provider info display
        @Test
        public void rateAvailabilityArray(){
            String[] availability;
            availability = new String[]{"Monday", "Wednesday"};
            StringBuilder days = new StringBuilder();
            days.append("[");

            for (int i = 0; i < availability.length; i++) {
                if (availability[i] != null) {
                    days.append(availability[i].substring(0,2));
                    if (i < availability.length - 1) {
                        days.append(", ");
                    } else {
                        days.append("]");
                    }
                }
            }
            String actualOutput = "You are available on the following days: " + days.toString();
            String expectedOutput = "You are available on the following days: [Mo, We]";
            assertEquals("service availability format failed", expectedOutput, actualOutput);
        }
    }
