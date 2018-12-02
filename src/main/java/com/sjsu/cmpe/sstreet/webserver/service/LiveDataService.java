package com.sjsu.cmpe.sstreet.webserver.service;

import com.sjsu.cmpe.sstreet.webserver.broker.MirroringServerBroker;
import com.sjsu.cmpe.sstreet.webserver.broker.impl.MirroringServerBrokerImpl;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiveDataService {

    private SmartClusterService smartClusterService;
    private BrokerService brokerService;

    @Autowired
    public LiveDataService(SmartClusterService smartClusterService, BrokerService brokerService) {

        this.smartClusterService = smartClusterService;
        this.brokerService = brokerService;
    }

    public ConnectivityStat getLiveClusterConnectivityStatistic(SmartCluster cluster){

        MirroringServerBroker broker = brokerService.getClusterBroker(cluster);
        ConnectivityStat liveConnectivityStatisticForCluster = broker.getLiveConnectivityStatisticForCluster();
        liveConnectivityStatisticForCluster.setEntity(cluster);

        return liveConnectivityStatisticForCluster;
    }

    public ConnectivityStat getLiveClusterConnectivityStatistic(Integer id){

        SmartCluster cluster = smartClusterService.getSmartClusterById(id);

        return getLiveClusterConnectivityStatistic(cluster);
    }

    public List<ConnectivityStat> getLiveConnectivityByStateAndCity(String state, String city){

        List<ConnectivityStat> result = new ArrayList<>();
        List<SmartCluster> smartClusters = smartClusterService.getSmartClusterByStateAndCity(state, city);
        for (SmartCluster cluster:smartClusters){
            ConnectivityStat connectivityStat = getLiveClusterConnectivityStatistic(cluster);
            result.add(connectivityStat);
        }

        return result;
    }
}
