package com.sjsu.cmpe.sstreet.webserver.service;


import com.sjsu.cmpe.sstreet.webserver.broker.MirroringServerBroker;
import com.sjsu.cmpe.sstreet.webserver.model.Location;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorStatus;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorStatusByTimestamp;
import com.sjsu.cmpe.sstreet.webserver.repository.cassandra.SensorStatusByTimestampRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.cassandra.SensorStatusRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.LocationRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SmartClusterRepository;
import com.sjsu.cmpe.sstreet.webserver.repository.mysql.SmartNodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SmartNodeService {

    private SmartNodeRepository smartNodeRepository;
    private SmartClusterRepository smartClusterRepository;
    private SensorStatusRepository sensorStatusRepository;
    private SensorStatusByTimestampRepository sensorStatusByTimestampRepository;
    private SmartClusterService smartClusterService;
    private BrokerService brokerService;
    private LocationRepository locationRepository;


    private ModelMapper modelMapper;

    @Autowired
    public SmartNodeService(
        SmartNodeRepository smartNodeRepository,
        SmartClusterRepository smartClusterRepository,
        SensorStatusRepository sensorStatusRepository,
        SensorStatusByTimestampRepository sensorStatusByTimestampRepository,
        SmartClusterService smartClusterService,
        BrokerService brokerService,
        LocationRepository locationRepository
    ) {

        this.smartNodeRepository = smartNodeRepository;
        this.smartClusterRepository = smartClusterRepository;
        this.sensorStatusRepository = sensorStatusRepository;
        this.sensorStatusByTimestampRepository = sensorStatusByTimestampRepository;
        this.smartClusterService = smartClusterService;
        this.brokerService = brokerService;
        this.locationRepository = locationRepository;
    }

    public SmartNode createSmartNode(SmartNode smartNode, Integer idSmartCluster) {
        System.out.print("ID: " + idSmartCluster);
        Optional<SmartCluster> smartCluster = smartClusterRepository.findById(idSmartCluster);
        if(!smartCluster.isPresent()) {

            return null;
        }
        smartNode.setSmartCluster(smartCluster.get());
        Location location = smartNode.getLocation();
        location = locationRepository.save(location);

        return smartNodeRepository.save(smartNode);
    }

    public ResponseEntity<String> updateSmartNode(SmartNode smartNode){



        Optional<SmartNode> smartNodeResult = smartNodeRepository.findById(smartNode.getIdSmartNode());

        smartNodeResult.ifPresent(result->{
            smartNode.setName(result.getName());
            smartNode.setMake(result.getMake());
            smartNode.setModel(result.getModel());
            smartNode.setInstallationDate(result.getInstallationDate());
            smartNode.setSmartCluster(result.getSmartCluster());

        });

        if(smartNodeResult.isPresent()){

            if(null != smartNodeRepository.save(smartNode)){
                return ResponseEntity.ok("Smart Node updated");

            }else{
                return new ResponseEntity<>("Smart Node  Failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }else{
            return new ResponseEntity<>("Smart Node with ID: " + smartNode.getIdSmartNode()+" does not exist", HttpStatus.BAD_REQUEST);
        }

    }

    public List<SmartNode> getAllSmartNodes(){

        Iterable<SmartNode> smartNodeIterable = smartNodeRepository.findAll();
        List<SmartNode> smartNodeList  = new ArrayList<>();

        smartNodeIterable.forEach(smartNode ->
            smartNodeList.add(modelMapper.map(smartNode, SmartNode.class))

        );

        return smartNodeList;
    }

    public SmartNode getSmartNodeById(Integer id){

        Optional<SmartNode> smartNodeOptional = smartNodeRepository.findById(id);
        List<SmartNode> smartNode = new ArrayList<>();

        if(!smartNodeOptional.isPresent()) {

            return null;
        }


        return smartNode.get(0);

    }

    public SmartNode getSmartNodeByName(String name){

        Optional<SmartNode> smartNodeOptional = smartNodeRepository.findByName(name);
        List<SmartNode> smartNodeList = new ArrayList<>();

        if(!smartNodeOptional.isPresent()) {

            return null;
        }

        smartNodeOptional.ifPresent(smartNode ->
                smartNodeList.add(modelMapper.map(smartNode,SmartNode.class))

        );

        return smartNodeList.get(0);

    }

    public SmartNode getSmartNodeByLocation(Location location){

        SmartNode smartNode = smartNodeRepository.findByLocation(location);

        return modelMapper.map(smartNode, SmartNode.class);


    }

    public List<SmartNode> getSmartNodeBySmartCluster(SmartCluster smartCluster) {

        return smartNodeRepository.findBySmartCluster(smartCluster);

    }

    public ResponseEntity<String> deleteSmartNodeById(Integer id){

        smartNodeRepository.deleteById(id);

        return ResponseEntity.ok("Smart Node Successfully Deleted");

    }

    public ResponseEntity<String> deleteSmartNodeByName(String name){

        smartNodeRepository.deleteByName(name);

        return ResponseEntity.ok("Smart Node Successfully Deleted");

    }

    public ResponseEntity<String> deleteSmartNodeBySmartCluster(SmartCluster smartCluster){

        smartNodeRepository.deleteBySmartCluster(smartCluster);

        return ResponseEntity.ok("Smart Node Successfully Deleted");
    }

    public List<SmartNode> getUnregisteredNodes(Integer clusterId){

        SmartCluster cluster = smartClusterService.getSmartClusterById(clusterId);
        MirroringServerBroker broker = brokerService.getClusterBroker(cluster);

        return broker.getUnregisteredNodes();
    }

    public SmartNode registerNode(SmartNode smartNode){
        SmartNode registeredNode = createSmartNode(smartNode, smartNode.getSmartCluster().getIdSmartCluster());
        MirroringServerBroker clusterBroker = brokerService.getClusterBroker(registeredNode.getSmartCluster());
        clusterBroker.nodeRegisteredEvent(registeredNode);

        return registeredNode;
    }

    public List<SmartNode> getNodesByClusterId(Integer clusterId){

        return smartNodeRepository.findAllBySmartCluster_IdSmartCluster(clusterId);
    }
}
