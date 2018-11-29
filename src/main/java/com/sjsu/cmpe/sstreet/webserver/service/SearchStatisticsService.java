package com.sjsu.cmpe.sstreet.webserver.service;

import com.sjsu.cmpe.sstreet.webserver.model.Sensor;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import com.sjsu.cmpe.sstreet.webserver.model.search.InfrastructureStatistic;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SensorRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SmartClusterRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SmartNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchStatisticsService {

    private SmartClusterRepository smartClusterRepository;
    private SmartNodeRepository smartNodeRepository;
    private SensorRepository sensorRepository;

    @Autowired
    public SearchStatisticsService(
        SmartClusterRepository smartClusterRepository,
        SmartNodeRepository smartNodeRepository,
        SensorRepository sensorRepository
    ) {

        this.smartClusterRepository = smartClusterRepository;
        this.smartNodeRepository = smartNodeRepository;
        this.sensorRepository = sensorRepository;
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
            }
        }

        return statisticMap.values();
    }
}
