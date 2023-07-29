package com.jobsity.tenpinbowling.islassolution.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.model.Request;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.utils.FileReader;
import com.jobsity.tenpinbowling.islassolution.utils.LineReader;

@Component
public class ReadInputHandler implements Handler{

    @Autowired
    private FileReader fileReader;

    @Autowired
    private LineReader lineReader;
    private Handler nextHandler;

    @Autowired @Qualifier("validatorHandler")
    @Override
    public void setNext(Handler handler) {

        nextHandler = handler;
    }

    @Override public Response handle(Request request, Response response) throws Exception{

            request = new Request(fileReader.getInfoFromTxt());
            request.setLines(lineReader.parseContent(request.getInput()));
            return nextHandler.handle(request, response);

    }
}
