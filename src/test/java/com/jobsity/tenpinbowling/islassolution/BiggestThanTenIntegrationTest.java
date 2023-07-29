package com.jobsity.tenpinbowling.islassolution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.utils.ResultPrinter;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = {"inputPath=negative/invalid-score-case2.txt"})
class BiggestThanTenIntegrationTest {

    @Autowired ResultPrinter resultPrinter;

    @Autowired @Qualifier("readerHandler") Handler readerHandler;
    @Test void contextLoads() {
    }

    @Test void biggestThanTenShouldFail(){

        RuntimeException thrown =  assertThrows(
                        RuntimeException.class,
                        () ->readerHandler.handle(null, null),
                        "Expected to fail, since the file is not valid"
        );
        assert thrown.getMessage().contains("The input is not in correct format, the score should less than or equal 10");

    }
}
