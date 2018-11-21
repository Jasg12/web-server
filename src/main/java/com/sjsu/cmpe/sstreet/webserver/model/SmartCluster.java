package com.sjsu.cmpe.sstreet.webserver.model;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;

@Entity
@Table(name = "smart_cluster")
public class SmartCluster {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idSmartCluster;

    private String name;

    private String model;

    private String make;

    private Date installationDate;

    private URL url;

    @OneToOne
    @JoinColumn(name="location_idlocation", unique= true, nullable=true, insertable=true, updatable=true)
    private Location location;

    public SmartCluster(
        String name,
        String model,
        String make,
        Date installationDate,
        Location location
    ) {

        this.name = name;
        this.model = model;
        this.make = make;
        this.installationDate = installationDate;
        this.location = location;
    }

    public SmartCluster() {

    }

    public Integer getIdSmartCluster() {

        return idSmartCluster;
    }

    public void setIdSmartCluster(Integer idSmartCluster) {

        this.idSmartCluster = idSmartCluster;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getModel() {

        return model;
    }

    public void setModel(String model) {

        this.model = model;
    }

    public String getMake() {

        return make;
    }

    public void setMake(String make) {

        this.make = make;
    }

    public Date getInstallationDate() {

        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {

        this.installationDate = installationDate;
    }

    public Location getLocation() {

        return location;
    }

    public void setLocation(Location location) {

        this.location = location;
    }


    public URL getUrl() {

        return url;
    }

    public void setUrl(URL url) {

        this.url = url;
    }
}
