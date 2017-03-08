package com.servicedeskmanager.servicedesk.restful;

public enum RestfulEndpoints {

    Login("api-token-auth/"),
    AllIncident("inci/incidentinfo/incident_cse/"),
    EditIncident("inci/incidentinfo/"),
    SaveIncident("inci/incidentinfo/"),
    GetUserDetails("getuserdetails/"),
    SearchRequester("catalogue/service/requesterdata/"),
    AddIncidentOption("inci/incidentinfo/options/"),
    GetImpactServiceList("catalogue/category/treedata/"),
    GetServiceBaseConfig("inci/incidentinfo/add_service_data/");
    private final String msg;

    private RestfulEndpoints(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}


