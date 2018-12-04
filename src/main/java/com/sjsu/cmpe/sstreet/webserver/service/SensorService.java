package com.sjsu.cmpe.sstreet.webserver.service;

import com.sjsu.cmpe.sstreet.webserver.model.*;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.*;
import com.sjsu.cmpe.sstreet.webserver.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private SensorRepository sensorRepository;
    private SmartNodeRepository smartNodeRepository;
    private LocationRepository locationRepository;

    @Autowired
    public SensorService(
        SensorRepository sensorRepository,
        SmartNodeRepository smartNodeRepository,
        LocationRepository locationRepository)
    {
        this.sensorRepository = sensorRepository;
        this.smartNodeRepository = smartNodeRepository;
        this.locationRepository = locationRepository;
    }

    public Sensor createSensor(Sensor sensor) {
        Location location = sensor.getLocation();
        location = locationRepository.save(location);
        sensor = sensorRepository.save(sensor);

        return sensor;
    }

    public ResponseEntity<String> updateSensor(Sensor sensor) {


        Optional<Sensor> sensorResult = sensorRepository.findById(sensor.getIdSensor());

        sensorResult.ifPresent(result ->
            EntityUtils.setUnsetValues(sensor, result)
        );

        if (sensorResult.isPresent()) {

            if (null != sensorRepository.save(sensor)) {
                return ResponseEntity.ok("Smart Node updated");

            } else {
                return new ResponseEntity<>("Smart Node  Failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>("Smart Node with ID: " + sensor.getIdSensor() + " does not exist", HttpStatus.BAD_REQUEST);
        }

    }

    public List<Sensor> getAllSensors() {

        Iterable<Sensor> sensorIterable = sensorRepository.findAll();
        List<Sensor> sensorList = new ArrayList<>();

        sensorIterable.forEach(sensor ->
                sensorList.add(sensor)
        );

        return sensorList;
    }

    public Sensor getSensorById(Integer id) {

        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        List<Sensor> sensor = new ArrayList<>();

        if (!sensorOptional.isPresent()) {

            return null;
        }


        return sensor.get(0);

    }

    public List<Sensor> getSensorBySmartNode(SmartNode smartNode) {

        return sensorRepository.findBySmartNode(smartNode);

    }

    public List<Sensor> getSensorBySmartCluster(SmartCluster smartCluster) {

        List<Sensor> sensorList = new ArrayList<>();

        List<SmartNode> smartNodeList = smartNodeRepository.findBySmartCluster(smartCluster);

        for (SmartNode smartNode:smartNodeList)
            sensorList.addAll(sensorRepository.findBySmartNode(smartNode));

       return sensorList;

    }

    public ResponseEntity<String> deleteSensorById(Integer id) {

        sensorRepository.deleteById(id);
        return ResponseEntity.ok("Sensor Successfully Deleted");

    }

    public ResponseEntity<String> deleteSensorBySmartNode(SmartNode smartNode) {

        sensorRepository.deleteBySmartNode(smartNode);

        return ResponseEntity.ok("Sensor Successfully Deleted");

    }

    public ResponseEntity<String> deleteSensorBySmartCluster(SmartCluster smartCluster) {

        List<SmartNode> smartNodeList = smartNodeRepository.findBySmartCluster(smartCluster);

        for (SmartNode smartNode:smartNodeList)
            sensorRepository.deleteBySmartNode(smartNode);

        return ResponseEntity.ok("Sensor Successfully Deleted");

    }

    public List<Sensor> getAllSensorsByNodeId(Integer nodeId){

        return sensorRepository.findAllBySmartNode_IdSmartNode(nodeId);
    }

}


