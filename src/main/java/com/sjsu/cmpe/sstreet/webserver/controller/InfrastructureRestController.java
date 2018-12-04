package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.model.Location;
import com.sjsu.cmpe.sstreet.webserver.model.Sensor;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import com.sjsu.cmpe.sstreet.webserver.model.search.InfrastructureStatistic;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;
import com.sjsu.cmpe.sstreet.webserver.service.LiveDataService;
import com.sjsu.cmpe.sstreet.webserver.service.SearchStatisticsService;
import com.sjsu.cmpe.sstreet.webserver.service.SmartClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.*;

@RestController
public class InfrastructureRestController {


    private SmartClusterService smartClusterService;
    private SearchStatisticsService searchStatisticsService;
    private LiveDataService liveDataService;

    @Autowired
    public InfrastructureRestController(
        SmartClusterService smartClusterService,
        SearchStatisticsService searchStatisticsService
    ) {

        this.smartClusterService = smartClusterService;
        this.searchStatisticsService = searchStatisticsService;
    }

    @RequestMapping(value = "/infrastructure/stat/{state}/{city}", method = RequestMethod.GET, produces = "application/json")
    public Collection<InfrastructureStatistic> getInfrastructureStatistic(@PathVariable("state") String state, @PathVariable("city") String city) {

        return searchStatisticsService.getInfrastructureStatistic(state, city);
    }


    @RequestMapping(value = "/infrastructure/connectivity/cluster/{state}/{city}", method = RequestMethod.GET, produces = "application/json")
    public List<ConnectivityStat> getConnectivityStatistic(@PathVariable("state") String state, @PathVariable("city") String city) {

        return liveDataService.getLiveConnectivityByStateAndCity(state, city);
    }

    @RequestMapping(value = "/infrastructure/{state}/{city}", method = RequestMethod.GET, produces = "application/json")
    public List getInfrastructureByStateAndCity(@PathVariable("state") String state, @PathVariable("city") String city) {

        //return createFakeData();
        return searchStatisticsService.getClustersForMapView(state, city);
    }


    private List<SmartCluster> createFakeData(){
        Location location1 = new Location(37.335720, -121.901672, "CA", "San Jose", "street#1", 94513);
        Location location2 = new Location(37.336326, -121.901941, "CA", "San Jose", "street#1", 94513);
        Location location3 = new Location(37.336275, -121.902616, "CA", "San Jose", "street#1", 94513);
        Location location4 = new Location(37.336744, -121.901930, "CA", "San Jose", "street#1", 94513);
        Location location5 = new Location(37.336454, -121.900826, "CA", "San Jose", "street#1", 94513);
        Location location6 = new Location(37.334588, -121.902951, "CA", "San Jose", "street#1", 94513);

        Location location7 = new Location(37.335240, -121.904136, "CA", "San Jose", "street#1", 94513);
        Location location8 = new Location(37.334060, -121.903992, "CA", "San Jose", "street#1", 94513);
        Location location9 = new Location(37.333744, -121.901977, "CA", "San Jose", "street#1", 94513);


        List<SmartCluster> result = new ArrayList<>();
        SmartCluster smartCluster1 = new SmartCluster("cluster#1", "model#1", "make#1",new Date(), null, location1);
        SmartNode smartNode1 = new SmartNode("node#1", "model#1", "make#1", new Date(), location2);
        SmartNode smartNode2 = new SmartNode("node#1", "model#1", "make#1", new Date(), location6);
        List<SmartNode> nodes = new ArrayList<>();
        nodes.add(smartNode1);
        nodes.add(smartNode2);
        smartCluster1.setNodes(nodes);
        result.add(smartCluster1);

        Sensor sensor1 = new Sensor("sensor#1", "model#1", "make#1", new Date(), "temperature", location3);
        Sensor sensor2 = new Sensor("sensor#2", "model#1", "make#1", new Date(), "temperature", location4);
        Sensor sensor3 = new Sensor("sensor#2", "model#1", "make#1", new Date(), "temperature", location5);
        List<Sensor> sensors = new ArrayList<>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);
        smartNode1.setSensors(sensors);

        Sensor sensor4 = new Sensor("sensor#1", "model#1", "make#1", new Date(), "temperature", location7);
        Sensor sensor5 = new Sensor("sensor#2", "model#1", "make#1", new Date(), "temperature", location8);
        Sensor sensor6 = new Sensor("sensor#2", "model#1", "make#1", new Date(), "temperature", location9);
        List<Sensor> sensors2 = new ArrayList<>();
        sensors2.add(sensor4);
        sensors2.add(sensor5);
        sensors2.add(sensor6);
        smartNode2.setSensors(sensors2);

        return result;
    }

    private List mockData(String state, String city){
        SmartCluster cluster = new SmartCluster();
        cluster.setLocation(new Location(state, city, "Street#1"));
        cluster.setIdSmartCluster(1);

        SmartCluster cluster2 = new SmartCluster();
        cluster2.setLocation(new Location(state, city, "Street#1"));
        cluster2.setIdSmartCluster(2);

        SmartCluster cluster3 = new SmartCluster();
        cluster3.setLocation(new Location(state, city, "Street#2"));
        cluster3.setIdSmartCluster(3);

        SmartCluster cluster4 = new SmartCluster();
        cluster4.setLocation(new Location(state, city, "Street#2"));
        cluster4.setIdSmartCluster(4);

        SmartCluster cluster5 = new SmartCluster();
        cluster5.setLocation(new Location(state, city, "Street#3"));
        cluster5.setIdSmartCluster(5);

        // This is the fake data for the testing purpose
        List<Map> response = new ArrayList<>();
        Map<String, Object> streetStat1 = new TreeMap<>();
        streetStat1.put("cluster", cluster);
        streetStat1.put("status", "UP");

        Map<String, Object> streetStat2 = new TreeMap<>();
        streetStat2.put("cluster", cluster2);
        streetStat2.put("status", "DOWN");

        Map<String, Object> streetStat3 = new TreeMap<>();
        streetStat3.put("cluster", cluster3);
        streetStat3.put("status", "DOWN");

        Map<String, Object> streetStat4 = new TreeMap<>();
        streetStat4.put("cluster", cluster4);
        streetStat4.put("status", "DOWN");

        Map<String, Object> streetStat5 = new TreeMap<>();
        streetStat5.put("cluster", cluster5);
        streetStat5.put("status", "DOWN");

        response.add(streetStat1);
        response.add(streetStat2);
        response.add(streetStat3);
        response.add(streetStat4);
        response.add(streetStat5);

        return response;
    }
}
