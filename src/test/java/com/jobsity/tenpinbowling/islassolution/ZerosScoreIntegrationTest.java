package com.jobsity.tenpinbowling.islassolution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.utils.ResultPrinter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest(properties = {"inputPath=positive/zeros.txt"})
public class ZerosScoreIntegrationTest {
    @Autowired ResultPrinter resultPrinter;

    @Autowired @Qualifier("readerHandler") Handler readerHandler;

    Response result;

    @Test void contextLoads() {
    }

    @Test void zerosFileShouldWork() {
        assertDoesNotThrow(() ->  readerHandler.handle(null, null),
                        "Expected to work, since the file is valid");
    }

    @Test void renderingZerosResultShouldMatch() throws Exception {
        result = readerHandler.handle(null, null);
        assert resultPrinter.print(result).toString().equals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                        + "Carl\n" + "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n"
                        + "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n");
    }
}
