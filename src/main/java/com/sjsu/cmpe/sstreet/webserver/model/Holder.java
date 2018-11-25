package com.sjsu.cmpe.sstreet.webserver.model;

public class Holder {

    private SmartNode smartNode;
    private Integer idSmartCluster;

    public Holder(SmartNode smartNode, Integer idSmartCluster) {
        this.smartNode = smartNode;
        this.idSmartCluster = idSmartCluster;
    }

    public Holder() {
    }

    public SmartNode getSmartNode() {
        return smartNode;
    }

    public void setSmartNode(SmartNode smartNode) {
        this.smartNode = smartNode;
    }

    public Integer getIdSmartCluster() {
        return idSmartCluster;
    }

    public void setIdSmartCluster(Integer idSmartCluster) {
        this.idSmartCluster = idSmartCluster;
    }
}
