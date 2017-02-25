package com.servicedeskmanager.servicedesk.model;

import java.util.HashMap;
import java.util.Map;

public class IncidentListModel {
    private Integer id;
    private String display_id;
    private String requester;
    private String category_name;
    private String service_name;
    private String status;
    private String status_color;
    private String summary;
    private String description;
    private String priority;
    private Map<String, String> sladata = new HashMap<String, String>();

    public Integer getId() {
        return id;
    }

    public void setId(float id) {
        this.id = (int)id;
    }

    public String getDisplay_id() {
        return display_id;
    }

    public void setDisplay_id(String display_id) {
        this.display_id = display_id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_color() {
        return status_color;
    }

    public void setStatus_color(String status_color) {
        this.status_color = status_color;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Map<String, String> getSladata() {
        return sladata;
    }

    public void setSladata(Map<String, String> sladata) {
        this.sladata = sladata;
    }
}
