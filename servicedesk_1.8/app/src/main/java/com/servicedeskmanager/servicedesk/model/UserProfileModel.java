package com.servicedeskmanager.servicedesk.model;

/**
 * Created by DMX-I-LT-41 on 2/27/2017.
 */

public class UserProfileModel {
    private String username;
    private String organization_name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }
}
