package com.servicedeskmanager.servicedesk.restful;

public enum RestfulEndpoints {

    Login("api-token-auth/"), AllIncident("inci/incidentinfo/incident_cse/");
    private final String msg;

    private RestfulEndpoints(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
