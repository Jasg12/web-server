package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SmartClusterStatus;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface SmartClusterStatusRepository extends CassandraRepository<SmartClusterStatus, Integer> {


}
