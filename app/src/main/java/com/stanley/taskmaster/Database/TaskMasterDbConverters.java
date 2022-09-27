package com.stanley.taskmaster.Database;

import androidx.room.TypeConverter;

import java.util.Date;

public class TaskMasterDbConverters {
    @TypeConverter
    public static Date fromTimeStamp(Long val){
        return val == null ? null : new Date(val);
    }

    @TypeConverter
    public static Long dateToTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }
}
