package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorStatus;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface SensorStatusRepository extends CassandraRepository<SensorStatus,Integer> {



}
