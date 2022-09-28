package com.stanley.taskmaster;

import android.app.Application;
import android.util.Log;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;

public class TaskAmplifyApp extends Application {
    public static final String Tag = "Taskmaster";

    @Override
    public void onCreate(){
        super.onCreate();
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
        } catch (AmplifyException amplifyException){
            Log.e(Tag, "Error initializing Amplify: " + amplifyException.getMessage(), amplifyException);
        }
    }
}
