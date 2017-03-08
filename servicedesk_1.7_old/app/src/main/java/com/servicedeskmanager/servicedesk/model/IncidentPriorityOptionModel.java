package com.servicedeskmanager.servicedesk.model;

/**
 * Created by DMX-I-LT-41 on 2/26/2017.
 */

public class IncidentPriorityOptionModel {
    private Integer priority_id;
    private String name;

    public Integer getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(Integer priority_id) {
        this.priority_id = priority_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
