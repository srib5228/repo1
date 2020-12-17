package com.test;

public class FamilyTreeNode {
    private final String parent_id;
    private final String node_id;
    private final String node_name;

    public FamilyTreeNode(String parent_id, String node_id, String node_name) {
        this.parent_id = parent_id;
        this.node_id = node_id;
        this.node_name = node_name;
    }

    @Override
    public String toString() {
        return "FamilyTreeNode{" +
                "parent_id='" + parent_id + '\'' +
                ", node_id='" + node_id + '\'' +
                ", node_name='" + node_name + '\'' +
                '}';
    }

    public String getParent_id() {
        return parent_id;
    }


    public String getNode_id() {
        return node_id;
    }


    public String getNode_name() {
        return node_name;
    }

}
