package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorStatus;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.sql.Timestamp;
import java.util.List;

public interface SensorStatusRepository extends CassandraRepository<SensorStatus,Integer> {


    List<SensorStatus> findFirstByIdSensorAndAndIdSmartNodeAndAndIdSmartCluster(Integer idSensor, Integer idSmartNode, Integer idSmartCluster);

    List<SensorStatus> findByTimestampBeforeAndTimestampAfter(Long timestampBefore, Long timestampAfter);



}
