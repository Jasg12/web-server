package com.sjsu.cmpe.sstreet.webserver.broker;

import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorData;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;

import java.util.List;

public interface MirroringServerBroker {

    ConnectivityStat getLiveConnectivityStatisticForCluster();

    List<ConnectivityStat> getLiveConnectivityStatisticForAllCluster();

    List<SensorData> getLiveSensorDataByCluster();

    List<SensorData> getLiveSensorDataByNode(Integer nodeId);

    SensorData getLiveSensorDataBySensor(Integer sensorId);

    List<SmartNode> getUnregisteredNodes();

}
