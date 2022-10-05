package com.stanley.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.stanley.taskmaster.R;
import com.stanley.taskmaster.adapter.RecyclerViewAdapter;
import com.stanley.taskmaster.models.TaskModel;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TASK_NAME_EXTRA_TAG = "taskName";
    public static final String Tag = "MainActivityTag";
    SharedPreferences sharedPreferences;
    List<Task> taskList = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        recyclerViewSetup();
        setUpAddTaskBttn();
        setSettingsButton();

//        Team team1 = Team.builder().name("Team 1").build();
//        Amplify.API.mutate(
//                ModelMutation.create(team1),
//                success -> Log.i(Tag, "Team created successfully"),
//                failure -> Log.i(Tag, "Team not created")
//        );
//        Team team2 = Team.builder().name("Team 2").build();
//        Amplify.API.mutate(
//                ModelMutation.create(team2),
//                success -> Log.i(Tag, "Team created successfully"),
//                failure -> Log.i(Tag, "Team not created")
//        );
//        Team team3 = Team.builder().name("Team 3").build();
//        Amplify.API.mutate(
//                ModelMutation.create(team3),
//                success -> Log.i(Tag, "Team created successfully"),
//                failure -> Log.i(Tag, "Team not created")
//        );
//
//

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
}
//  tasks.add(new TaskModel("task 1", "task", TaskModel.StateEnum.NEW));
//        tasks.add(new TaskModel("task 2","task", TaskModel.StateEnum.IN_PROGRESS));
//        tasks.add(new TaskModel("task 3", "task", TaskModel.StateEnum.COMPLETED));
//        tasks.add(new TaskModel("task 4", "task", TaskModel.StateEnum.NEW));