package com.stanley.taskmaster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.StateEnum;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.stanley.taskmaster.R;
import com.stanley.taskmaster.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class AddTask extends AppCompatActivity {

    public static final String Tag = "AddTaskActivity";
    Spinner statusSpinner = null;
    Spinner taskTeamSpinner = null;
    ArrayList<String> teamNames = null;
    CompletableFuture<List<Team>> teamFuture = null;
    CompletableFuture<List<Task>> taskFuture = null;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        teamNames = new ArrayList<>();
        taskFuture = new CompletableFuture<>();
        teamFuture = new CompletableFuture<>();
        setStatusSpinner();
        setSubmitButton();
        setTeamSpinner();

    }

    @Override
    protected void onResume() {
        super.onResume();


        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    Log.i(Tag, "Teams read successfully");
                    teamNames.clear();
                    ArrayList<Team> teams = new ArrayList<>();
                    for (Team dataBaseTeam : success.getData()) {
                        teamNames.add(dataBaseTeam.getName());
                        teams.add(dataBaseTeam);
                    }
                    teamFuture.complete(teams);
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                },
                failure -> {
                    Log.i(Tag, "Teams not read");
                    teamFuture.complete(null);
                }
        );
    }

        private void setTeamSpinner() {
            Spinner taskTeamSpinner = findViewById(R.id.taskTeamspinner);
            adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, teamNames);
            taskTeamSpinner.setAdapter(adapter);
        }
        private void setStatusSpinner() {
            Spinner statusSpinner = findViewById(R.id.taskStatusSpinner);
            statusSpinner.setAdapter(new ArrayAdapter<>(
                    this,
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    StateEnum.values()
            ));
        }

        private void setSubmitButton() {
            Button saveNewTaskBttn = findViewById(R.id.taskSubmitButton);
            Spinner statusSpinner = findViewById(R.id.taskStatusSpinner);
            Spinner teamSpinner = findViewById(R.id.taskTeamspinner);
            saveNewTaskBttn.setOnClickListener(view -> {
                String teamString = teamSpinner.getSelectedItem().toString();
                String dateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
                List<Team> teams = null;

                try {
                    teams = teamFuture.get();
                } catch (InterruptedException ie) {
                    Log.e(Tag, "Interrupted Exception while getting teams");
                    Thread.currentThread().interrupt();
                } catch (ExecutionException ee) {
                    Log.e(Tag, "ExecutionException while getting teams" + ee.getMessage());
                }
                Team selectedTeam = teams.stream().filter(t -> t.getName().equals(teamString)).findAny().orElseThrow(RuntimeException::new);
//            data from inputs
                String taskTitle = ((EditText) findViewById(R.id.taskEditText)).getText().toString();
                String taskBody = ((EditText) findViewById(R.id.descriptionEditText)).getText().toString();

//            Task Object
                Task newTask = Task.builder()
                        .title(taskTitle)
                        .body(taskBody)
                        .state((StateEnum) statusSpinner.getSelectedItem())
                        .dateCreated(new Temporal.DateTime(dateString))
                        .team(selectedTeam)
                        .build();
                Amplify.API.mutate(
                        ModelMutation.create(newTask),
                        success -> Log.i(Tag, "Task created"),
                        failure -> Log.i(Tag, "Task not created" + failure)
                );
                Intent goToMainActivity = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToMainActivity);
            });
        }


}

