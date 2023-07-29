package com.jobsity.tenpinbowling.islassolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.utils.FileReader;
import com.jobsity.tenpinbowling.islassolution.utils.ResultPrinter;

@SpringBootApplication
public class IslasSolutionApplication
                implements CommandLineRunner {

    @Autowired @Qualifier("readerHandler") Handler readerHandler;

    @Autowired ResultPrinter resultPrinter;

    @Autowired FileReader fileReader;

    public static void main(String[] args){

        SpringApplication.run(IslasSolutionApplication.class, args);
    }

    @Override public void run(String... args){

        try {
            Response result = readerHandler.handle(null, null);
            System.out.println(resultPrinter.print(result));

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
