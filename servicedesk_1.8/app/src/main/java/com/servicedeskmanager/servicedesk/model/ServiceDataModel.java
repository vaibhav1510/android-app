package com.servicedeskmanager.servicedesk.model;

/**
 * Created by DMX-I-LT-41 on 3/2/2017.
 */

public class ServiceDataModel {
    private Object [] children;
    private String node_type;
    private String node_tree_text;
    private Integer id;
    private String name;

    public Object[] getChildren() {
        return children;
    }

    public void setChildren(Object[] children) {
        this.children = children;
    }

    public String getNode_type() {
        return node_type;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public String getNode_tree_text() {
        return node_tree_text;
    }

    public void setNode_tree_text(String node_tree_text) {
        this.node_tree_text = node_tree_text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
