package com.soo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

/**
 * @author KHS
 */
@ActiveProfiles(value = "test")
@Configuration
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ComponentScan(basePackages = "com.soo", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class))
public class TestConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

}
