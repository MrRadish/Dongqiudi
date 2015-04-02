package com.ds.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-4-2.
 */
public class Events {
    private List<Incident> incidentList = new ArrayList<>();
    private String minute;

    public Events() {
    }

    public Events(String minute) {
        this.minute = minute;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void addlist(Incident incident){
        incidentList.add(incident);
    }

    public List<Incident> getlist(){
        return incidentList;
    }
}
