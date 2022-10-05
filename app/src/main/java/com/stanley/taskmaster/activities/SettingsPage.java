package com.stanley.taskmaster.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.stanley.taskmaster.R;

import java.util.List;
import java.util.ArrayList;

public class SettingsPage extends AppCompatActivity {
    public static final String Tag = "SettingsPage";
    SharedPreferences sharedPreferences;
    public static final String USER_NAME_TAG = "username";
    public static final String USER_TEAM_TAG = "userTeam";
    List<String> teamNames = null;
    ArrayAdapter<String> adapter;

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
        teamNames = new ArrayList<>();
        setTeamSpinner();
        settingSaveButton();
    }

    @Override
    protected void onResume(){
        super.onResume();

        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    Log.i(Tag, "Teams read successfully!");
                    teamNames.clear();
                    for (Team teams : success.getData()){
                        teamNames.add(teams.getName());
                    }
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },
                failure -> Log.i(Tag, "Did not read team successfully")
        );
    }
    private void settingSaveButton(){
        Button saveButton = SettingsPage.this.findViewById(R.id.editUsername);
        Spinner teamSpinner = findViewById(R.id.settingSpinner);
        saveButton.setOnClickListener(view -> {
            SharedPreferences.Editor editPreference = sharedPreferences.edit();
            String nameEdit = ((TextView) findViewById(R.id.settingsUsername)).getText().toString();
            editPreference.putString(USER_NAME_TAG, nameEdit);
            String chooseTeam = ((String) teamSpinner.getSelectedItem());
            editPreference.putString(USER_TEAM_TAG, chooseTeam);
            editPreference.apply();

            Intent goToMainActivity = new Intent(SettingsPage.this, MainActivity.class);
            goToMainActivity.putExtra(SettingsPage.USER_NAME_TAG, nameEdit);
            startActivity(goToMainActivity);
        });
    }
    private void setTeamSpinner(){
        Spinner userTeamSpinner = findViewById(R.id.settingSpinner);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                teamNames);
        userTeamSpinner.setAdapter(adapter);
    }
}
