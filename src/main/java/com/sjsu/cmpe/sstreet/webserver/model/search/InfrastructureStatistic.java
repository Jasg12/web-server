package com.sjsu.cmpe.sstreet.webserver.model.search;

public class InfrastructureStatistic {

    private String city;

    private String street;

    private int clusters;

    private int nodes;

    private int sensors;

    public InfrastructureStatistic() {
    }


    public InfrastructureStatistic(String city, String street) {

        this.city = city;
        this.street = street;
        this.clusters = 0;
        this.nodes = 0;
        this.sensors = 0;
    }

    public void increaseClusterCount(){
        clusters++;
    }

    public void increaseNodesCount(){
        nodes++;
    }

    public void increaseSensorsCount(){
        sensors++;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getStreet() {

        return street;
    }

    public void setStreet(String street) {

        this.street = street;
    }

    public int getClusters() {

        return clusters;
    }

    public void setClusters(int clusters) {

        this.clusters = clusters;
    }

    public int getNodes() {

        return nodes;
    }

    public void setNodes(int nodes) {

        this.nodes = nodes;
    }

    public int getSensors() {

        return sensors;
    }

    public void setSensors(int sensors) {

        this.sensors = sensors;
    }
}
