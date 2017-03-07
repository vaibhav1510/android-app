package com.servicedeskmanager.servicedesk.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin-PC on 2/24/2017.
 */

public class AddIncidentModel {
    private BigInteger id;

    //Requester Details field
    private String full_name;
    private String phone;
    private String email;

    private String categories;
    private String service_name;
    private String urgency;
    private String summary;
    private String description;

    private Map<String, String> sladata = new HashMap<String, String>();

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getfull_name() {return full_name;}
    public void setfull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getphone() {return phone;}
    public void setphone(String phone) {
        this.phone = phone;
    }

    public String get_email() {return email;}
    public void set_email(String email) {
        this.email = email;
    }

    public String getcategories() {return categories;}
    public void setcategories(String categories) {
        this.categories = categories;
    }

    public String getService_name() {return service_name;}
    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getUrgency() {return urgency;}
    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getsummary() {return summary;}
    public void setsummary(String summary) {
        this.summary = summary;
    }

    public String getdescription() {return description;}
    public void setdescription(String description) {
        this.description = description;
    }

    public Map<String, String> getSladata() {
        return sladata;
    }

    public void setSladata(Map<String, String> sladata) {
        this.sladata = sladata;
    }
}
