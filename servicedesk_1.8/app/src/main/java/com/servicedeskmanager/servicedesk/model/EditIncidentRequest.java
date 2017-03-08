package com.servicedeskmanager.servicedesk.model;

/**
 * Created by Admin-PC on 2/21/2017.
 */

public class EditIncidentRequest {

    private Integer id;
    private String display_id;
    private Integer requester_id;
    private Integer category;
    private String service;
    private String classification;
    private Integer status;
    private Integer state;
    private String summary;
    private String description;
    private Integer urgency;
    private Integer impact;
    private Integer priority;
    private Integer group;
    private Integer level;
    private Integer level_seq;
    private Integer customer_site_group;
    private Integer assignee;
    private Integer incident_source;
    private Integer queue;
    private Integer workflow_config;
    private String serviceName;
    private String classificationName;
    private String resolutionType;
    private String impactArea;
    private String callback;
    private String symptom;
    private String rootCause;
    private String resolution;
    private String troubleReason;
    private String recoveryAction;
    private Integer team_template;
    private Integer sla;
    private String cseRequired;
    private String cse_required;
    private Integer vendor;
    private Integer vendor_team;
    private Integer vendor_cse;
    private Integer cse_assignee;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplay_id() {
        return display_id;
    }

    public void setDisplay_id(String display_id) {
        this.display_id = display_id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public Integer getImpact() {
        return impact;
    }

    public void setImpact(Integer impact) {
        this.impact = impact;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel_seq() {
        return level_seq;
    }

    public void setLevel_seq(Integer level_seq) {
        this.level_seq = level_seq;
    }

    public Integer getCustomer_site_group() {
        return customer_site_group;
    }

    public void setCustomer_site_group(Integer customer_site_group) {
        this.customer_site_group = customer_site_group;
    }

    public Integer getAssignee() {
        return assignee;
    }

    public void setAssignee(Integer assignee) {
        this.assignee = assignee;
    }

    public Integer getIncident_source() {
        return incident_source;
    }

    public void setIncident_source(Integer incident_source) {
        this.incident_source = incident_source;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }

    public String getImpactArea() {
        return impactArea;
    }

    public void setImpactArea(String impactArea) {
        this.impactArea = impactArea;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getTroubleReason() {
        return troubleReason;
    }

    public void setTroubleReason(String troubleReason) {
        this.troubleReason = troubleReason;
    }

    public String getRecoveryAction() {
        return recoveryAction;
    }

    public void setRecoveryAction(String recoveryAction) {
        this.recoveryAction = recoveryAction;
    }

    public Integer getTeam_template() {
        return team_template;
    }

    public void setTeam_template(Integer team_template) {
        this.team_template = team_template;
    }

    public Integer getSla() {
        return sla;
    }

    public void setSla(Integer sla) {
        this.sla = sla;
    }

    public Integer getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(Integer requester_id) {
        this.requester_id = requester_id;
    }

    public String getCseRequired() {
        return cseRequired;
    }

    public void setCseRequired(String cseRequired) {
        this.cseRequired = cseRequired;
    }

    public String getCse_required() {
        return cse_required;
    }

    public void setCse_required(String cse_required) {
        this.cse_required = cse_required;
    }

    public Integer getVendor() {
        return vendor;
    }

    public void setVendor(Integer vendor) {
        this.vendor = vendor;
    }

    public Integer getVendor_team() {
        return vendor_team;
    }

    public void setVendor_team(Integer vendor_team) {
        this.vendor_team = vendor_team;
    }

    public Integer getVendor_cse() {
        return vendor_cse;
    }

    public void setVendor_cse(Integer vendor_cse) {
        this.vendor_cse = vendor_cse;
    }

    public Integer getCse_assignee() {
        return cse_assignee;
    }

    public void setCse_assignee(Integer cse_assignee) {
        this.cse_assignee = cse_assignee;
    }
}
