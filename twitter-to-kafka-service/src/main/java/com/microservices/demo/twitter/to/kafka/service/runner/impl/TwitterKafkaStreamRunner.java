package com.microservices.demo.twitter.to.kafka.service.runner.impl;

import com.microservices.demo.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import jakarta.annotation.PreDestroy;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.Arrays;

@Component
@ConditionalOnProperty(name = "twitter-to-kafka-service.enable-mock-tweets", havingValue = "false", matchIfMissing = true) // matchIfMissing: So in case this enable-mock-tweets cannot be found in configuration, the original tweet implementation will be loaded and used.
public class TwitterKafkaStreamRunner implements StreamRunner {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TwitterKafkaStatusListener.class);
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private  final TwitterKafkaStatusListener twitterKafkaStatusListener;
    private TwitterStream twitterStream;
    public TwitterKafkaStreamRunner(TwitterToKafkaServiceConfigData configData,
                                    TwitterKafkaStatusListener statusListener){
        this.twitterKafkaStatusListener = statusListener;
        this.twitterToKafkaServiceConfigData = configData;
    }
    @Override
    public void start() throws TwitterException {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterKafkaStatusListener);
        addFilter();
    }
    @PreDestroy
    public void shutdown(){
        if (twitterStream != null){
            LOG.info("Closing twitter stream!");
            twitterStream.shutdown();
        }
    }
    private void addFilter() {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("Started filtering twitter stream for keywords {}", Arrays.toString(keywords));
    }
}
