package com.servicedeskmanager.servicedesk.model;

/**
 * Created by DMX-I-LT-41 on 2/26/2017.
 */

public class IncidentOptionModel {
    private Object[] impact;
    private Object[] category;
    private Object[] priority;
    private Object[] state;
    private Object[] status;
    private Object[] urgency;

    public Object[] getImpact() {
        return impact;
    }

    public void setImpact(Object[] impact) {
        this.impact = impact;
    }

    public Object[] getCategory() {
        return category;
    }

    public void setCategory(Object[] category) {
        this.category = category;
    }

    public Object[] getPriority() {
        return priority;
    }

    public void setPriority(Object[] priority) {
        this.priority = priority;
    }

    public Object[] getState() {
        return state;
    }

    public void setState(Object[] state) {
        this.state = state;
    }

    public Object[] getStatus() {
        return status;
    }

    public void setStatus(Object[] status) {
        this.status = status;
    }

    public Object[] getUrgency() {
        return urgency;
    }

    public void setUrgency(Object[] urgency) {
        this.urgency = urgency;
    }
}
