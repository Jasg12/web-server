package com.sjsu.cmpe.sstreet.webserver.repository.mysql;

import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartClusterRepository extends CrudRepository<SmartCluster, Integer> {

}
