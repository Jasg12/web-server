package com.sjsu.cmpe.sstreet.webserver.repository.cassandra;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjsu.cmpe.sstreet.webserver.model.cassandra.SensorValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class SensorValueReadingConverter implements Converter<String, SensorValue> {

    @Override
    public SensorValue convert(String sensorValue) {
        try{
            ObjectMapper om = new ObjectMapper();
            JsonNode valueJson = om.readTree(sensorValue);
            String className = valueJson.get("classs").asText();
            Class classs = Class.forName(className);
            SensorValue value = (SensorValue)om.readValue(sensorValue, classs);

            return value;
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;
    }
}
