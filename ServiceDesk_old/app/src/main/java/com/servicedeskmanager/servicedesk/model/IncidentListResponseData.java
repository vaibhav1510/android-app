package com.servicedeskmanager.servicedesk.model;

import java.util.ArrayList;


public class IncidentListResponseData {
    private ArrayList<IncidentListModel> incidentList;

    public ArrayList<IncidentListModel> getIncidentList() {
        return incidentList;
    }

    public void setIncidentList(ArrayList<IncidentListModel> incidentList) {
        this.incidentList = incidentList;
    }
}
