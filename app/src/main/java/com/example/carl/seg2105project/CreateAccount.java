package com.example.carl.seg2105project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    private Button submitButton;
    private EditText inputEmail, inputPassword;
    private String accountType;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        inputEmail = findViewById(R.id.editText_CreateUsername);
        inputPassword = findViewById(R.id.editText_CreatePassword);
        submitButton = findViewById(R.id.button_SubmitAccountInfo);
        submitButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        //Sets up the spinner
        Spinner spinner = findViewById(R.id.spinner_AccountType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.AccountTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("account", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("account", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        accountType = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_SubmitAccountInfo:
                submitAccountAttempt(view);
                break;
        }
    }

    /*
        This function should check that both the edit text fields are VALID
        Then it should attempt to push the account details to FIREBASE
        It should not allow more than one admin account to be created
        After it successfully completes all of these things it should return the user to the login screen.
     */
    public void submitAccountAttempt(View view) {
        String email = inputEmail.getText().toString();
        String pass = inputPassword.getText().toString();
        Log.d("account", "createAccount:" + email);
        if (!validateForm()) {
            return;
        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();


        System.out.println(mAuth.getCurrentUser());

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Log.d("account", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.e("account", "onComplete: Failed=" + task.getException().getMessage());
                            Toast.makeText(CreateAccount.this, "Register failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else {
                            FirebaseUser user = mAuth.getCurrentUser();
                            User userInfo = new User(inputEmail.getText().toString(), accountType);
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            ref.child("users").child(user.getUid()).setValue(userInfo);


                            Intent intent = new Intent(CreateAccount.this, LoginActivity.class);
                            CreateAccount.this.startActivity(intent);
                            finish();
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = inputEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Required.");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        String password = inputPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Required.");
            valid = false;
        } else {
           inputPassword.setError(null);
        }

        return valid;
    }

}
