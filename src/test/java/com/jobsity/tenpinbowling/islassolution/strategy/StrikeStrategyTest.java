package com.jobsity.tenpinbowling.islassolution.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;
import com.jobsity.tenpinbowling.islassolution.service.RuleEngine;
import com.jobsity.tenpinbowling.islassolution.strategy.impl.StrikeStrategy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StrikeStrategyTest {

    @Autowired StrikeStrategy strikeStrategy;

    @Autowired RuleEngine ruleEngine;

    PlayerScore playerScore;
    Frame frame;


    private PlayerScore getStrikePlayerScore() {
        playerScore = new PlayerScore("testPlayer", 10);
        for (int i = 1; i < 12; i++) {
            ruleEngine.fillScoreInFrame(playerScore, 10);
        }
        return playerScore;
    }

    @BeforeEach void setup() {
        playerScore = getStrikePlayerScore();
        frame = playerScore.getFrames().get(0);

    }

    @Test void testAccumulatedScore() {
        strikeStrategy.calculateAccumulatedScore(playerScore, frame);
        assert frame.getAccumulatedScore().equals(30);
    }

    @Test void testPrintFalls() {
        StringBuffer stringBuffer = new StringBuffer();
        strikeStrategy.printPinFalls(frame, stringBuffer);
        assert stringBuffer.toString().equals("\tX\t");

    }
}
