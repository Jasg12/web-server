package com.sjsu.cmpe.sstreet.webserver.repository.mysql;

import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import org.springframework.data.repository.CrudRepository;

public interface SmartNodeRepository extends CrudRepository<SmartNode, Integer> {

}
