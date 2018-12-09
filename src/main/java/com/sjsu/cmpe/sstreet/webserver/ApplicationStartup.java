package com.sjsu.cmpe.sstreet.webserver;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Logger log;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        log.info("Application started");
    }
}
