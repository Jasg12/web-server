package com.sjsu.cmpe.sstreet.webserver.service;

import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SmartClusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartClusterService {

    private SmartClusterRepository smartClusterRepository;

    public SmartClusterService(@Autowired SmartClusterRepository smartClusterRepository) {

        this.smartClusterRepository = smartClusterRepository;
    }

    public List<SmartCluster> getClustersByStreet(String city, String street){
        return smartClusterRepository.findAllByLocation_CityAndLocation_Street(city, street);
    }

}
