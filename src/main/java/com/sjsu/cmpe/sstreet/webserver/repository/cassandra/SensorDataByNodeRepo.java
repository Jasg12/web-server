package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorDataByCluster;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorDataByNode;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;

public interface SensorDataByNodeRepo extends CassandraRepository<SensorDataByNode, String> {

    Slice<SensorDataByNode> findAllByIdSmartNode(Integer idSmartNode, Pageable page);

    @Query("SELECT * FROM sensor_data_by_node WHERE idSmartNode = :idSmartNode AND timestamp > :from AND timestamp < :to")
    Slice<SensorDataByNode> findByIdSmartNodeAndTimeRange(@Param("idSmartNode") Integer idSmartNode, @Param("from") Long from, @Param("to") Long to, Pageable page);

}
