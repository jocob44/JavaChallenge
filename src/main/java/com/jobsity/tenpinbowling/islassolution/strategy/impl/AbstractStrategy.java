package com.jobsity.tenpinbowling.islassolution.strategy.impl;

import org.springframework.beans.factory.annotation.Value;

import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.strategy.Strategy;

public abstract class AbstractStrategy implements Strategy {

    @Value("${column-separator}") private String COLUMN_SEPARATOR;

    @Override public void printScore(Frame frame, StringBuffer stringBuffer) {

        if (frame.getFrameNumber()<10) {
            stringBuffer.append(frame.getAccumulatedScore()).append(COLUMN_SEPARATOR).append(COLUMN_SEPARATOR);
        }else {
            stringBuffer.append(frame.getAccumulatedScore());
        }

    }
}
