package com.stanley.taskmaster.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.stanley.taskmaster.models.TaskModel;

import java.util.List;

@Dao
public interface TaskMasterDao {
    @Insert
    public void insertTask(TaskModel taskModel);

    @Query("SELECT * FROM TaskModel")
    public List<TaskModel> findAll();

    @Query("SELECT * FROM TaskModel WHERE state = :state")
    public List<TaskModel> findAllByState(TaskModel.StateEnum state);

    @Query("SELECT * FROM TaskModel WHERE id = :id")
    TaskModel findTaskModelId(Long id);
}
