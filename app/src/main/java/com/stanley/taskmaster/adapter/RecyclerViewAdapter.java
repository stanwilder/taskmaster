package com.stanley.taskmaster.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stanley.taskmaster.R;
import com.stanley.taskmaster.activities.MainActivity;
import com.stanley.taskmaster.activities.TaskDetailsPage;
import com.stanley.taskmaster.fragments.FragView;
import com.stanley.taskmaster.models.TaskModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.FragViewHolder> {

    List<TaskModel> data;
    Context callingActivity;

    public RecyclerViewAdapter(List<TaskModel> data, Context callingActivity) {
        this.data = data;
        this.callingActivity = callingActivity;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.FragViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fragView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_frag_view, parent, false);
        return new FragViewHolder(fragView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.FragViewHolder holder, int position) {
        TextView titleTextView = holder.itemView.findViewById(R.id.fragTitle);
        String taskTitle = this.data.get(position).getTitle();
        titleTextView.setText((position + 1) + ". " + taskTitle);

        TextView bodyTextView = holder.itemView.findViewById(R.id.fragBody);
        String body = this.data.get(position).getBody();
        bodyTextView.setText(body);

        TextView stateTextView = holder.itemView.findViewById(R.id.FragState);
        String state = this.data.get(position).getState().toString();
        stateTextView.setText(state);

        View FragViewHolder = holder.itemView;
        FragViewHolder.setOnClickListener(view ->{
            Intent goToTaskDetailsPage = new Intent(callingActivity, TaskDetailsPage.class);
            goToTaskDetailsPage.putExtra(MainActivity.TASK_NAME_EXTRA_TAG, "taskName");
            callingActivity.startActivity(goToTaskDetailsPage);
        });

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class FragViewHolder extends RecyclerView.ViewHolder{
        public FragViewHolder(@NonNull View view){
            super(view);
        }
    }
}