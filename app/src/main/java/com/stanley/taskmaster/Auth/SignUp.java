package com.stanley.taskmaster.Auth;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.stanley.taskmaster.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    public static final String SIGNUP_EMAIL_TAG = "Signup_Email_Tag";
    public static final String Tag = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setUpSignUp();
    }
    private void setUpSignUp(){

        Button signUpSubmitButton = findViewById(R.id.signUpSubmit);
                signUpSubmitButton.setOnClickListener(view -> {
            String userEmail = ((EditText)findViewById(R.id.signUpEmail)).getText().toString();
            String userPassword = ((EditText) findViewById(R.id.signUpPassword)).getText().toString();
            Amplify.Auth.signUp(
                    userEmail,
                    userPassword,
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.email(), userEmail)
                            .build(),
                    success -> {
                        Log.i(Tag, "Signup success! " + success);
                        Intent goToVerifyActivity = new Intent(SignUp.this, Verify.class);
                        goToVerifyActivity.putExtra(SIGNUP_EMAIL_TAG, userEmail);
                        startActivity(goToVerifyActivity);
                    },
                    failure -> {
                        Log.i(Tag,"Signup failed with username " + userEmail + "with message: " + failure);
                        runOnUiThread(() -> Toast.makeText(SignUp.this, "Signup Failed!", Toast.LENGTH_SHORT).show());
                    }
            );

        });

    }

}