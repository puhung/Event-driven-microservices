package com.microservices.demo.twitter.to.kafka.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterKafkaStatusListener extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStatusListener.class);
    @Override
    public void onStatus(Status status) {
        LOG.info("Twitter status with text {}", status.getText()); // This Status class is again a class from twitter4J library and it represents a Twitter object.

    }
}
