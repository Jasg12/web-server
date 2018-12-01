package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorStatus;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorStatusByTimestamp;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface SensorStatusByTimestampRepository extends CassandraRepository<SensorStatusByTimestamp,Long> {


    List<SensorStatusByTimestamp> findByTimestampBeforeAndTimestampAfter(Long timestampBefore, Long timestampAfter);

}
