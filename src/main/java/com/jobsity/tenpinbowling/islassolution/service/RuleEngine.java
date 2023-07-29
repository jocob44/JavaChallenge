package com.jobsity.tenpinbowling.islassolution.service;

import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;

public interface RuleEngine {

    void fillScoreInFrame(PlayerScore playerScore, Integer integer);

    void validateRegularFrameScore(Frame frame);

    void calculateResult(PlayerScore playerScore);
}
