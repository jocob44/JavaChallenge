package com.jobsity.tenpinbowling.islassolution.strategy.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;
import com.jobsity.tenpinbowling.islassolution.strategy.Strategy;

@Service
public class SpareStrategy extends AbstractStrategy implements Strategy {

    @Value("${column-separator}") private String COLUMN_SEPARATOR;

    @Override public void printPinFalls(Frame frame, StringBuffer stringBuffer) {

        stringBuffer.append(frame.getFirstChance().getPoints()).append(COLUMN_SEPARATOR).append("/");

        if(frame.getFrameNumber()<10){
            stringBuffer.append(COLUMN_SEPARATOR);
        }

    }

    @Override public void calculateAccumulatedScore(PlayerScore playerScore, Frame frame) {
        Integer frameSumScoreAux = frame.getKnockedDownPins();
        frameSumScoreAux += playerScore.getFirstThrowPoints(frame.getFrameNumber() + 1);
        frameSumScoreAux += playerScore.getAccumulatedScoreForFrameNumber(frame.getFrameNumber()-1);
        frame.setAccumulatedScore(frameSumScoreAux);
    }
}
