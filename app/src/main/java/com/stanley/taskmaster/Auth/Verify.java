package com.stanley.taskmaster.Auth;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.stanley.taskmaster.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class Verify extends AppCompatActivity {
    public static final String Tag = "VerifyAccountActivity";
    public static final String VERIFY_ACCOUNT_EMAIL_TAG = "Verify_Account_Email_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        setUpVerify();
    }
    private void setUpVerify(){
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(SignUp.SIGNUP_EMAIL_TAG);
        findViewById(R.id.verifyButton).setOnClickListener(view -> {
            String verificationCode = ((EditText) findViewById(R.id.verifyCode)).getText().toString();

            Amplify.Auth.confirmSignUp(
                    userEmail,
                    verificationCode,
                    success -> {
                        Log.i(Tag, "Verification succeeded: " + success.toString());
                        Intent goToLogInActivity = new Intent(Verify.this, Login.class);
                        goToLogInActivity.putExtra(VERIFY_ACCOUNT_EMAIL_TAG, userEmail);
                        startActivity(goToLogInActivity);
                    },
                    failure -> {
                        Log.i(Tag, "Verification failed with username: " + "ed@codefellows.com" + " with this message: " + failure.toString());
                        runOnUiThread(() -> Toast.makeText(Verify.this, "Verify account failed!", Toast.LENGTH_SHORT));}
            );
        });
    }
}