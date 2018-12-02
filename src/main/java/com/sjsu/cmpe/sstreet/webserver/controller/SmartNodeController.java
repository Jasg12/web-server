package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.model.SearchSmartNodeBySmartClusterWrapper;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sjsu.cmpe.sstreet.webserver.model.Location;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;

import com.sjsu.cmpe.sstreet.webserver.service.SmartNodeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/smart_node")
public class SmartNodeController {

    private final SmartNodeService smartNodeService;
    private Logger log;

    @Autowired
    public SmartNodeController(SmartNodeService smartNodeService, Logger log) {
        this.smartNodeService = smartNodeService;
        this.log = log;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nodes/{clusterId}", produces = "application/json")
    public List<SmartNode> getNodeList(@PathVariable("clusterId") Integer clusterId){
        List<SmartNode> result = new ArrayList<>();
        SmartNode node1 = new SmartNode();
        node1.setIdSmartNode(1);
        node1.setName("Node#1");
        result.add(node1);

        SmartNode node2 = new SmartNode();
        node2.setIdSmartNode(2);
        node2.setName("Node#2");
        result.add(node2);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = "application/json")
    public SmartNode createSmartNode(@RequestBody SearchSmartNodeBySmartClusterWrapper searchSmartNodeBySmartClusterWrapper) {

        return smartNodeService.createSmartNode(searchSmartNodeBySmartClusterWrapper.getSmartNode(), searchSmartNodeBySmartClusterWrapper.getIdSmartCluster());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public @ResponseBody ResponseEntity<String> updateSmartNode(@RequestBody SmartNode smartNode){

        return smartNodeService.updateSmartNode(smartNode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public @ResponseBody List<SmartNode> getAllSmartNode(){

        return smartNodeService.getAllSmartNodes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byId/{idSmartNode}")
    public @ResponseBody SmartNode getSmartNodeById(@PathVariable(value = "idSmartNode") Integer idSmartNode){

        return smartNodeService.getSmartNodeById(idSmartNode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byLocation")
    public @ResponseBody SmartNode getSmartNodeByLocation(@RequestBody Location location){

        return smartNodeService.getSmartNodeByLocation(location);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byName/{name}")
    public @ResponseBody SmartNode getSmartNodeByName(@PathVariable(value = "name") String name){

        return smartNodeService.getSmartNodeByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nodes", produces = "application/json")
    public List<SmartNode> getSmartNodeBySmartCluster(@RequestBody SmartCluster smartCluster){

        return smartNodeService.getSmartNodeBySmartCluster(smartCluster);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byName/{name}")
    public @ResponseBody ResponseEntity<String> deleteSmartNodeByName(@PathVariable(value = "name") String name){

        return smartNodeService.deleteSmartNodeByName(name);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteSmartNodeById(@PathVariable(value = "id") Integer idSmartNode){

        return smartNodeService.deleteSmartNodeById(idSmartNode);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/bySmartCluster")
    public @ResponseBody ResponseEntity<String> deleteSmartNodeBySmartCluster(@RequestBody SmartCluster smartCluster){

        return smartNodeService.deleteSmartNodeBySmartCluster(smartCluster);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/unregistered", produces = "application/json")
    public List<SmartNode> getUnregisteredNodes(@RequestParam("clusterId") Integer clusterId){

        return smartNodeService.getUnregisteredNodes(clusterId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register", produces = "application/json")
    public SmartNode registerNode(@RequestBody SmartNode smartNode){
        log.info("Getting node register request node:{}", smartNode);

        return smartNodeService.registerNode(smartNode);
    }

}
