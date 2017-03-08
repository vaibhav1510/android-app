package com.servicedeskmanager.servicedesk.model;

/**
 * Created by DMX-I-LT-41 on 2/26/2017.
 */

public class StringWithTag {
    public String string;
    public Object tag;

    public StringWithTag(String string, Object tag) {
        this.string = string;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return string;
    }

    public Object getTag() {
        return tag;
    }
}
