package com.hematite.predictive.search;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.dataprovider.FileDataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PredictiveSearchTreeApplication {

    @Bean
    public DataProvider dataProvider() {
        // TODO file from predictive-search-tree-file module
        return new FileDataProvider("F:\\doc.txt");
    }

    @Bean
    public PredictiveSearchTreeFactory treeFactory() {
        return new PredictiveSearchTreeFactory();
    }

    public static void main(final String[] args) {
        SpringApplication.run(PredictiveSearchTreeApplication.class, args);
    }

}
