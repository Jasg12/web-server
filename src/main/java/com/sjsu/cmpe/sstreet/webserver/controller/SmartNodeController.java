package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SmartNodeController {

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

}
