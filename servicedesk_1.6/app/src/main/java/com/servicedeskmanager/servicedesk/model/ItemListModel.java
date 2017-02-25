package com.servicedeskmanager.servicedesk.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin-PC on 2/24/2017.
 */

public class ItemListModel {

    private String itemId;
    private String priority;
    private String item_class;
    private String itemType;
    private String inventoryId;
    private String barCode;
    private String qrCode;

    private Map<String, String> sladata = new HashMap<String, String>();

    public String getItemId() { return itemId;}
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getItemClass() {
        return item_class;
    }
    public void setItemClass(String item_class) {
        this.item_class = item_class;
    }

    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(String inventoryId) { this.inventoryId = inventoryId; }

    public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getQrCode() {
        return qrCode;
    }
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Map<String, String> getSladata() {
        return sladata;
    }

    public void setSladata(Map<String, String> sladata) {
        this.sladata = sladata;
    }
}
