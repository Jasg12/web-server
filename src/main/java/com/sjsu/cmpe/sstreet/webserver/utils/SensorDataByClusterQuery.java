package com.sjsu.cmpe.sstreet.webserver.utils;

import com.sjsu.cmpe.sstreet.webserver.model.SensorType;

public class SensorDataByClusterQuery extends SensorDataSearchQuery {

    private Integer clusterId;

    public SensorDataByClusterQuery(){
        super();
    }

    public SensorDataByClusterQuery(Integer clusterId) {
        super();
        this.clusterId = clusterId;
    }

    public SensorDataByClusterQuery(
        Integer maxResult,
        String continuation,
        Integer clusterId
    ) {

        super(maxResult, continuation);
        this.clusterId = clusterId;
    }

    public Integer getClusterId() {

        return clusterId;
    }

    public void setClusterId(Integer clusterId) {

        this.clusterId = clusterId;
    }
}
