package com.stanley.taskmaster.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.stanley.taskmaster.models.TaskModel;

@TypeConverters({TaskMasterDbConverters.class})
@Database(entities = {TaskModel.class}, version = 1)
public abstract class TaskMasterDb extends RoomDatabase{

}
