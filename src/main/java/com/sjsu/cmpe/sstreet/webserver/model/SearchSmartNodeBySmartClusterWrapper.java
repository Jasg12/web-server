package com.sjsu.cmpe.sstreet.webserver.model;

public class SearchSmartNodeBySmartClusterWrapper {

    private SmartNode smartNode;
    private Integer idSmartCluster;

    public SearchSmartNodeBySmartClusterWrapper(SmartNode smartNode, Integer idSmartCluster) {
        this.smartNode = smartNode;
        this.idSmartCluster = idSmartCluster;
    }

    public SearchSmartNodeBySmartClusterWrapper() {
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
