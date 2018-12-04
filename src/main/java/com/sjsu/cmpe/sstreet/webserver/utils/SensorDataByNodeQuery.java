package com.sjsu.cmpe.sstreet.webserver.utils;

public class SensorDataByNodeQuery extends SensorDataSearchQuery  {

    private Integer nodeId;

    public SensorDataByNodeQuery(){
        super();
    }

    public SensorDataByNodeQuery(Integer nodeId) {

        this.nodeId = nodeId;
    }

    public SensorDataByNodeQuery(Integer maxResult, String continuation, Integer nodeId) {

        super(maxResult, continuation);
        this.nodeId = nodeId;
    }

    public Integer getNodeId() {

        return nodeId;
    }

    public void setNodeId(Integer nodeId) {

        this.nodeId = nodeId;
    }
}
