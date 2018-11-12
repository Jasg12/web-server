package com.sjsu.cmpe.sstreet.webserver.service;

import com.sjsu.cmpe.sstreet.webserver.model.SensorData;
import com.sjsu.cmpe.sstreet.webserver.repository.cassandra.SensorDataCRepository;
import com.sjsu.cmpe.sstreet.webserver.utils.SensorDataSearchQuery;
import com.sjsu.cmpe.sstreet.webserver.utils.SensorDataSearchResult;
import com.sjsu.cmpe.sstreet.webserver.utils.factory.CassandraRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class SensorDataService {

    private SensorDataCRepository sensorDataCRepository;
    private CassandraRepositoryFactory cassandraRepositoryFactory;

    public SensorDataService(
        @Autowired SensorDataCRepository sensorDataCRepository,
        @Autowired CassandraRepositoryFactory cassandraRepositoryFactory
    ) {
        this.sensorDataCRepository = sensorDataCRepository;
        this.cassandraRepositoryFactory = cassandraRepositoryFactory;
    }

    public SensorData save(SensorData sensorData){

        SensorData savedSensorData = sensorDataCRepository.save(sensorData);
        return savedSensorData;
    }

    public SensorDataSearchResult getSensorDataBySensorType(SensorDataSearchQuery searchQuery){
        Slice<SensorData> result = ((SensorDataCRepository)cassandraRepositoryFactory.getRepository(searchQuery.getType()))
            .findAllByIdSmartClusterAndAndIdSensor(searchQuery.getIdSmartCluster(), searchQuery.getIdSmartNode());

        return new SensorDataSearchResult<SensorData>(result, searchQuery);
    }

}
