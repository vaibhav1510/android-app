package com.servicedeskmanager.servicedesk.util;

/**
 * Created by DMX-I-LT-41 on 2/26/2017.
 */

public class SDEditIncidentResponse {
    private Object option;
    private Object incident;
    private Object status_option;
    private Object sla_data;
    private Object[] asset_details;


    public Object getOption() {
        return option;
    }

    public void setOption(Object option) {
        this.option = option;
    }

    public Object getIncident() {
        return incident;
    }

    public void setIncident(Object incident) {
        this.incident = incident;
    }

    public Object getStatus_option() {
        return status_option;
    }

    public void setStatus_option(Object status_option) {
        this.status_option = status_option;
    }

    public Object getSla_data() {
        return sla_data;
    }

    public void setSla_data(Object sla_data) {
        this.sla_data = sla_data;
    }

    public Object[] getAsset_details() {
        return asset_details;
    }

    public void setAsset_details(Object[] asset_details) {
        this.asset_details = asset_details;
    }
}
