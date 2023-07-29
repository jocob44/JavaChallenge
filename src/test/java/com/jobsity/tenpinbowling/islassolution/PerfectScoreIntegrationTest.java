package com.jobsity.tenpinbowling.islassolution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.utils.ResultPrinter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest(properties = {"inputPath=positive/perfect.txt"})
public class PerfectScoreIntegrationTest {
    @Autowired ResultPrinter resultPrinter;

    @Autowired @Qualifier("readerHandler") Handler readerHandler;

    Response result;

    @Test void contextLoads() {
    }

    @Test void perfectFileShouldWork() {
        assertDoesNotThrow(() ->  readerHandler.handle(null, null),
                        "Expected to work, since the file is valid");
    }

    @Test void renderingPerfectResultShouldMatch() throws Exception {
        result = readerHandler.handle(null, null);
        assert resultPrinter.print(result).toString().equals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                        + "Carl\n" + "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\t10\t10\n"
                        + "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n");
    }
}
