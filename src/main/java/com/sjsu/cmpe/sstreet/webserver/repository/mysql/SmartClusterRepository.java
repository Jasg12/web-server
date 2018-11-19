package com.sjsu.cmpe.sstreet.webserver.repository.mysql;

import com.sjsu.cmpe.sstreet.webserver.model.Location;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SmartClusterRepository extends CrudRepository<SmartCluster, Integer> {

    List<SmartCluster> findAllByLocation_CityAndLocation_Street(String city, String street);

    SmartCluster findByLocation(Location location);

    Optional<SmartCluster> findByName(String name);

    void deleteByName(String name);

}
