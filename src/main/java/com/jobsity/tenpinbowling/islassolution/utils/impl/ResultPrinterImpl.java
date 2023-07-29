package com.jobsity.tenpinbowling.islassolution.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.strategy.StrategyResolver;
import com.jobsity.tenpinbowling.islassolution.utils.ResultPrinter;

@Service
public class ResultPrinterImpl implements ResultPrinter {

    @Autowired StrategyResolver strategyResolver;

    @Value("${column-separator}") private String COLUMN_SEPARATOR;

    @Value("${line-separator}") private String LINE_SEPARATOR;

    @Override
    public StringBuffer print(Response response){

        StringBuffer result = new StringBuffer();

        result.append("Frame").append(COLUMN_SEPARATOR+COLUMN_SEPARATOR);

        for(int i =1;i<=9;i++){

            result.append(i).append(COLUMN_SEPARATOR+COLUMN_SEPARATOR);
        }
        result.append(10);

        result.append(LINE_SEPARATOR);

        for (PlayerScore playerScore:response.getPlayerScore()) {

            print(playerScore,result);
        }


        return result;

    }

    private void print(PlayerScore playerScore, StringBuffer result) {

        result.append(playerScore.getName()).append(LINE_SEPARATOR);

        result.append("Pinfalls").append(COLUMN_SEPARATOR);
        for (Frame frame: playerScore.getFrames()) {

            strategyResolver.get(frame.getBonusType()).printPinFalls(frame,result);
        }
        result.append(LINE_SEPARATOR);

        result.append("Score").append(COLUMN_SEPARATOR).append(COLUMN_SEPARATOR);
        for (Frame frame: playerScore.getFrames()) {

            strategyResolver.get(frame.getBonusType()).printScore(frame,result);
        }
        result.append(LINE_SEPARATOR);
    }


}
