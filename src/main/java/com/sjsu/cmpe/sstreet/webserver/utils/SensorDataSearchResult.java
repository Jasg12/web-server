package com.sjsu.cmpe.sstreet.webserver.utils;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

public class SensorDataSearchResult<T> {

    private List<? extends T> content;

    private Integer maxResult;

    private String continuation;

    private String direction;

    public SensorDataSearchResult() {
    }

    public SensorDataSearchResult(List<? extends T> content, Integer maxResult, String continuation, String direction) {
        this.content = content;
        this.maxResult = maxResult;
        this.continuation = continuation;
        this.direction = direction;
    }

    public SensorDataSearchResult(Slice<? extends T> slice, SensorDataSearchQuery searchQuery) {
        this.content = slice.getContent();
        if(slice.nextPageable() instanceof CassandraPageRequest){
            CassandraPageRequest pageRequest = (CassandraPageRequest)slice.nextPageable();
            this.continuation = pageRequest.getPagingState() != null?pageRequest.getPagingState().toString():null;
            this.maxResult = slice.nextPageable().getPageSize();
        } else {
            this.continuation = null;
            this.maxResult = searchQuery.getMaxResult();
        }
        this.direction = searchQuery.getDirection() != null?searchQuery.getDirection().toString():null;
    }

    public List<? extends T> getContent() {
        return content;
    }

    public Integer getMaxResult() {
        return maxResult;
    }

    public String getContinuation() {
        return continuation;
    }

    public String getDirection() {
        return direction;
    }

}
