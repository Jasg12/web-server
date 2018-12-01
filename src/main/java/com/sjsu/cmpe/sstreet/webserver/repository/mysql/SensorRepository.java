package com.sjsu.cmpe.sstreet.webserver.repository.mysql;

import com.sjsu.cmpe.sstreet.webserver.model.Sensor;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Integer> {

    List<Sensor> findBySmartNode(SmartNode smartNode);

    void deleteBySmartNode(SmartNode smartNode);

    List<Sensor> findAllByLocation_StateAndLocation_City(String state, String city);

}
