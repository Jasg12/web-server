package com.sjsu.cmpe.sstreet.webserver.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
}
