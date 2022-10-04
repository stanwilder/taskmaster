package com.stanley.taskmaster.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.StateEnum;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.stanley.taskmaster.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class AddTask extends AppCompatActivity {

    public static final String Tag = "AddTaskActivity";
    Spinner taskSpinner = null;
    Spinner taskTeamSpinner = null;
    CompletableFuture<List<Team>> teamFuture = null;
    CompletableFuture<List<Task>> taskFuture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskFuture = new CompletableFuture<>();
        teamFuture = new CompletableFuture<>();
        setTaskSpinner();
        setUpTeamSpinner();
        setSubmitButton();

    }

    private void setTaskSpinner() {
        ArrayList<String> teamNames = new ArrayList<>();
        taskSpinner = findViewById(R.id.taskTeamspinner);
        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    Log.i(Tag, "Read Tasks successfully");

                    ArrayList<Team> teams = new ArrayList<>();
                    for (Team team : success.getData()) {
                        teams.add(team);
                    }
                    teamFuture.complete(teams);
                    runOnUiThread(() -> {
                       for(Team team : teams){
                           teamNames.add(team.getName());
                       }
                        taskSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                android.R.layout.simple_spinner_item,
                                teamNames));
                    });
                    },
                failure -> {
                    teamFuture.complete(null);
                    Log.i(Tag, "Did not read task successfully");
                }

        );
    }
    private void setUpTeamSpinner(){
        taskTeamSpinner = findViewById(R.id.taskTeamspinner);
        taskTeamSpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                StateEnum.values()
        ));
    }

    private void setSubmitButton(){
        String teamString = taskSpinner.getSelectedItem().toString();
        List<Task> tasks = null;
        try{
            tasks = taskFuture.get();
        } catch (InterruptedException ie){
            Log.e(Tag, "Interrupted Exception while getting teams");
            Thread.currentThread().interrupt();
        } catch (ExecutionException ee){
            Log.e(Tag, "ExecutionException while getting teams" + ee.getMessage());
        }
    }
        Team selectedTeam = teams.stream().filter(t -> t.getName().equals(selectedTeamString)).findAny().orElseThrow(RuntimeException::new);
    Spinner TaskTeamSpinner = findViewById(R.id.taskTeamspinner);
    Button saveNewTeamButton = findViewById(R.id.taskSubmitButton);
    saveNewTeamButton.setOnClickListener(view -> {
        String taskTitle = ((EditText) findViewById())
    })
}

