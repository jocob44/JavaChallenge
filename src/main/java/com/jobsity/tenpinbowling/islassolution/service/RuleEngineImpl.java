package com.jobsity.tenpinbowling.islassolution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.islassolution.model.BonusType;
import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;
import com.jobsity.tenpinbowling.islassolution.model.Score;
import com.jobsity.tenpinbowling.islassolution.strategy.StrategyResolver;

@Service
public class RuleEngineImpl
                implements RuleEngine {

    @Autowired StrategyResolver strategyResolver;


    public void fillScoreInFrame(PlayerScore playerScore, Integer integer) {

        Frame frame = playerScore.getLastFrame();

        if (frame.getFrameNumber().equals(10)) {
            calculateScoreForLastFrame(playerScore, integer);
        } else {
            calculateScoreForRegularFrame(playerScore, integer);
        }

    }

    @Override public void validateRegularFrameScore(Frame frame) {

        if (frame.getFirstChance().getPoints() + frame.getSecondChance().getPoints() > 10) {
            throw new RuntimeException(
                            String.format("The input is not in correct format, the frame %2d have more than 10 points",
                                            frame.getFrameNumber()));
        }
    }


    private void calculateScoreForLastFrame(PlayerScore playerScore, Integer integer) {

        if (playerScore.getLastFrame().getSecondChance().equals(Score.NONE)) {
            playerScore.getLastFrame().setSecondChance(Score.getScoreFromInt(integer));
        } else if (playerScore.getLastFrame().getBonusChance().equals(Score.NONE)) {
            playerScore.getLastFrame().setBonusChance(Score.getScoreFromInt(integer));
        } else {
            throw new RuntimeException(
                            String.format("The input is not in correct format, more than expected scores. User: %s",
                                            playerScore.getName()));
        }
    }

    private void calculateScoreForRegularFrame(PlayerScore playerScore, Integer integer) {

        if (!playerScore.getLastFrame().getSecondChance().equals(Score.NONE) | Score.STRIKE.equals(
                        playerScore.getLastFrame().getFirstChance())) {
            playerScore.addNewFrame(integer);
        } else {
            playerScore.getLastFrame().setSecondChance(Score.getScoreFromInt(integer));
            validateRegularFrameScore(playerScore.getLastFrame());
        }
    }

    @Override public void calculateResult(PlayerScore playerScore) {
        playerScore.getFrames().forEach(frame -> calculateResult(playerScore, frame));

    }

    private void calculateResult(PlayerScore playerScore, Frame frame) {

        if (frame.getFrameNumber() < 10) {
            strategyResolver.get(frame.getBonusType()).calculateAccumulatedScore(playerScore, frame);
        } else {
            strategyResolver.get(BonusType.NONE).calculateAccumulatedScore(playerScore, frame);
        }
    }

}
