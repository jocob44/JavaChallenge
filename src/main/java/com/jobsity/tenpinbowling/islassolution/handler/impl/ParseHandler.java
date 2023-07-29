package com.jobsity.tenpinbowling.islassolution.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jobsity.tenpinbowling.islassolution.dto.LineDto;
import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;
import com.jobsity.tenpinbowling.islassolution.model.Request;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.service.RuleEngine;
import com.jobsity.tenpinbowling.islassolution.utils.LineReader;
import com.jobsity.tenpinbowling.islassolution.validation.ContentValidator;

@Component
public class ParseHandler
                implements Handler {

    @Autowired LineReader lineReader;

    @Autowired RuleEngine ruleEngine;

    @Autowired ContentValidator contentValidator;

    private Handler nextHandler;

    @Autowired @Qualifier("calculationHandler") @Override public void setNext(Handler handler) {
        nextHandler = handler;
    }


    @Override public Response handle(Request request, Response response) throws Exception{
         response = parseRequestLines(request);

         contentValidator.validateResponse(response);
        return nextHandler.handle(request,response);
    }

    private Response parseRequestLines(Request request) {

        Map<String, PlayerScore> playerIndex = new HashMap<>();

        Response response = new Response();

        for (LineDto line : request.getLines()) {
            String playerName = line.getName();

            Integer score = line.getNumber().equals("F") ? 0 :  Integer.valueOf(line.getNumber());
            PlayerScore playerScore = playerIndex.get(playerName);
            if (playerScore == null) {
                playerScore = new PlayerScore(playerName, score);
                playerIndex.put(playerName, playerScore);
                response.getPlayerScore().add(playerScore);
            }else {
                ruleEngine.fillScoreInFrame(playerScore, score);
            }
        }

        return response;
    }
}
