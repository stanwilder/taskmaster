package com.stanley.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stanley.taskmaster.Auth.Login;
import com.stanley.taskmaster.Auth.SignUp;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.stanley.taskmaster.R;
import com.stanley.taskmaster.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TASK_NAME_EXTRA_TAG = "taskName";
    public static final String Tag = "MainActivityTag";
    SharedPreferences sharedPreferences;
    List<Task> taskList = new ArrayList<>();
    RecyclerViewAdapter adapter;
    public AuthUser currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        recyclerViewSetup();
        setUpAddTaskBttn();
        setSettingsButton();
        SetUpAuthButtons();

    }


    @Override
    protected void onResume() {
        super.onResume();
        String userName = sharedPreferences.getString(SettingsPage.USER_NAME_TAG, "no username");
        TextView editedUsername = findViewById(R.id.textView2);
        editedUsername.setText(userName + "'s Tasks");

        Amplify.API.query(
                ModelQuery.list(Task.class),
        success -> {
            Log.i(Tag, "Read successfully!");
            taskList.clear();
            for(Task database : success.getData()){
                taskList.add(database);
            }
            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
            });
        },
                failure -> Log.i(Tag, "Task read Unsuccessfully"));
    }
    private void recyclerViewSetup(){
        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
        adapter = new RecyclerViewAdapter(taskList, this);
        recyclerView.setAdapter(adapter);

    }
    private void setUpAddTaskBttn(){
        findViewById(R.id.addTaskButton).setOnClickListener(view -> {
            Intent goToAddTaskPage = new Intent(MainActivity.this, AddTask.class);
            startActivity(goToAddTaskPage);
        });
    }
    private void setSettingsButton(){
        findViewById(R.id.settingButton).setOnClickListener(view -> {
            Intent goToSettingsPage = new Intent(MainActivity.this, SettingsPage.class);
            startActivity(goToSettingsPage);
        });
    }

    private void SetUpAuthButtons(){
        Button signUpButton = findViewById(R.id.SignUpButton);
        Button loginButton = findViewById(R.id.LoginButton);

        if (currentUser == null) {
            loginButton.setVisibility(View.VISIBLE);
            signUpButton.setVisibility(View.VISIBLE);
        } else {
            loginButton.setVisibility(View.INVISIBLE);
            signUpButton.setVisibility(View.INVISIBLE);
        }
        loginButton.setOnClickListener(view -> {
            Intent goToLoginActivity = new Intent(MainActivity.this, Login.class);
            startActivity(goToLoginActivity);
        });

        signUpButton.setOnClickListener(view -> {
            Intent goToSignUpActivity = new Intent(MainActivity.this, SignUp.class);
            startActivity(goToSignUpActivity);
        });
    }
}
//  tasks.add(new TaskModel("task 1", "task", TaskModel.StateEnum.NEW));
//        tasks.add(new TaskModel("task 2","task", TaskModel.StateEnum.IN_PROGRESS));
//        tasks.add(new TaskModel("task 3", "task", TaskModel.StateEnum.COMPLETED));
//        tasks.add(new TaskModel("task 4", "task", TaskModel.StateEnum.NEW));