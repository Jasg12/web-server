package com.sjsu.cmpe.sstreet.webserver.service;


import com.sjsu.cmpe.sstreet.webserver.model.Location;
import com.sjsu.cmpe.sstreet.webserver.model.SmartCluster;
import com.sjsu.cmpe.sstreet.webserver.model.SmartNode;
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


    private ModelMapper modelMapper;


    @Autowired
    public SmartNodeService(SmartNodeRepository smartNodeRepository, SmartClusterRepository smartClusterRepository) {
        this.smartNodeRepository = smartNodeRepository;
        this.smartClusterRepository = smartClusterRepository;
        this.modelMapper = new ModelMapper();
    }

    public ResponseEntity<String> createSmartNode(SmartNode smartNode, Integer idSmartCluster) {
        System.out.print("ID: "+idSmartCluster);
        Optional<SmartCluster> smartClusterOptional = smartClusterRepository.findById(idSmartCluster);
        List<SmartCluster> smartCluster = new ArrayList<>();

        if(!smartClusterOptional.isPresent()) {

            return null;
        }

        smartClusterOptional.ifPresent( smartCluster1 ->

                smartCluster.add(smartCluster1)

        );
        System.out.print("ID: "+smartCluster.get(0).getIdSmartCluster());

        smartNode.setSmartCluster(smartCluster.get(0));


        SmartNode savedSmartNode = smartNodeRepository.save(smartNode);

        if(null != savedSmartNode){

            return ResponseEntity.ok("Smart Node Created with ID: "+savedSmartNode.getIdSmartNode());
        }else{

            return new ResponseEntity<>("A Smart Node at requested location already exists", HttpStatus.BAD_REQUEST);
        }

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



}
