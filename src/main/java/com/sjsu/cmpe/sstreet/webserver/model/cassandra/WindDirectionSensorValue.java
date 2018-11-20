package com.sjsu.cmpe.sstreet.webserver.model.cassandra;

import com.sjsu.cmpe.sstreet.webserver.model.WindDirectionType;

public class WindDirectionSensorValue extends SensorValue  {

    private WindDirectionType direction;

    public WindDirectionSensorValue() {

    }

    public WindDirectionSensorValue(WindDirectionType direction) {

        this.direction = direction;
    }

    public WindDirectionType getDirection() {

        return direction;
    }

    public void setDirection(WindDirectionType direction) {

        this.direction = direction;
    }

}
