package com.stanley.taskmaster;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details_page);
    }
    @Override
    protected void onResume(){
        super.onResume();

        String taskName = getIntent().getStringExtra("taskName");
        TextView taskTextView = findViewById(R.id.taskTextView);
        taskTextView.setText(taskName);
    }
}
