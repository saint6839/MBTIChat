package com.example.mbti_final;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity.class";
    private FirebaseAuth auth;

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate호출");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() 호출");
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() == null){
            showFirebaseUI();
        } else {
            signInLauncher.launch(new Intent(MainActivity.this, HomeActivity.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showFirebaseUI() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build());


        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .setLogo(R.mipmap.ic_launcher_round)
                .build();
        signInIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        signInLauncher.launch(signInIntent);
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            Log.d(TAG, "onSignInResult success");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            updateUI(user);
            // ...
        } else {
            Log.d(TAG, "onSignInResult failed");
            updateUI(null);
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            Log.d(TAG,"updateUI 성공");
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else{
            Log.d(TAG,"updateUI 실패");
        }
    }
}