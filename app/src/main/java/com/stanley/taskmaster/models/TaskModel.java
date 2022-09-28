package com.stanley.taskmaster.models;

import androidx.annotation.NonNull;



public class TaskModel {

    public Long id;

    public String title;

    public String body;
    public StateEnum state;
    public TaskModel(String title, String body, StateEnum state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
    public TaskModel(){}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }


    public enum StateEnum {
        NEW("new task"),
        ASSIGNED("assigned"),
        IN_PROGRESS("in progress"),
        COMPLETED("completed");

        public final String state;

        StateEnum(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
        public static StateEnum fromString(String stateOption){
            for (StateEnum state : StateEnum.values()){
                if(state.state.equals(stateOption)){
                    return state;
                }
            }
            return null;
        }
        @NonNull
        @Override
        public String toString(){
            if(state == null){
                return "Nothing Assigned";
            }
            return state;
        }
    }


}

