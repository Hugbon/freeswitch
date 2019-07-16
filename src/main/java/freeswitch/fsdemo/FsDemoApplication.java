package freeswitch.fsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsDemoApplication.class, args);
    }

}
