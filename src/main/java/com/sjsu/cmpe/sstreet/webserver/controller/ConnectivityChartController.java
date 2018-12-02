package com.sjsu.cmpe.sstreet.webserver.controller;

import com.sjsu.cmpe.sstreet.webserver.model.TimeRange;
import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;
import com.sjsu.cmpe.sstreet.webserver.service.SearchStatisticsService;
import com.sjsu.cmpe.sstreet.webserver.service.SmartClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class ConnectivityChartController {
    private SmartClusterService smartClusterService;
    private SearchStatisticsService searchStatisticsService;

    @Autowired
    public ConnectivityChartController(
            SmartClusterService smartClusterService,
            SearchStatisticsService searchStatisticsService
    ) {

        this.smartClusterService = smartClusterService;
        this.searchStatisticsService = searchStatisticsService;
    }



    @RequestMapping(method = RequestMethod.POST ,value= "/connectivity/for/entitytype/{entity}/id/{id}//from/{from}/to/{to}", produces = "application/json")
    public List<ConnectivityStat> getConnectivityStatByTimeRange(
            @PathVariable(value = "id") Integer id,
            @PathVariable("entity") EntityType entityType,
            @PathVariable("from") long from,
            @PathVariable("to") long to) {
        TimeRange timeRange = new TimeRange(from, to);

        return searchStatisticsService.getConnectivityStatByTimeRange(id, entityType, timeRange);
    }
}
