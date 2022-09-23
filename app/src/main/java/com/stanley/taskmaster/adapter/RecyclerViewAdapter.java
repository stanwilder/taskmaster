package com.stanley.taskmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stanley.taskmaster.R;
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
    public void onBindViewHolder(@NonNull FragViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class FragViewHolder extends RecyclerView.ViewHolder{
        public FragViewHolder(@NonNull View view){
            super(view);
        }
    }
}