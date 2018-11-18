package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.SensorData;
import com.sjsu.cmpe.sstreet.webserver.model.TemperatureSensorData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataCRepository extends CassandraRepository<SensorData, String> {

    Slice<SensorData> findAllByIdSmartClusterAndIdSmartNode(Integer idSmartCluster, Integer idSmartNode);

}
