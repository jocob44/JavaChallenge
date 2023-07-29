package com.jobsity.tenpinbowling.islassolution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.utils.ResultPrinter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest(properties = {"inputPath=positive/scores.txt"})
public class ValidScoresIntegrationTest {
    @Autowired ResultPrinter resultPrinter;

    @Autowired @Qualifier("readerHandler") Handler readerHandler;

    Response result;

    @Test void contextLoads() {
    }

    @Test void validScoreShouldWork() {
        assertDoesNotThrow(() ->  readerHandler.handle(null, null),
                        "Expected to work, since the file is valid");
    }

    @Test void renderingValidScoreResultShouldMatch() throws Exception {
        result = readerHandler.handle(null, null);
        assert resultPrinter.print(result).toString().equals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n"
                        + "Jeff\n" + "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\t0\t6\t\tX\t\tX\tX\t8\t1\n"
                        + "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" + "John\n"
                        + "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n"
                        + "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n");
    }
}
