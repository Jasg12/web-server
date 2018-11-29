package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.data_transfer.LocationDto;
import com.sjsu.cmpe.sstreet.webserver.data_transfer.SmartClusterDto;
import com.sjsu.cmpe.sstreet.webserver.data_transfer.SmartClusterUpdateDto;

import com.sjsu.cmpe.sstreet.webserver.model.Location;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.service.SmartClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/smart_cluster")
public class SmartClusterController {

    private final SmartClusterService smartClusterService;

    @Autowired
    public SmartClusterController(SmartClusterService smartClusterService) {
        this.smartClusterService = smartClusterService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public @ResponseBody ResponseEntity<String> createSmartCluster(@RequestBody SmartCluster smartCluster){

        return smartClusterService.createSmartCluster(smartCluster);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public @ResponseBody ResponseEntity<String> updateSmartCluster(@RequestBody SmartCluster smartCluster){

        return smartClusterService.updateSmartCluster(smartCluster);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public @ResponseBody List<SmartCluster> getAllSmartCluster(){

        return smartClusterService.getAllSmartClusters();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byId/{idSmartCluster}")
    public @ResponseBody SmartCluster getSmartClusterById(@PathVariable(value = "idSmartCluster") Integer idSmartCluster){

        return smartClusterService.getSmartClusterById(idSmartCluster);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byLocation")
    public @ResponseBody SmartCluster getSmartClusterByLocation(@RequestBody Location location){

        return smartClusterService.getSmartClusterByLocation(location);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byName/{name}")
    public @ResponseBody SmartCluster getSmartClusterByName(@PathVariable(value = "name") String name){

        return smartClusterService.getSmartClusterByName(name);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byName/{name}")
    public @ResponseBody ResponseEntity<String> deleteSmartClusterByName(@PathVariable(value = "name") String name){

        return smartClusterService.deleteSmartClusterByName(name);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteSmartClusterById(@PathVariable(value = "id") Integer idSmartCluster){

        return smartClusterService.deleteSmartClusterById(idSmartCluster);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/clusters/{state}/{city}", produces = "application/json")
    public List<SmartCluster> getClustersByCity(@PathVariable("state") String state, @PathVariable("city") String city){

     //   return smartClusterService.getSmartClusterByCity(city);
        List<SmartCluster> result = new ArrayList<>();
        SmartCluster cluster = new SmartCluster();
        cluster.setLocation(new Location(state, city, "Washington 1"));
        cluster.setIdSmartCluster(1);
        cluster.setName("Cluster#1");

        SmartCluster cluster2 = new SmartCluster();
        cluster2.setLocation(new Location(state, city, "Washington 1"));
        cluster2.setIdSmartCluster(2);
        cluster2.setName("Cluster#2");

        result.add(cluster);
        result.add(cluster2);

        return result;
    }

}
