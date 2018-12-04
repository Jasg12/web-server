package com.sjsu.cmpe.sstreet.webserver.repository.mysql;

import com.sjsu.cmpe.sstreet.webserver.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SmartNodeRepository extends CrudRepository<SmartNode, Integer> {

    SmartNode findByLocation(Location location);

    List<SmartNode> findBySmartCluster(SmartCluster smartCluster);

    Optional<SmartNode> findByName(String name);

    void deleteByName(String name);

    void deleteBySmartCluster(SmartCluster smartCluster);

    List<SmartNode> findAllByLocation_StateAndLocation_City(String state, String city);

    List<SmartNode> findAllBySmartCluster_IdSmartCluster(Integer clusterId);

}
