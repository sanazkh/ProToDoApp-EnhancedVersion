package com.example.myapp.models;

/**
 * Created by sanazk on 8/18/17.
 */

public class Task {
    private int id;
    private String name;
    private String desc;
    private String dueDate;
    private String prio;
    private String status;

    public Task(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task(int tId, String tName, String tDesc, String tDu, String tP, String tS){
        this.id = tId;
        this.name = tName;
        this.desc = tDesc;
        this.dueDate = tDu;
        this.prio = tP;
        this.status = tS;
    }

    public Task(String tName, String tDesc, String tDu, String tP, String tS){
        this.name = tName;
        this.desc = tDesc;
        this.dueDate = tDu;
        this.prio = tP;
        this.status = tS;
    }
}
