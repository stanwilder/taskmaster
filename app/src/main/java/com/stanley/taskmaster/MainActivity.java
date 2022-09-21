package com.stanley.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. get an UI element by id
        Button submitBttn = MainActivity.this.findViewById(R.id.submitButton);

        //2. add an event listener
        submitBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //3. Call back fn() OnClick() -> do stuff
                //4. do stuff in the callback fn()
                System.out.println("ZORK WAS HERE");
                Log.v("", "Very Verbose");
                Log.d("", "Debugger");
                Log.i("", "Information");
                Log.w("", "Warning");
                Log.e("", "Error");
                Log.wtf("", "What a terrible failure");

            }
        });
        mainActivityButton1();
    }

        private void mainActivityButton1() {
            Button taskTwoButton = MainActivity.this.findViewById(R.id.mainActivityButton1);

            taskTwoButton.setOnClickListener(view -> {
                Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailsPage.class);
                startActivity(goToTaskDetailPage);
            });
        }
}