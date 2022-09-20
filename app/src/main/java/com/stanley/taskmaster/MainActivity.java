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
        Button submitBttn = MainActivity.this.findViewById(R.id.mainActivityAddTaskButton);

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

        Button orderFormButtn = MainActivity.this.findViewById(R.id.mainActivityToOrderFormBttn);
        // setting up routing logic with intents. Intents are the highway between activities
        // send you along with data
        orderFormButtn.setOnClickListener(view -> {
            // set up the intent (Current context.this, class to go to Class.class)
            Intent goToOrderFormActivity = new Intent(MainActivity.this, AddTask.class);
            // Launch the intent
            startActivity(goToOrderFormActivity);
        });

    }
}