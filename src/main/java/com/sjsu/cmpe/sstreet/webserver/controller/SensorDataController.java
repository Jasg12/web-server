package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.data_transfer.SmartClusterDto;
import com.sjsu.cmpe.sstreet.webserver.model.SensorData;
import com.sjsu.cmpe.sstreet.webserver.model.SensorType;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.TimeRange;
import com.sjsu.cmpe.sstreet.webserver.service.SensorDataService;
import com.sjsu.cmpe.sstreet.webserver.service.SmartClusterService;
import com.sjsu.cmpe.sstreet.webserver.utils.SensorDataSearchQuery;
import com.sjsu.cmpe.sstreet.webserver.utils.SensorDataSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@RequestMapping(value = "/sensor_data")
public class SensorDataController{

    private final SensorDataService sensorDataService;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;

    }

    @RequestMapping(method = RequestMethod.GET ,value = "/get/(sensortype)")
    public @ResponseBody
    SensorDataSearchResult getSensorDataBySensorType(@PathVariable("sensortype") SensorType type){
        return sensorDataService.getSensorDataBySensorType(type);
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/get/(cluster)/(timerange)")
    public @ResponseBody
    SensorDataSearchResult getDataBySmartClusterAndTimeRange(@PathVariable("cluster") Integer idSmartCluster ,  @PathVariable("timerange") Long timestamp){

        return sensorDataService.getDataBySmartClusterAndTimeRange(idSmartCluster,timestamp);
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/get/(cluster)/(node)/(timerange)")
    public @ResponseBody
    SensorDataSearchResult getDataBySmartClusterAndSmartNodeAndTimeRange(@PathVariable("cluster") Integer idSmartCluster,@PathVariable("node") Integer idSmartNode,@PathVariable("timerange") Long timestamp){

        return sensorDataService.getDataBySmartClusterAndSmartNodeAndTimeRange(idSmartCluster,idSmartNode,timestamp);
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/get/(cluster)/(node)")
    public @ResponseBody
    SensorDataSearchResult getDataBySmartClusterAndSmartNode(@PathVariable("cluster") Integer idSmartCluster,@PathVariable("node") Integer idSmartNode){

        return sensorDataService.getDataBySmartClusterAndSmartNode(idSmartCluster,idSmartNode);
    }



}
