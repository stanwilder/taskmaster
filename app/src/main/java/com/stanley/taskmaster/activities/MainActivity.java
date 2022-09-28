package com.stanley.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.stanley.taskmaster.R;
import com.stanley.taskmaster.adapter.RecyclerViewAdapter;
import com.stanley.taskmaster.models.TaskModel;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "task_master_db";
    public static final String TASK_NAME_EXTRA_TAG = "taskName";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        recyclerViewSetup();



    }


    @Override
    protected void onResume() {
        super.onResume();
        String userName = sharedPreferences.getString(SettingsPage.USER_NAME_TAG, "no username");
        TextView editedUsername = findViewById(R.id.textView2);
        editedUsername.setText(userName + "'s Tasks");
    }
    private void recyclerViewSetup(){
        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);

        List<TaskModel> tasks = new ArrayList<>();

        tasks.add(new TaskModel("task 1", "task", TaskModel.StateEnum.NEW));
        tasks.add(new TaskModel("task 2","task", TaskModel.StateEnum.IN_PROGRESS));
        tasks.add(new TaskModel("task 3", "task", TaskModel.StateEnum.COMPLETED));
        tasks.add(new TaskModel("task 4", "task", TaskModel.StateEnum.NEW));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(tasks, this);
        recyclerView.setAdapter(adapter);
    }






        }
