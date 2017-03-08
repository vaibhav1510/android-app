package com.servicedeskmanager.servicedesk.model;

/**
 * Created by DMX-I-LT-41 on 2/26/2017.
 */

public class IncidentStatusModel {
    private Integer id;
    private String name;
    private String state_id;
    private Integer workflow_flag;
    private Integer sla_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public Integer getWorkflow_flag() {
        return workflow_flag;
    }

    public void setWorkflow_flag(Integer workflow_flag) {
        this.workflow_flag = workflow_flag;
    }

    public Integer getSla_flag() {
        return sla_flag;
    }

    public void setSla_flag(Integer sla_flag) {
        this.sla_flag = sla_flag;
    }
}
