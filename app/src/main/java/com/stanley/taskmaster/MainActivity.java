package com.stanley.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        mainActivityButton1();
        mainActivityButton2();
        mainActivityButton3();
//        mainActivitySettingsButton();
    }


    @Override
    protected void onResume() {
        super.onResume();
        String userName = sharedPreferences.getString(SettingsPage.USER_NAME_TAG, "no username");
        TextView editedUsername = findViewById(R.id.textView2);
        editedUsername.setText(userName + "'s Tasks");
    }
    private void mainActivityButton1 () {
            Button taskOneButton = MainActivity.this.findViewById(R.id.mainActivityButton1);

            taskOneButton.setOnClickListener(view -> {
                Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailsPage.class);
                String buttonText = ((Button)view).getText().toString();
                goToTaskDetailPage.putExtra("taskButton", buttonText);
                startActivity(goToTaskDetailPage);
            });
            };
    private void mainActivityButton2 () {
        Button taskOneButton = MainActivity.this.findViewById(R.id.mainActivitybutton2);

        taskOneButton.setOnClickListener(view -> {
            Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailsPage.class);
            String buttonText = ((Button)view).getText().toString();
            goToTaskDetailPage.putExtra("taskButton", buttonText);
            startActivity(goToTaskDetailPage);
        });
    };

    private void mainActivityButton3 () {
        Button taskOneButton = MainActivity.this.findViewById(R.id.mainActivitybutton3);

        taskOneButton.setOnClickListener(view -> {
            Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailsPage.class);
            String buttonText = ((Button)view).getText().toString();
            goToTaskDetailPage.putExtra("taskButton", buttonText);
            startActivity(goToTaskDetailPage);
        });
    };
        }
