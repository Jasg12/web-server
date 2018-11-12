package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.TemperatureSensorData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureDataRepository extends CassandraRepository<TemperatureSensorData, Integer> {

    Slice<TemperatureSensorData> findAllByIdSmartClusterAndAndIdSensor(Integer idSmartCluster, Integer idSmartNode);
}
