package com.sjsu.cmpe.sstreet.webserver.broker.impl;

import com.sjsu.cmpe.sstreet.webserver.broker.MirroringServerBroker;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorData;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.List;

public class MirroringServerBrokerImpl implements MirroringServerBroker {

    private final URL serverUrl;

    private final RestTemplate restTemplate;

    private final String liveConnectivityByClusterAPI = "/cluster/connectivity/statistic/";
    private final String liveSensorDataByClusterAPI = "/cluster/data/";
    private final String liveSensorDataByNodeAPI = "/cluster/node/data/";
    private final String liveSensorDataBySensorAPI = "/cluster/sensor/data/";


    public MirroringServerBrokerImpl(URL serverUrl){
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<ConnectivityStat> getLiveConnectivityStatisticByCluster() {
        String url = serverUrl.toString() + liveConnectivityByClusterAPI;
        ResponseEntity<List<ConnectivityStat>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ConnectivityStat>>(){});
        List<ConnectivityStat> result = response.getBody();

        return result;
    }

    @Override
    public List<SensorData> getLiveSensorDataByCluster() {
        String url = serverUrl.toString() + liveSensorDataByClusterAPI;
        ResponseEntity<List<SensorData>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<SensorData>>(){});
        List<SensorData> result = response.getBody();

        return result;
    }

    @Override
    public List<SensorData> getLiveSensorDataByNode(Integer nodeId) {
        String url = serverUrl.toString() + liveSensorDataByNodeAPI;
        ResponseEntity<List<SensorData>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<SensorData>>(){});
        List<SensorData> result = response.getBody();

        return result;
    }

    @Override
    public SensorData getLiveSensorDataBySensor(Integer sensorId) {
        String url = serverUrl.toString() + liveSensorDataBySensorAPI;
        SensorData result = restTemplate.getForObject(url, SensorData.class);

        return result;
    }
}
