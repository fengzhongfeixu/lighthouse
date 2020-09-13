package com.sugon.gsq.lighthouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages = {"com.sugon.gsq.lighthouse.agent"})
public class LHAgent implements CommandLineRunner {

    @Autowired
    private Environment environment;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LHAgent.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
