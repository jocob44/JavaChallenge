package com.jobsity.tenpinbowling.islassolution.strategy;

import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;

public interface Strategy {

    void printPinFalls(Frame frame, StringBuffer stringBuffer);


    void printScore(Frame frame, StringBuffer stringBuffer);

    void calculateAccumulatedScore(PlayerScore playerScore, Frame frame);
}
