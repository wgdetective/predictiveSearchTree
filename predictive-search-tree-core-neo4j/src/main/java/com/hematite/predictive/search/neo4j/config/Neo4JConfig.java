package com.hematite.predictive.search.neo4j.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = { "com.hematite.predictive.search.neo4j" })
@Configuration
//@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "com.hematite.predictive.search.neo4j.rep")
//@EntityScan(basePackages = "com.hematite.predictive.search.dataprovider.entity")
public class Neo4JConfig {

    @Value("${neo4j.url}")
    private String url;
    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration.Builder().uri(url).build();
        return config;
    }

    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(),
                "com.hematite.predictive.search.neo4j.ent");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(getSessionFactory());
    }
}
