package com.sjsu.cmpe.sstreet.webserver.utils;

public class SensorDataBySensorQuery extends SensorDataSearchQuery {

    private Integer sensorId;

    public SensorDataBySensorQuery(Integer sensorId) {

        this.sensorId = sensorId;
    }

    public SensorDataBySensorQuery(Integer maxResult, String continuation, Integer sensorId) {

        super(maxResult, continuation);
        this.sensorId = sensorId;
    }

    public Integer getSensorId() {

        return sensorId;
    }

    public void setSensorId(Integer sensorId) {

        this.sensorId = sensorId;
    }
}
