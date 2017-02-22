package com.servicedeskmanager.servicedesk.model;

import java.util.ArrayList;

/**
 * Created by Admin-PC on 2/21/2017.
 */

public class EditResponseData {
        private ArrayList<EditIncidentModel> editIncident;

        public ArrayList<EditIncidentModel> getEditIncident() {
            return editIncident;
        }

        public void setEditIncident(ArrayList<EditIncidentModel> editIncidentModels) {
            this.editIncident= editIncident;
        }
    }