package com.moderna.attempt1optauthentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneAuthActivity extends AppCompatActivity {

    private static final String TAG = "PhoneAuthActivity";


    // 1. Declaring an instance of FirebaseAuth
    private FirebaseAuth mAuth;



    public CardView sendOTPButton;
    private TextInputEditText phoneAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);


        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        sendOTPButton = findViewById(R.id.sendOTPButton);
        phoneAuth = findViewById(R.id.phoneAuthButton);

        String phoneNumber = phoneAuth.getText().toString();

        sendOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneAuth.setText(phoneNumber+"Help");
                goToSecondActivity(phoneNumber);
            }
        });

    }


    public void goToSecondActivity(String phoneNumber) {
        Intent intent = new Intent(PhoneAuthActivity.this, SecondActivity.class);
        intent.putExtra("phoneNumber", phoneNumber);
        startActivity(intent);
    }
}