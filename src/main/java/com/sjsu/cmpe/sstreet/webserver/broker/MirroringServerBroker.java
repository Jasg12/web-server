package com.sjsu.cmpe.sstreet.webserver.broker;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorData;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;

import java.util.List;

public interface MirroringServerBroker {

    List<ConnectivityStat> getLiveConnectivityStatisticByCluster();

    List<SensorData> getLiveSensorDataByCluster();

    List<SensorData> getLiveSensorDataByNode(Integer nodeId);

    SensorData getLiveSensorDataBySensor(Integer sensorId);

}
