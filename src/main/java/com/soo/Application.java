package com.soo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author KHS
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(args);
        ConfigurableApplicationContext context = application.run(Application.class);
        //UserController userController = context.getBean(UserController.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
