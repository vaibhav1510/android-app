package com.servicedeskmanager.servicedesk.util;

/**
 * Created by DMX-I-LT-41 on 3/3/2017.
 */

public class SDServiceResponse {
    private Integer default_urgency;
    private Object[] status;
    private Object[] matrix;
    private Integer default_impact;
    private Integer workflow_config;
    private Integer queue;
    private String sla;

    public Integer getDefault_urgency() {
        return default_urgency;
    }

    public void setDefault_urgency(Integer default_urgency) {
        this.default_urgency = default_urgency;
    }

    public Object[] getStatus() {
        return status;
    }

    public void setStatus(Object[] status) {
        this.status = status;
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

    public Integer getWorkflow_config() {
        return workflow_config;
    }

    public void setWorkflow_config(Integer workflow_config) {
        this.workflow_config = workflow_config;
    }

    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }
}
