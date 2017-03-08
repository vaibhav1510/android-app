package com.servicedeskmanager.servicedesk.model;

/**
 * Created by DMX-I-LT-41 on 3/3/2017.
 */

public class IncidentPriorityMatrix {
    private Integer urgency_id;
    private Integer impact_id;
    private Integer priority_id;

    public Integer getUrgency_id() {
        return urgency_id;
    }

    public void setUrgency_id(Integer urgency_id) {
        this.urgency_id = urgency_id;
    }

    public Integer getImpact_id() {
        return impact_id;
    }

    public void setImpact_id(Integer impact_id) {
        this.impact_id = impact_id;
    }

    public Integer getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(Integer priority_id) {
        this.priority_id = priority_id;
    }
}
