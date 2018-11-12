package com.sjsu.cmpe.sstreet.webserver.utils.factory;

import com.sjsu.cmpe.sstreet.webserver.model.SensorType;
import com.sjsu.cmpe.sstreet.webserver.repository.cassandra.SensorDataCRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.cassandra.TemperatureDataRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.cassandra.WindDirectionDataRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.cassandra.WindSpeedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;

@Service
public class CassandraRepositoryFactory {

    private TemperatureDataRepository temperatureDataRepository;
    private WindDirectionDataRepository windDirectionDataRepository;
    private WindSpeedDataRepository windSpeedDataRepository;
    private SensorDataCRepository sensorDataCRepository;

    public CassandraRepositoryFactory(
        @Autowired TemperatureDataRepository temperatureDataRepository,
        @Autowired WindDirectionDataRepository windDirectionDataRepository,
        @Autowired WindSpeedDataRepository windSpeedDataRepository,
        @Autowired SensorDataCRepository sensorDataCRepository
    ) {

        this.temperatureDataRepository = temperatureDataRepository;
        this.windDirectionDataRepository = windDirectionDataRepository;
        this.windSpeedDataRepository = windSpeedDataRepository;
        this.sensorDataCRepository = sensorDataCRepository;
    }

    public CassandraRepository getRepository(SensorType type){
        switch (type){
            case TEMPERATURE:
                return temperatureDataRepository;
            case WIND_DIRECTION:
                return windDirectionDataRepository;
            case WIND_SPEED:
                return windSpeedDataRepository;
                default:
                    return sensorDataCRepository;
        }
    }
}
