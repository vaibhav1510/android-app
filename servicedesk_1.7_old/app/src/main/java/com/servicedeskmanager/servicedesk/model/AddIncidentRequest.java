package com.servicedeskmanager.servicedesk.model;

/**
 * Created by Admin-PC on 2/24/2017.
 */

public class AddIncidentRequest {
    private Integer category;
    private Integer state;
    private Integer status;
    private Integer impact;
    private Integer urgency;
    private Integer priority;
    private Integer queue_id;
    private Integer workflow_id;
    private String serviceName;
    private Integer service;
    private String requester;
    private Integer requester_id;
    private Integer requester_support_loc;
    private Integer sla;
    private String summary;
    private String description;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getImpact() {
        return impact;
    }

    public void setImpact(Integer impact) {
        this.impact = impact;
    }

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getQueue_id() {
        return queue_id;
    }

    public void setQueue_id(Integer queue_id) {
        this.queue_id = queue_id;
    }

    public Integer getWorkflow_id() {
        return workflow_id;
    }

    public void setWorkflow_id(Integer workflow_id) {
        this.workflow_id = workflow_id;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public Integer getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(Integer requester_id) {
        this.requester_id = requester_id;
    }

    public Integer getRequester_support_loc() {
        return requester_support_loc;
    }

    public void setRequester_support_loc(Integer requester_support_loc) {
        this.requester_support_loc = requester_support_loc;
    }

    public Integer getSla() {
        return sla;
    }

    public void setSla(Integer sla) {
        this.sla = sla;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
