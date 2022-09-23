package com.stanley.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.stanley.taskmaster.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        recyclerViewSetup();


//        mainActivityButton1();
//        mainActivityButton2();
//        mainActivityButton3();
//        mainActivitySettingsButton();
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

        tasks.add(new TaskModel("task 1", "task", "assigned"));
        tasks.add(new TaskModel("task 2","task", "in progress"));
        tasks.add(new TaskModel("task 3", "task", "complete"));
    }





//    private void mainActivityButton1 () {
//            Button mainActivityButton1 = MainActivity.this.findViewById(R.id.mainActivityButton1);
//
//        mainActivityButton1.setOnClickListener(view -> {
//                Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailsPage.class);
//                String buttonText = ((Button)view).getText().toString();
//                goToTaskDetailPage.putExtra("taskButton", buttonText);
//                startActivity(goToTaskDetailPage);
//            });
//            }
//    private void mainActivityButton2 () {
//        Button mainActivityButton2 = MainActivity.this.findViewById(R.id.mainActivitybutton2);
//
//        mainActivityButton2.setOnClickListener(view -> {
//            Intent goToTaskDetailsPage = new Intent(MainActivity.this, TaskDetailsPage.class);
//            String buttonText = ((Button)view).getText().toString();
//            goToTaskDetailsPage.putExtra("taskButton", buttonText);
//            startActivity(goToTaskDetailsPage);
//        });
//    }
//
//    private void mainActivityButton3 () {
//        Button mainActivityButton3 = MainActivity.this.findViewById(R.id.mainActivitybutton3);
//
//        mainActivityButton3.setOnClickListener(view -> {
//            Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailsPage.class);
//            String buttonText = ((Button)view).getText().toString();
//            goToTaskDetailPage.putExtra("taskButton", buttonText);
//            startActivity(goToTaskDetailPage);
//        });
//
//    }

        }
