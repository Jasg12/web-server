package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.statistic.ConnectivityStat;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectivityStatRepository extends CassandraRepository<ConnectivityStat, String> {

    @Query("SELECT * FROM connectivity_stat WHERE id = :id AND entityType = :entityType AND timestamp > :from AND timestamp < :to")
    List<ConnectivityStat> findAllByIdAndEntityTypeAndTimeRange(@Param("id") Integer id, @Param("entityType") String entityType, @Param("from") Long from, @Param("to") Long to);

}
