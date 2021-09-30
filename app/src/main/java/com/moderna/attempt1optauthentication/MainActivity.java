package com.moderna.attempt1optauthentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    public CardView registerButton;
    public CardView signIn;
    public CardView phoneAuthButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = findViewById(R.id.register);
        signIn = findViewById(R.id.signIn);
        phoneAuthButton = findViewById(R.id.phoneAuthButton);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisterActivity();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignInActivity();
            }
        });

        phoneAuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPhoneAuthActivity();
            }
        });
    }

    private void goToSignInActivity() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void goToRegisterActivity() {
        Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
        startActivity(intent);
    }


    public void goToPhoneAuthActivity() {
        Intent intent = new Intent(MainActivity.this, PhoneAuthActivity.class);
        startActivity(intent);
    }

}