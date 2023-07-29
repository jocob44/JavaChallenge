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
import com.jobsity.tenpinbowling.islassolution.strategy.impl.SpareStrategy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpareStrategyTest {

    @Autowired SpareStrategy spareStrategy;

    @Autowired RuleEngine ruleEngine;

    PlayerScore playerScore;
    Frame frame;

    private PlayerScore getSparePlayerScore() {
        playerScore = new PlayerScore("testPlayer", 3);
        for (int i = 1; i < 20; i++) {
            if (Math.floorMod(i, 2) == 0) {
                ruleEngine.fillScoreInFrame(playerScore, 3);
            } else {

                ruleEngine.fillScoreInFrame(playerScore, 7);
            }
        }
        return playerScore;
    }

    @BeforeEach void setup() {
        playerScore = getSparePlayerScore();
        frame = playerScore.getFrames().get(0);

    }

    @Test void testAccumulatedScore() {
        spareStrategy.calculateAccumulatedScore(playerScore, frame);
        assert frame.getAccumulatedScore().equals(13);
    }

    @Test void testPrintFalls() {
        StringBuffer stringBuffer = new StringBuffer();
        spareStrategy.printPinFalls(frame, stringBuffer);
        assert stringBuffer.toString().equals("3\t/\t");

    }
}
