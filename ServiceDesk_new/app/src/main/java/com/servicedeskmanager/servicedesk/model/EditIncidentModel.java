package com.servicedeskmanager.servicedesk.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin-PC on 2/21/2017.
 */

public class EditIncidentModel {

    private BigInteger id;
    private String display_id;
    private String requester;
    private String firstName;
    private String phone;
    private String email;
    private String status;
    private String state;
    private String description;
    private String symptom;
    private String  resolution;
    private String rootCause;
    private String troubleReason;
    private String recoveryAction;
    private String category_name;
    private String service_name;
    private String classification;
    private String impact;
    private String urgency;
    private String priority;

    private Map<String, String> sladata = new HashMap<String, String>();

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {this.phone = phone;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {this.status = status;}

    public String getState() {
        return state;
    }
    public void setState(String state) {this.state = state;}

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
    public String getSymptom() {
        return symptom;
    }

    public String getResolution() {
        return resolution;
    }
    public void setResolution(String resolution) {
        this.resolution= resolution;
    }

    public String getRootCause() {
        return rootCause;
    }
    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getTroubleReason() {
        return  troubleReason;
    }
    public void setTroubleReason(String  troubleReason) {this. troubleReason =  troubleReason;}

    public String getRecoveryAction() {return recoveryAction;}
    public void setRecoveryAction(String recoveryAction) {
        this.recoveryAction = recoveryAction;
    }

    public String getCategory_name() {return category_name;}
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getService_name() {return service_name;}
    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getClassification() {return classification;}
    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getImpact() {return impact;}
    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getUrgency() {return urgency;}
    public void setUrgency(String urgency) {
        this.urgency = urgency;
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
