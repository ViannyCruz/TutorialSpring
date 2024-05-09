package org.donutsti.tutorialspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TutorialSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialSpringApplication.class, args);
    }

}
