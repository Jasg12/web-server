package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SmartNodeStatus;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SmartNodeStatusByTimestamp;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface SmartNodeStatusByTimestampRepository extends CassandraRepository<SmartNodeStatusByTimestamp, Long> {
}
