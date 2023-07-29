package com.jobsity.tenpinbowling.islassolution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.utils.ResultPrinter;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = {"inputPath=negative/invalid-score.txt"})
class InvalidScoreIntegrationTest {

    @Autowired ResultPrinter resultPrinter;

    @Autowired @Qualifier("readerHandler") Handler readerHandler;
    @Test void contextLoads() {
    }

    @Test void emptyFileFail(){

        RuntimeException thrown =  assertThrows(
                        RuntimeException.class,
                        () ->readerHandler.handle(null, null),
                        "Expected to fail, since the file is not valid"
        );
        assert thrown.getMessage().contains("The input is not in correct format, check the score column");

    }
}
