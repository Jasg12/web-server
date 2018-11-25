package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SmartNodeStatus;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface SmartNodeStatusRepository extends CassandraRepository<SmartNodeStatus, Integer> {
}
