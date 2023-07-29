package com.jobsity.tenpinbowling.islassolution.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jobsity.tenpinbowling.islassolution.handler.impl.ValidatorHandler;
import com.jobsity.tenpinbowling.islassolution.model.Request;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.utils.LineReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ValidatorHandlerTest {

    @Autowired ValidatorHandler validatorHandler;
    @Autowired LineReader lineReader;


    @Test void whenInputHasLessScores() {
        Request request = new Request("Carl\t10\n" + "Carl\t10\n");
        request.setLines(lineReader.parseContent(request.getInput()));
        RuntimeException thrown = assertThrows(RuntimeException.class,
                        () -> validatorHandler.handle(request, new Response()));
        assert thrown.getMessage().contains("The input is not in correct format, each player should have data for 10 frame");
    }

    @Test void whenInputIsInvalid() {
        Request request = new Request(
                        "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t8\n"
                                        + "Carl\t2\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n"
                                        + "Carl\t10");
        request.setLines(lineReader.parseContent(request.getInput()));
        assertDoesNotThrow(() -> validatorHandler.handle(request, new Response()), "The input is valid, but raise an error");

    }
}
