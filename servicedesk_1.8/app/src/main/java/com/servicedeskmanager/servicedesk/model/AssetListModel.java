package com.servicedeskmanager.servicedesk.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin-PC on 3/1/2017.
 */

public class AssetListModel {

    private String display_id;
    private String criticality;
    private String item_type;
    private String inventory_id;
    private String addon_field_description;
    private Integer id;



    public String getDisplay_id() {
        return display_id;
    }

    public void setDisplay_id(String display_id) {
        this.display_id = display_id;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getAddon_field_description() {
        return addon_field_description;
    }

    public void setAddon_field_description(String addon_field_description) {
        this.addon_field_description = addon_field_description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
