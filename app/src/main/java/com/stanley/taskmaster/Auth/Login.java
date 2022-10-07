package com.stanley.taskmaster.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.stanley.taskmaster.R;
import com.stanley.taskmaster.activities.MainActivity;

public class Login extends AppCompatActivity {
    public static final String Tag = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpLogin();
    }
    private void setUpLogin(){
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(Verify.VERIFY_ACCOUNT_EMAIL_TAG);
        EditText emailET = findViewById(R.id.emailEditText);
        emailET.setText(userEmail);
        findViewById(R.id.LoginButton).setOnClickListener(view -> {
            String userSelectedEmail = emailET.getText().toString();
            String userPassword = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

            Amplify.Auth.signIn(
                    userSelectedEmail,
                    userPassword,
                    success -> {
                        Log.i(Tag, "Login succeeded: " + success);
                        Intent goToProductListIntent = new Intent(Login.this, MainActivity.class);
                        startActivity(goToProductListIntent);
                    },
                    failure -> {
                        Log.i(Tag, "Login failed: " + failure);
                        runOnUiThread(() -> Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show());
                    }
            );
        });
    }
}