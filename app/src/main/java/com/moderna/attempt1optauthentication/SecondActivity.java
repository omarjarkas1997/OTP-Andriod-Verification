package com.moderna.attempt1optauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    public CardView verifyOptButton;
    public TextInputEditText optInput;
    public TextView enterOTPBelow;

    // 1. Declaring an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        verifyOptButton = findViewById(R.id.verifyOptButton);
        optInput = findViewById(R.id.optInput);
        enterOTPBelow = findViewById(R.id.enterOTPBelow);


        // 2. Initializing a FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        phoneNumber = "+1 650-555-1997";
        enterOTPBelow.setText(phoneNumber);
        Toast.makeText(SecondActivity.this, "Enter Your Phone Number",Toast.LENGTH_LONG).show();


        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);

                Toast.makeText(SecondActivity.this, "onVerificationCompleted",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SecondActivity.this, StoryPageActivity.class);
                startActivity(intent);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.w(TAG, "Invalid request", e);
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Log.w(TAG, "The SMS quota for the project has been exceeded", e);
                }
                Toast.makeText(SecondActivity.this, "onVerificationFailed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);

                Toast.makeText(SecondActivity.this, "onCodeSent",Toast.LENGTH_LONG).show();

                Toast.makeText(SecondActivity.this, "verificationId "+verificationId,Toast.LENGTH_LONG).show();
            }
        };


        sendVerificationCodeToUser(phoneNumber, mCallbacks);

    }

    private void sendVerificationCodeToUser(String phoneNumber, PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        Toast.makeText(SecondActivity.this, "sendVerificationCodeToUser",Toast.LENGTH_LONG).show();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                                        .setPhoneNumber(phoneNumber)
                                        .setTimeout(60L, TimeUnit.SECONDS)
                                        .setActivity(this)
                                        .setCallbacks(mCallbacks)
                                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

}