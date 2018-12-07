package com.example.carl.seg2105project;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

//         ___________________________
//        |                           |
//        | ABANDON ALL HOPE          |
//        |       YE WHO ENTER HERE   |
//        |___________________________|
//                     |
//                     |

public class serviceProviderCondenser {
    private String address, bio, phoneNumber, email;
    private boolean[] availabilities;
    private ArrayList<String> services;
    public static String tempA, tempB, tempP, tempE;
    public static boolean[] tempAv;
    public static ArrayList<String> tempS;


    public serviceProviderCondenser(String address, boolean[] availabilities, String bio, String phoneNumber, String email, ArrayList<String> services) {
        this.address = address;
        this.availabilities = availabilities;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.services = services;
    }

    public serviceProviderCondenser () {
        this.address = null;
        this.availabilities = new boolean[0];
        this.bio = null;
        this.phoneNumber = null;
        this.email = null;
        this.services = new ArrayList<>();
    }

    public String formatSPInfo(boolean[] availability, ArrayList<String> services) {
        String a, s;
        String[] daysOfWeek = new String[]{"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
        boolean x = true;
        boolean y = true;

        try{
            x  = (availability.length == 0);
        } catch (NullPointerException e) {
            System.out.println("Guess I'll die then ¯|_(ツ)_/¯");
        }

        if(x) {
            a = "Currently not available on any day!";
        } else {
            StringBuilder days = new StringBuilder();
            days.append("[ ");

            for (int i = 0; i < availability.length; i++) {
                if(availability[i]) {
                    days.append(daysOfWeek[i]);
                    days.append(" ");
                }
            }
            days.append("]");
            a = "Available on the following days: " + days.toString();
        }

        try{
            y  = services.isEmpty();
        } catch (NullPointerException e) {
            System.out.println("Guess I'll die then ¯|_(ツ)_/¯");
        }


        if(y) {
            s = "Not registered for any services!";
        } else {
            StringBuilder serv = new StringBuilder();

            for (int j = 0; j < services.size(); j++) {
                serv.append(services.get(j));
                if (j == (services.size() - 1)) {
                    serv.append(".");
                } else {
                    serv.append(", ");
                }
            }
            s = "Registered for the following services: " + serv.toString();

        }

        return a + "\n" + s;
    }

    public void setEqualTo (serviceProviderCondenser toBeCopiedTo, serviceProviderCondenser toBeCopiedFrom) {
        toBeCopiedTo.setServices(toBeCopiedFrom.getServices());
        toBeCopiedTo.setPhoneNumber(toBeCopiedFrom.getPhoneNumber());
        toBeCopiedTo.setBio(toBeCopiedFrom.getBio());
        toBeCopiedTo.setAvailabilities(toBeCopiedFrom.getAvailabilities());
        toBeCopiedTo.setAddress(toBeCopiedFrom.getAddress());
        toBeCopiedTo.setEmail(toBeCopiedFrom.getEmail());
    }

    public String getAddress() {
        return this.address;
    }

    public boolean[] getAvailabilities() {
        return this.availabilities;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public ArrayList<String> getServices() {
        return this.services;
    }

    public  serviceProviderCondenser getServiceProviderProfile(final String email, FirebaseDatabase db) {
        DatabaseReference ref = db.getReference("users");
        final serviceProviderCondenser returned = new serviceProviderCondenser();
        ref.addValueEventListener(new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnap : dataSnapshot.getChildren()) {
                    User userInfo = userSnap.getValue(User.class);
                    if (userInfo.getEmail().equals(email)) {
                       Object address = userSnap.child("Address").getValue();
                       System.out.println(address.toString());
                       Object bio = userSnap.child("Bio").getValue();
                        System.out.println(bio.toString());
                       Object phoneNumber = userSnap.child("PhoneNumber").getValue();
                        System.out.println(phoneNumber.toString());
                       boolean[] availability = new boolean[7];
                       ArrayList<String> services = new ArrayList<String>();
                       for (DataSnapshot availabilities : userSnap.getChildren()) {
                           try {
                               availability[1] = availabilities.child("Monday").getValue(Boolean.class);
                               availability[2] = availabilities.child("Tuesday").getValue(Boolean.class);
                               availability[3] = availabilities.child("Wednesday").getValue(Boolean.class);
                               availability[4] = availabilities.child("Thursday").getValue(Boolean.class);
                               availability[5] = availabilities.child("Friday").getValue(Boolean.class);
                               availability[6] = availabilities.child("Saturday").getValue(Boolean.class);
                               availability[0] = availabilities.child("Sunday").getValue(Boolean.class);

                           } catch (java.lang.NullPointerException e) {
                               System.out.println();
                           }
                       }
                       for(DataSnapshot servicesOffered : userSnap.child("currentServices").getChildren()) {
                           String s = servicesOffered.getValue(String.class);
                           services.add(s);
                        }

                        returned.tempA = address.toString();
                       returned.tempAv = availability;
                       returned.tempB = bio.toString();
                       returned.tempP = phoneNumber.toString();
                        returned.tempE = email;
                        returned.tempS = services;

                        serviceProviderCondenser test = new serviceProviderCondenser(tempA, tempAv, tempB, tempP, tempE, tempS);
                        System.out.println(test);
                        System.out.flush();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return new serviceProviderCondenser(tempA, tempAv, tempB, tempP, tempE, tempS);
    }


    public String getEmail() {
        return this.email;
    }

    public String getBio(){
        return this.bio;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvailabilities(boolean[] availabilities) {
        this.availabilities = availabilities;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public String toString() {
       String toReturn = "Address: " + address + "\n" +
               "Bio: " + bio + "\n" +
               "PhoneNumber: " + phoneNumber + "\n" +
               "Email: " + email + "\n" +
               formatSPInfo(availabilities, services);

        return toReturn;
    }
}
