package com.jobsity.tenpinbowling.islassolution.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jobsity.tenpinbowling.islassolution.handler.impl.ParseHandler;
import com.jobsity.tenpinbowling.islassolution.model.Request;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.utils.LineReader;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ParseHandlerTest {

    @Autowired ParseHandler parseHandler;

    @Autowired LineReader lineReader;

    @Test
    void whenRequestIsValidResponseToo(){
        Request request = new Request("Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n"
                        + "Carl\t8\n" + "Carl\t2\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10\n" + "Carl\t10");

        request.setLines(lineReader.parseContent(request.getInput()));

        Assertions.assertDoesNotThrow(()->parseHandler.handle(request,new Response()),"");
    }
}
