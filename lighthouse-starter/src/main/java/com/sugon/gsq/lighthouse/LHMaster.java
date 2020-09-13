package com.sugon.gsq.lighthouse;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sugon.gsq.lighthouse.master",
        "com.sugon.gsq.lighthouse.core","com.sugon.gsq.lighthouse.db"})
public class LHMaster implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LHMaster.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
