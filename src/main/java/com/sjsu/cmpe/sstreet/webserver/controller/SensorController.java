package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.model.Sensor;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import com.sjsu.cmpe.sstreet.webserver.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/sensor")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public @ResponseBody
    ResponseEntity<String> createSensor(@RequestBody Sensor sensor){

        return sensorService.createSensor(sensor);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public @ResponseBody ResponseEntity<String> updateSensor(@RequestBody Sensor sensor){

        return sensorService.updateSensor(sensor);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all")
    public @ResponseBody
    List<Sensor> getAllSensor(){

        return sensorService.getAllSensors();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/byId/{idSensor}")
    public @ResponseBody Sensor getSensorById(@PathVariable(value = "idSensor") Integer idSensor){

        return sensorService.getSensorById(idSensor);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/bySmartNode")
    public @ResponseBody List<Sensor> getSensorBySmartNode(@RequestBody SmartNode smartNode){

        return sensorService.getSensorBySmartNode(smartNode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/bySmartCluster")
    public @ResponseBody List<Sensor> getSensorBySmartCluster(@RequestBody SmartCluster smartCluster){

        return sensorService.getSensorBySmartCluster(smartCluster);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/byId/{id}")
    public @ResponseBody ResponseEntity<String> deleteSensorById(@PathVariable(value = "id") Integer idSensor){

        return sensorService.deleteSensorById(idSensor);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/by/node")
    public @ResponseBody ResponseEntity<String> deleteSensorBySmartNode(@RequestBody SmartNode smartNode){

        return sensorService.deleteSensorBySmartNode(smartNode);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/by/cluster")
    public @ResponseBody ResponseEntity<String> deleteSensorBySmartCluster(@RequestBody SmartCluster smartCluster){

        return sensorService.deleteSensorBySmartCluster(smartCluster);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/sensors/{nodeId}", produces = "application/json")
    public List<Sensor> getSensorsByNode(@PathVariable("nodeId") Integer nodeId){
        List<Sensor> result = new ArrayList<>();
        Sensor sensor = new Sensor();
        sensor.setIdSensor(1);
        sensor.setName("Sensor#1");
        result.add(sensor);

        Sensor sensor2 = new Sensor();
        sensor2.setIdSensor(2);
        sensor2.setName("Sensor#2");
        result.add(sensor2);

        return result;
    }

}
