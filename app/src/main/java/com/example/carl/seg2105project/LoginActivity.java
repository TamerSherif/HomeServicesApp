package com.example.carl.seg2105project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button lButton, cButton;
    private EditText username, password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lButton = findViewById(R.id.button_Login);
        lButton.setOnClickListener(this);
        cButton = findViewById(R.id.button_CreateAccount);
        cButton.setOnClickListener(this);
        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        mAuth = FirebaseAuth.getInstance();
    }

    //Defines the functions of the buttons in this activity
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_CreateAccount:
                goToCreateAccountActivity(view);
                break;
            case R.id.button_Login:
                loginAttempt(view);
                break;
        }
    }

    //Switches to the CreateAccount activity
    public void goToCreateAccountActivity(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }



    /*Attempts a login:
    First validates text fields
    Then checks that the username matches with password in database
    Then switches to welcome screen if successful
     */
    public void loginAttempt(View view) {
        mAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //Toast.makeText(getApplicationContext(),"this is the current user data" + user.toString(), Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
