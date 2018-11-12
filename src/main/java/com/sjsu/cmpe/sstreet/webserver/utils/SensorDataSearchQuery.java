package com.sjsu.cmpe.sstreet.webserver.utils;

import com.datastax.driver.core.PagingState;
import com.sjsu.cmpe.sstreet.webserver.model.SensorType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SensorDataSearchQuery {

    private Integer idSmartCluster;

    private Integer idSmartNode;

    private SensorType type;

    private Integer maxResult;

    private String continuation;

    private Sort.Direction direction;

    private String sortProperty;

    public SensorDataSearchQuery() {
    }

    public SensorDataSearchQuery(Integer idSmartCluster, Integer idSmartNode, SensorType type, Integer maxResult, String continuation) {

        this.idSmartCluster = idSmartCluster;
        this.idSmartNode = idSmartNode;
        this.type = type;
        this.maxResult = maxResult;
        this.continuation = continuation;
    }

    public Integer getIdSmartCluster() {

        return idSmartCluster;
    }

    public Integer getIdSmartNode() {

        return idSmartNode;
    }

    public SensorType getType() {

        return type;
    }

    public Integer getMaxResult() {
        return maxResult;
    }

    public String getContinuation() {
        return continuation;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public Pageable getPage() {
        Sort sort;
        if(direction == null || sortProperty == null){
            sort = Sort.unsorted();
        } else {
            sort = new Sort(direction, sortProperty);
        }
        CassandraPageRequest page;
        if(StringUtils.isNotEmpty(this.continuation)){
            page = CassandraPageRequest.of(PageRequest.of(1, this.maxResult, sort), PagingState.fromString(this.continuation));
        }else {
            page = CassandraPageRequest.of(0, this.maxResult != null?this.maxResult:50, sort);
        }

        return page;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("SearchQuery - ");
        sb.append("product: ").append(this.idSmartCluster).append(", ");
        sb.append("tenantId: ").append(this.idSmartNode).append(", ");
        sb.append("entityType: ").append(this.type).append(", ");
        sb.append("maxResult: ").append(this.maxResult).append(", ");
        sb.append("direction: ").append(this.direction).append(", ");
        sb.append("sortProperty: ").append(this.sortProperty).append(", ");
        sb.append("continuation: ").append(this.continuation).append(" ");

        return sb.toString();
    }
}
