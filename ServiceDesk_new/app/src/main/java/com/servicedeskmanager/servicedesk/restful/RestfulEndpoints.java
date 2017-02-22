package com.servicedeskmanager.servicedesk.restful;

public enum RestfulEndpoints {

    Base("http://192.168.50.95:8000"),
    Login("api-token-auth/"), AllIncident("inci/incidentinfo/incident_cse/"),
    SingleIncident("incident/edit/33");
    private final String msg;

    private RestfulEndpoints(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
