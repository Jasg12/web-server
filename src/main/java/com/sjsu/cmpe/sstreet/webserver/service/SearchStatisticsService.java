package com.sjsu.cmpe.sstreet.webserver.service;

import com.sjsu.cmpe.sstreet.webserver.model.Sensor;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import com.sjsu.cmpe.sstreet.webserver.model.TimeRange;
import com.sjsu.cmpe.sstreet.webserver.model.search.InfrastructureStatistic;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.EntityType;
import com.sjsu.cmpe.sstreet.webserver.repository.cassandra.ConnectivityStatRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SensorRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SmartClusterRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SmartNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchStatisticsService {

    private SmartClusterRepository smartClusterRepository;
    private SmartNodeRepository smartNodeRepository;
    private SensorRepository sensorRepository;
    private ConnectivityStatRepository connectivityStatRepository;

    @Autowired
    public SearchStatisticsService(
        SmartClusterRepository smartClusterRepository,
        SmartNodeRepository smartNodeRepository,
        SensorRepository sensorRepository,
        ConnectivityStatRepository connectivityStatRepository
    ) {

        this.smartClusterRepository = smartClusterRepository;
        this.smartNodeRepository = smartNodeRepository;
        this.sensorRepository = sensorRepository;
        this.connectivityStatRepository = connectivityStatRepository;
    }

    public Collection<InfrastructureStatistic> getInfrastructureStatistic(String state, String city){

        Map<String, InfrastructureStatistic> statisticMap = new HashMap<>();

        List<SmartCluster> clusters = smartClusterRepository.findAllByLocation_StateAndLocation_City(state, city);
        for (SmartCluster cluster:clusters){
            String street = cluster.getLocation().getStreet();
            if(statisticMap.containsKey(street)){
                statisticMap.get(street).increaseClusterCount();
            } else{
                InfrastructureStatistic infrastructureStatistic = new InfrastructureStatistic(city, street);
                infrastructureStatistic.increaseClusterCount();
                statisticMap.put(street, infrastructureStatistic);
            }
        }

        List<SmartNode> nodes = smartNodeRepository.findAllByLocation_StateAndLocation_City(state, city);
        for (SmartNode node:nodes){
            String street = node.getLocation().getStreet();
            if(statisticMap.containsKey(street)){
                statisticMap.get(street).increaseNodesCount();
            } else{
                InfrastructureStatistic infrastructureStatistic = new InfrastructureStatistic(city, street);
                infrastructureStatistic.increaseNodesCount();
                statisticMap.put(street, infrastructureStatistic);
            }
        }

        List<Sensor> sensors = sensorRepository.findAllByLocation_StateAndLocation_City(state, city);
        for (Sensor sensor:sensors){
            String street = sensor.getLocation().getStreet();
            if(statisticMap.containsKey(street)){
                statisticMap.get(street).increaseSensorsCount();
            } else{
                InfrastructureStatistic infrastructureStatistic = new InfrastructureStatistic(city, street);
                infrastructureStatistic.increaseSensorsCount();
                statisticMap.put(street, infrastructureStatistic);
            }
        }

        return statisticMap.values();
    }

    public List<ConnectivityStat> getConnectivityStatByTimeRange(Integer id, EntityType entityType, TimeRange timeRange){
        return  connectivityStatRepository.findAllByIdAndEntityTypeAndTimeRange(id, entityType.toString(), timeRange.getFrom().getTime(), timeRange.getTo().getTime());
    }

    public List<SmartCluster> getClustersForMapView(String state, String city){

        List<SmartCluster> clusters = smartClusterRepository.findAllByLocation_StateAndLocation_City(state, city);
        for(SmartCluster cluster:clusters){
            List<SmartNode> nodes = smartNodeRepository.findBySmartCluster(cluster);
            attachNodesToCluster(cluster, nodes);
        }

        return clusters;
    }

    private void attachNodesToCluster(SmartCluster cluster, List<SmartNode> nodes){
        cluster.setNodes(new ArrayList<>());
        for(SmartNode node:nodes){
            List<Sensor> sensors = sensorRepository.findBySmartNode(node);
            node.setSmartCluster(null);
            cluster.getNodes().add(node);
            attachSensorsToNode(node, sensors);
        }

    }

    private void attachSensorsToNode(SmartNode node, List<Sensor> sensors){
        node.setSensors(new ArrayList<>());
        for(Sensor sensor:sensors){
            sensor.setSmartNode(null);
            node.getSensors().add(sensor);
        }
    }
}
