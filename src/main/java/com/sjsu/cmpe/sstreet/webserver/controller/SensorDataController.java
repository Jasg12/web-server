package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.model.TimeRange;
import com.sjsu.cmpe.sstreet.webserver.service.SensorDataService;
import com.sjsu.cmpe.sstreet.webserver.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/sensor_data")
public class SensorDataController{

    private final SensorDataService sensorDataService;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;

    }

    @RequestMapping(method = RequestMethod.POST ,value = "/sensor/data/by/cluster/from/{from}/to/{to}", produces = "application/json")
    public SensorDataSearchResult getDataBySmartClusterAndTimeRange(
        @RequestBody SensorDataByClusterQuery searchQuery,
        @PathVariable("from") long from,
        @PathVariable("to") long to){
        TimeRange timeRange = new TimeRange(from, to);

        return sensorDataService.getDataBySmartClusterAndTimeRange(searchQuery, timeRange);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sensor/data/by/node/from/{from}/to/{to}")
    public SensorDataSearchResult getDataBySmartNodeAndTimeRange(
        @RequestBody SensorDataByNodeQuery searchQuery,
        @PathVariable("from") long from,
        @PathVariable("to") long to){
        TimeRange timeRange = new TimeRange(from, to);

        return sensorDataService.getDataBySmartNodeAndTimeRange(searchQuery, timeRange);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sensor/data/by/sensor/from/{from}/to/{to}")
    public SensorDataSearchResult getDataBySensorAndTimeRange(
        @RequestBody SensorDataBySensorQuery searchQuery,
        @PathVariable("from") long from,
        @PathVariable("to") long to){
        TimeRange timeRange = new TimeRange(from, to);

        return sensorDataService.getDataBySensorAndTimeRange(searchQuery, timeRange);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sensor/data/by/sensor")
    public @ResponseBody
    SensorDataSearchResult getSensorDataBySensor(@RequestBody SensorDataBySensorQuery searchQuery){

        return sensorDataService.getDataBySensor(searchQuery);
    }

}
