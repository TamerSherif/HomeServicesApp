package com.example.carl.seg2105project;

import android.widget.Toast;

public class Service {

    private String serviceName;
    private String serviceRate;

    public Service() { //constructor, may not be need
    }

    public Service(String name, String rate) {
            serviceName = name;
            serviceRate = rate;



    }

    public void setServiceName(String name){

            this.serviceName = name;


    }

    public String getServiceName(){
        return this.serviceName;
    }

    public void setServiceRate(String rate){
        this.serviceRate = rate;
    }

    public String getServiceRate(){
        return this.serviceRate;
    }
}
