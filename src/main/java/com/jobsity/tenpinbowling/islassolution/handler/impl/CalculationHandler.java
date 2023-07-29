package com.jobsity.tenpinbowling.islassolution.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.model.Request;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.service.RuleEngine;

public class CalculationHandler implements Handler {

    @Autowired RuleEngine ruleEngine;

    @Override
    public void setNext(Handler handler) {
    }

    @Override public Response handle(Request request, Response response) {
        return calculateResult(response);
    }

    private Response calculateResult(Response response) {

        response.getPlayerScore().forEach(playerScore -> ruleEngine.calculateResult(playerScore));
        return  response;
    }
}
