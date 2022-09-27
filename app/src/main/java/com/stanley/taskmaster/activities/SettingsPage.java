package com.stanley.taskmaster.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stanley.taskmaster.R;

public class SettingsPage extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String USER_NAME_TAG = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString(USER_NAME_TAG, "");

        if(!userName.isEmpty()){
            EditText editedUsername = findViewById(R.id.settingsEditUsername);
            editedUsername.setText(userName);
        }
            settingSaveButton();
    }
    private void settingSaveButton(){
        Button saveButton = SettingsPage.this.findViewById(R.id.editUsername);
        saveButton.setOnClickListener(view -> {
            SharedPreferences.Editor editPreference = sharedPreferences.edit();
            String nameEdit = ((TextView) findViewById(R.id.settingsUsername)).getText().toString();
            editPreference.putString(USER_NAME_TAG, nameEdit);
            editPreference.apply();
        });
    }
}
