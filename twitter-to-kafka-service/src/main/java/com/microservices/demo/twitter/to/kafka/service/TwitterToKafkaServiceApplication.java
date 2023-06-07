package com.microservices.demo.twitter.to.kafka.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;

@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
	// We said the current class name while creating the log variable, 
	// so that it will mention this class name during logging.
	private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);
	
	private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
	
	public TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfigData configData) {
        this.twitterToKafkaServiceConfigData = configData;
    }
	
    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }
    
    
    @Override
    public void run(String... args) throws Exception {
    	LOG.info("Application start!"); //we use info level which was set in the logback xml file
    	
    	// convert the list to string array to print it nicely on the console, using arrays to string
    	LOG.info(Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[] {}))); // Log the keywords in the run method by getting the keywords from config data object
        LOG.info(twitterToKafkaServiceConfigData.getWelcomeMessage());
    }
}