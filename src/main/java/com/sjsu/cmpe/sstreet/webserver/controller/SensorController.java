package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.model.Sensor;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SensorController {

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
