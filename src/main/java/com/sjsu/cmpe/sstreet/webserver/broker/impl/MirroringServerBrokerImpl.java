package com.sjsu.cmpe.sstreet.webserver.broker.impl;

import com.sjsu.cmpe.sstreet.webserver.broker.MirroringServerBroker;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorData;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MirroringServerBrokerImpl implements MirroringServerBroker {

    private final SmartCluster cluster;
    private final RestTemplate restTemplate;

    private static Logger log = LoggerFactory.getLogger("application");

    private final String liveConnectivityForClusterAPI = "/cluster/connectivity/statistic/";
    private final String liveConnectivityForAllClusterAPI = "/cluster/connectivity/statistic/all";
    private final String liveSensorDataByClusterAPI = "/cluster/data/";
    private final String liveSensorDataByNodeAPI = "/cluster/node/data/";
    private final String liveSensorDataBySensorAPI = "/cluster/sensor/data/";
    private final String UNREGISTERED_NODES_API = "/smart_node/nodes/unregistered";
    private final String REGISTERED_NODE_EVENT = "/smart_node/node/registered";

    public MirroringServerBrokerImpl(SmartCluster cluster){
        this.cluster = cluster;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public ConnectivityStat getLiveConnectivityStatisticForCluster() {
        String url = buildUrl(liveConnectivityForClusterAPI);
        ConnectivityStat result = restTemplate.getForObject(url, ConnectivityStat.class);

        return result;
    }

    @Override
    public List<ConnectivityStat> getLiveConnectivityStatisticForAllCluster() {
        String url = buildUrl(liveConnectivityForAllClusterAPI);
        ResponseEntity<List<ConnectivityStat>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ConnectivityStat>>(){});
        List<ConnectivityStat> result = response.getBody();

        return result;
    }

    @Override
    public List<SensorData> getLiveSensorDataByCluster() {
        String url = buildUrl(liveSensorDataByClusterAPI);
        ResponseEntity<List<SensorData>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<SensorData>>(){});
        List<SensorData> result = response.getBody();

        return result;
    }

    @Override
    public List<SensorData> getLiveSensorDataByNode(Integer nodeId) {
        String url = buildUrl(liveSensorDataByNodeAPI);
        ResponseEntity<List<SensorData>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<SensorData>>(){});
        List<SensorData> result = response.getBody();

        return result;
    }

    @Override
    public SensorData getLiveSensorDataBySensor(Integer sensorId) {
        String url = buildUrl(liveSensorDataBySensorAPI);
        SensorData result = restTemplate.getForObject(url, SensorData.class);

        return result;
    }

    @Override
    public List<SmartNode> getUnregisteredNodes() {

        String url = buildUrl(UNREGISTERED_NODES_API);
        Map<String, Integer> arguments = new HashMap<>();
        arguments.put("clusterId", cluster.getIdSmartCluster());
        ResponseEntity<List<SmartNode>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<SmartNode>>(){},
            arguments);
        List<SmartNode> nodes = response.getBody();

        return nodes;
    }

    @Override
    public void nodeRegisteredEvent(SmartNode smartNode){
        log.info("Call the cluster with registered node:{}", smartNode);
        String url = buildUrl(REGISTERED_NODE_EVENT);
        HttpEntity<SmartNode> httpEntity = new HttpEntity<>(smartNode);
        restTemplate.put(url, httpEntity);
    }

    private String buildUrl(String apiPath){
        return String.valueOf(cluster.getUrl().toString() + apiPath);
    }
}
