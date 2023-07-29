package com.jobsity.tenpinbowling.islassolution.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.model.Request;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.validation.ContentValidator;


@Component
public class ValidatorHandler
                implements Handler {


    private Handler nextHandler;


    @Autowired
    private ContentValidator contentValidator;

    @Autowired @Qualifier("parseHandler") @Override public void setNext(Handler handler) {

        nextHandler = handler;
    }

    @Override public Response handle(Request request, Response response) throws Exception{

        validate(request);
        return nextHandler.handle(request, response);
    }

    private void validate(Request request) {

        contentValidator.validate(request.getLines());
    }

}
