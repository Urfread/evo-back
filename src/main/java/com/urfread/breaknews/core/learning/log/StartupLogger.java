package com.urfread.breaknews.core.learning.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StartupLogger {

    private static final Logger logger = LoggerFactory.getLogger(StartupLogger.class);

    @PostConstruct
    public void logOnStartup() {
        logger.info("""
                
          
                Spring Boot application has started!
                """);
    }
}
