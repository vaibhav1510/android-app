package com.servicedeskmanager.servicedesk.util;

/**
 * Created by DMX-I-LT-41 on 3/2/2017.
 */

public class SDOptionAddIncident {
    private Object[] impact;
    private Object[] category;
    private Object[] priority;
    private Object[] state;
    private Object[] status;
    private Object[] urgency;
    private Object[] matrix;
    private Integer default_impact;
    private Integer default_urgency;
    private Integer queue;
    private Integer workflow_config;
    private String sla;

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

    public Object[] getUrgency() {
        return urgency;
    }

    public void setUrgency(Object[] urgency) {
        this.urgency = urgency;
    }

    public Object[] getMatrix() {
        return matrix;
    }

    public void setMatrix(Object[] matrix) {
        this.matrix = matrix;
    }

    public Integer getDefault_impact() {
        return default_impact;
    }

    public void setDefault_impact(Integer default_impact) {
        this.default_impact = default_impact;
    }

    public Integer getDefault_urgency() {
        return default_urgency;
    }

    public void setDefault_urgency(Integer default_urgency) {
        this.default_urgency = default_urgency;
    }

    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    public Integer getWorkflow_config() {
        return workflow_config;
    }

    public void setWorkflow_config(Integer workflow_config) {
        this.workflow_config = workflow_config;
    }

    public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public Object[] getStatus() {
        return status;
    }

    public void setStatus(Object[] status) {
        this.status = status;
    }
}
