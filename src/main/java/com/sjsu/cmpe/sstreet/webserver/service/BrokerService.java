package com.sjsu.cmpe.sstreet.webserver.service;

import com.sjsu.cmpe.sstreet.webserver.broker.MirroringServerBroker;
import com.sjsu.cmpe.sstreet.webserver.broker.impl.MirroringServerBrokerImpl;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import org.springframework.stereotype.Service;

@Service
public class BrokerService {

    public MirroringServerBroker getClusterBroker(SmartCluster cluster){
        return new MirroringServerBrokerImpl(cluster);
    }

}
