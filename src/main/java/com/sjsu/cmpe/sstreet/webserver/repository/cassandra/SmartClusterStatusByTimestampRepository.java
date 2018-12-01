package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SmartClusterStatus;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SmartClusterStatusByTimestamp;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface SmartClusterStatusByTimestampRepository extends CassandraRepository<SmartClusterStatusByTimestamp, Long> {


}
