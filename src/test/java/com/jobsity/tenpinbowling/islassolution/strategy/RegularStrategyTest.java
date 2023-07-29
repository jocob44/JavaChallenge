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
import com.jobsity.tenpinbowling.islassolution.strategy.impl.RegularStrategy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegularStrategyTest {

    @Autowired RegularStrategy regularStrategy;

    @Autowired RuleEngine ruleEngine;

    PlayerScore playerScore;
    Frame frame;

    private PlayerScore getRegularPlayerScore() {
        playerScore = new PlayerScore("testPlayer", 5);
        for (int i = 1; i < 20; i++) {
            if (Math.floorMod(i, 2) == 0) {
                ruleEngine.fillScoreInFrame(playerScore, 5);
            } else {
                ruleEngine.fillScoreInFrame(playerScore, 4);
            }
        }
        return playerScore;
    }

    @BeforeEach
    void setup() {
        playerScore = getRegularPlayerScore();
        frame = playerScore.getFrames().get(0);
    }

    @Test void testAccumulatedScore() {
        regularStrategy.calculateAccumulatedScore(playerScore, frame);
        assert frame.getAccumulatedScore().equals(9);
    }

    @Test void testPrintFalls() {
        StringBuffer stringBuffer = new StringBuffer();
        regularStrategy.printPinFalls(frame, stringBuffer);
        assert stringBuffer.toString().equals("5\t4\t");

    }
}
