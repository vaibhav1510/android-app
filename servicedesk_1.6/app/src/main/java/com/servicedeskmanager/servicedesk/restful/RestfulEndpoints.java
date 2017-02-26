package com.servicedeskmanager.servicedesk.restful;

public enum RestfulEndpoints {

//    Base("http://192.168.50.95:8000"),
    Base("http://trialsd.everest-ims.com/"),
    Login("api-token-auth/"),
    AllIncident("inci/incidentinfo/incident_cse/"),
    Incident("inci/incidentinfo/incident_cse/");

    private final String msg;

    private RestfulEndpoints(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
