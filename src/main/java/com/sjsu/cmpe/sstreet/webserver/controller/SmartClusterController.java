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
    public @ResponseBody
    ResponseEntity<String> createSmartCluster(@RequestBody SmartClusterDto smartClusterDto){

        return smartClusterService.createSmartCluster(smartClusterDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public @ResponseBody
    ResponseEntity<String> updateSmartCluster(@RequestBody SmartClusterUpdateDto smartClusterUpdateDto){

        return smartClusterService.updateSmartCluster(smartClusterUpdateDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public @ResponseBody
    List<SmartClusterDto> getAllSmartCluster(){

        return smartClusterService.getAllSmartClusters();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byId/{idSmartCluster}")
    public @ResponseBody
    SmartClusterDto getSmartClusterById(@PathVariable(value = "idSmartCluster") Integer idSmartCluster){

        return smartClusterService.getSmartClusterById(idSmartCluster);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/status/byId/{idSmartCluster}")
    public @ResponseBody
    ResponseEntity<String> getSmartClusterStatusById(@PathVariable(value = "idSmartCluster") Integer idSmartCluster){

        return smartClusterService.getSmartClusterStatusById(idSmartCluster);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byLocation")
    public @ResponseBody
    SmartClusterDto getSmartClusterByLocation(@RequestBody LocationDto locationDto){

        return smartClusterService.getSmartClusterByLocation(locationDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byName/{name}")
    public @ResponseBody
    SmartClusterDto getSmartClusterByName(@PathVariable(value = "name") String name){

        return smartClusterService.getSmartClusterByName(name);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byName/{name}")
    public @ResponseBody
    ResponseEntity<String> deleteSmartClusterByName(@PathVariable(value = "name") String name){

        return smartClusterService.deleteSmartClusterByName(name);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byId/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteSmartClusterById(@PathVariable(value = "id") Integer idSmartCluster){

        return smartClusterService.deleteSmartClusterById(idSmartCluster);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clusters/{state}/{city}", produces = "application/json")
    public List<SmartCluster> getClustersByCity(@PathVariable("state") String state, @PathVariable("city") String city){
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
