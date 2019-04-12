package com.hematite.predictive.search;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.dataprovider.FileDataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
@EnableNeo4jRepositories
public class PredictiveSearchTreeApplication {

    @Value("${file.filename}")
    private String fileName;

    @Bean
    public DataProvider dataProvider() {
        return new FileDataProvider(fileName);
    }

    @Bean
    public PredictiveSearchTreeFactory treeFactory() {
        return new PredictiveSearchTreeFactory();
    }

    public static void main(final String[] args) {
        SpringApplication.run(PredictiveSearchTreeApplication.class, args);
    }

}
