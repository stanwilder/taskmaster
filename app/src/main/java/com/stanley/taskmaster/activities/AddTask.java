package com.stanley.taskmaster.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.stanley.taskmaster.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AddTask extends AppCompatActivity {

    public static final String Tag = "AddTaskActivity";
    Spinner taskSpinner = null;
    CompletableFuture<List<Task>> taskFuture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setTaskSpinner();


        Button taskSubmitButton = AddTask.this.findViewById(R.id.taskSubmitButton);
        taskSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CharSequence submittedText = "Submit Completed. Task added!";
                Context submit = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(submit, submittedText, duration);
            }
        });
    }
    private void setTaskSpinner(){
        Amplify.API.query(
                ModelQuery.list(Task.class),
                success -> {
                    Log.i(Tag, "Read Tasks successfully");
                    ArrayList<String> team = new ArrayList<>();
                    ArrayList<Task> teams = new ArrayList<>();
                    for(Task task : success.getData()){
                        teams.add(task);
                        team.add(teams.)
                    }
                });
    },
    failure -> {
        taskFuture.complete(null);
        Log.i(Tag, "Task not read successfully");
    }
}
