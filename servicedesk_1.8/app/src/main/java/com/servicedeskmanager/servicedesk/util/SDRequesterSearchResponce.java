package com.servicedeskmanager.servicedesk.util;

/**
 * Created by Admin-PC on 3/1/2017.
 */

public class SDRequesterSearchResponce {

    private Object[] asset_details;
    private String phone;
    private String full_name;
    private Object[] mapped_asset;
    private Integer id;
    private String email;
    private Integer support_location;

    public Object[] getAsset_details() {
        return asset_details;
    }

    public void setAsset_details(Object[] asset_details) {
        this.asset_details = asset_details;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Object[] getMapped_asset() {
        return mapped_asset;
    }

    public void setMapped_asset(Object[] mapped_asset) {
        this.mapped_asset = mapped_asset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSupport_location() {
        return support_location;
    }

    public void setSupport_location(Integer support_location) {
        this.support_location = support_location;
    }
}
