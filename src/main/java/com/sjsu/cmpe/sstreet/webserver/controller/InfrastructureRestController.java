package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.model.Location;
import com.sjsu.cmpe.sstreet.webserver.model.Sensor;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class InfrastructureRestController {

    @RequestMapping(value = "/infrastructure/stat/{state}/{city}", method = RequestMethod.GET, produces = "application/json")
    public List getInfrastructureStatistic(@PathVariable("state") String state, @PathVariable("city") String city) {

        // This is the fake data for the testing purpose
        List<Map> response = new ArrayList<>();
        Map<String, Object> streetStat = new TreeMap<>();
        streetStat.put("name", "Street#1");
        streetStat.put("clusters", 21);
        streetStat.put("nodes", 56);
        streetStat.put("sensors", 210);
        response.add(streetStat);

        return response;
    }


    @RequestMapping(value = "/infrastructure/connectivity/cluster/{state}/{city}", method = RequestMethod.GET, produces = "application/json")
    public List getConnectivityStatistic(@PathVariable("state") String state, @PathVariable("city") String city) {

        SmartCluster cluster = new SmartCluster();
        cluster.setLocation(new Location(state, city, "Washington 1"));
        cluster.setIdSmartCluster(1);

        SmartCluster cluster2 = new SmartCluster();
        cluster2.setLocation(new Location(state, city, "Washington 1"));
        cluster2.setIdSmartCluster(2);

        // This is the fake data for the testing purpose
        List<Map> response = new ArrayList<>();
        Map<String, Object> streetStat1 = new TreeMap<>();
        streetStat1.put("cluster", cluster);
        streetStat1.put("status", "UP");

        Map<String, Object> streetStat2 = new TreeMap<>();
        streetStat2.put("cluster", cluster2);
        streetStat2.put("status", "DOWN");

        response.add(streetStat1);
        response.add(streetStat2);

        return response;
    }

    @RequestMapping(value = "/infrastructure/{state}/{city}", method = RequestMethod.GET, produces = "application/json")
    public List getInfrastructureByStateAndCity(@PathVariable("state") String state, @PathVariable("city") String city) {

        return createFakeData();
    }








    private List<SmartCluster> createFakeData(){
        Location location1 = new Location(37.335720, -121.901672, "CA", "San Jose", "street#1", 94513);
        Location location2 = new Location(37.336326, -121.901941, "CA", "San Jose", "street#1", 94513);
        Location location3 = new Location(37.336275, -121.902616, "CA", "San Jose", "street#1", 94513);
        Location location4 = new Location(37.336744, -121.901930, "CA", "San Jose", "street#1", 94513);

        List<SmartCluster> result = new ArrayList<>();
        SmartCluster smartCluster1 = new SmartCluster("cluster#1", "model#1", "make#1", new Date(), location1);
        SmartNode smartNode1 = new SmartNode("node#1", "model#1", "make#1", new Date(), location2);
        Set<SmartNode> nodes = new HashSet<>();
        nodes.add(smartNode1);
     //   smartCluster1.setSmartNodeSet(nodes);
        result.add(smartCluster1);

        Sensor sensor1 = new Sensor("sensor#1", "model#1", "make#1", new Date(), "temperature", location3);
        Sensor sensor2 = new Sensor("sensor#2", "model#1", "make#1", new Date(), "temperature", location4);
        Set<Sensor> sensors = new HashSet<>();
        sensors.add(sensor1);
        sensors.add(sensor2);
      //  smartNode1.setSensorSet(sensors);

        return result;
    }
}
