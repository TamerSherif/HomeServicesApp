package com.example.carl.seg2105project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ServiceProviderInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{

    EditText EnteredAddress, EnteredPhoneNumber, EnteredBio;
    CheckBox Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
    Map<String, Object> availDict = new HashMap<>();
    Button submitButton;
    String Licensed;

    DatabaseReference reference;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_info);

        EnteredAddress = findViewById(R.id.editText_EnteredAddress);
        EnteredPhoneNumber = findViewById(R.id.editText_EnteredPhoneNumber);
        EnteredBio = findViewById(R.id.editText_EnteredBio);
        submitButton = findViewById(R.id.button_SubmitServiceProviderInfo);
        Sunday = findViewById(R.id.checkBox_Sunday);
        Monday = findViewById(R.id.checkBox_Monday);
        Tuesday = findViewById(R.id.checkBox_Tuesday);
        Wednesday = findViewById(R.id.checkBox_Wednesday);
        Thursday = findViewById(R.id.checkBox_Thursday);
        Friday = findViewById(R.id.checkBox_Friday);
        Saturday = findViewById(R.id.checkBox_Saturday);

        //Sets up the spinner
        Spinner spinner = findViewById(R.id.spinner_Licensed);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Licensed, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_SubmitServiceProviderInfo:
                submitProviderInfoAttempt(view);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Licensed = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }



    // 1) Validates that mandatory fields have been filled, and input is in proper format
    // 2) Stores/Overwrites Service providers info in the database
    // 3) Moves back to the welcome screen
    public void submitProviderInfoAttempt(View view) {

        String enteredAddr = EnteredAddress.getText().toString();
        String enteredPhone = EnteredPhoneNumber.getText().toString();
        String enteredBio = EnteredBio.getText().toString();


        if (enteredAddr.matches("") || enteredPhone.matches("") ) {

            if (enteredAddr.matches("") & enteredPhone.matches("")) {
                Toast.makeText(getApplicationContext(), "You must enter an address and a phone number", Toast.LENGTH_SHORT).show();
            }

            else if (enteredAddr.matches("")){
                Toast.makeText(getApplicationContext(), "You must enter an address", Toast.LENGTH_SHORT).show();
            }

            else if (enteredPhone.matches("")){
                Toast.makeText(getApplicationContext(), "You must enter a phone number", Toast.LENGTH_SHORT).show();
            }
        }
        else {

            if (enteredPhone.length() != 10){
                Toast.makeText(getApplicationContext(), "Please input a correct phone number, must be 10 digits", Toast.LENGTH_SHORT).show();
            }

            else {

                if (Sunday.isChecked()){
                    availDict.put("Sunday",true);
                }
                else{
                    availDict.put("Sunday",false);
                }
                if (Monday.isChecked()){
                    availDict.put("Monday",true);
                }
                else{
                    availDict.put("Monday",false);
                }
                if (Tuesday.isChecked()){
                    availDict.put("Tuesday",true);
                }
                else{
                    availDict.put("Tuesday",false);
                }
                if (Wednesday.isChecked()){
                    availDict.put("Wednesday",true);
                }
                else{
                    availDict.put("Wednesday",false);
                }
                if (Thursday.isChecked()){
                    availDict.put("Thursday",true);
                }
                else{
                    availDict.put("Thursday",false);
                }
                if (Friday.isChecked()){
                    availDict.put("Friday",true);
                }
                else{
                    availDict.put("Friday",false);

                }
                if (Saturday.isChecked()){
                    availDict.put("Saturday",true);
                }
                else{
                    availDict.put("Saturday",false);

                }


                FirebaseUser user = mAuth.getCurrentUser();

                Map<String, Object> serviceProviderUpdates = new HashMap<>();

                serviceProviderUpdates.put("Address",enteredAddr);
                serviceProviderUpdates.put("PhoneNumber",enteredPhone);
                serviceProviderUpdates.put("Bio",enteredBio);
                serviceProviderUpdates.put("Availabilities",availDict);



                reference.child(user.getUid()).updateChildren(serviceProviderUpdates);
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
            }
        }
    }
}
