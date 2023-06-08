package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.init.StreamInitializer;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.demo")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
	// We said the current class name while creating the log variable, 
	// so that it will mention this class name during logging.
	private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    private final StreamRunner streamRunner;
    private final StreamInitializer streamInitializer;

    public TwitterToKafkaServiceApplication(StreamRunner runner, StreamInitializer initializer) {
        this.streamRunner = runner;
        this.streamInitializer = initializer;
    }
	
    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }
    
    
    @Override
    public void run(String... args) throws Exception {
    	LOG.info("Application start!"); //we use info level which was set in the logback xml file
        streamInitializer.init();
        streamRunner.start();
    }
}