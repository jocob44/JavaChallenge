package com.jobsity.tenpinbowling.islassolution.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.jobsity.tenpinbowling.islassolution.model.BonusType;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;


import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RuleEngineTest {

    @Autowired
    RuleEngine ruleEngine;

    PlayerScore playerScore;

    private PlayerScore getPerfectPlayerScore(){
        playerScore = new PlayerScore("testPlayer",10);
        for(int i =1; i<12;i++) {
            ruleEngine.fillScoreInFrame(playerScore, 10);
        }
        return playerScore;
    }
    @Test
    void whenFillScoreInFirstFrame(){

        playerScore = new PlayerScore("testPlayer",1);
        ruleEngine.fillScoreInFrame(playerScore,2);
        assert playerScore.getFrames().get(0).getSecondChance().getPoints()==(2);
        assert playerScore.getFrames().size() == 1;
        assert playerScore.getFrames().get(0).getBonusType().equals(BonusType.NONE);
    }

    @Test
    void whenFillScoreInFirstFrameWithSpark(){

        playerScore = new PlayerScore("testPlayer",1);
        ruleEngine.fillScoreInFrame(playerScore,9);
        assert playerScore.getFrames().get(0).getBonusType().equals(BonusType.SPARE);
        assert playerScore.getFrames().size() == 1;
    }

    @Test
    void whenFillScoreInFirstFrameWithStrike(){

        playerScore = new PlayerScore("testPlayer",10);
        ruleEngine.fillScoreInFrame(playerScore,9);
        assert playerScore.getFrames().get(0).getBonusType().equals(BonusType.STRIKE);
        assert playerScore.getFrames().size() == 2;
    }

    @Test
    void whenFillScoreWithTwelveStrike(){

        playerScore = getPerfectPlayerScore();

        for(int i =0; i<10;i++) {
            assert playerScore.getFrames().get(i).getBonusType().equals(BonusType.STRIKE);
        }
        assert playerScore.getFrames().size() == 10;
    }

    @Test
    void whenFillScoreWith13thStrikeFail(){


        playerScore = getPerfectPlayerScore();

        RuntimeException thrown =  assertThrows(
                        RuntimeException.class,
                        () ->ruleEngine.fillScoreInFrame(playerScore, 10),
                        "Expected to fail, since frame information are full, but it didn't"
        );
        assert thrown.getMessage().contains("The input is not in correct format, more than expected scores");
    }


    @Test
    void whenCalculateWith12Strike(){
        playerScore = getPerfectPlayerScore();
        ruleEngine.calculateResult(playerScore);
        assert playerScore.getAccumulatedScoreForFrameNumber(10).equals(300);
    }

    @Test
    void whenCalculateShouldCiro(){
        playerScore = new PlayerScore("testPlayer",0);
        ruleEngine.calculateResult(playerScore);
        assert playerScore.getAccumulatedScoreForFrameNumber(1).equals(0);
    }
    @Test
    void whenCalculateShouldCiroAllFrames(){

        playerScore = new PlayerScore("testPlayer",0);
        for(int i =0; i<20;i++) {
            ruleEngine.fillScoreInFrame(playerScore, 0);
        }

        ruleEngine.calculateResult(playerScore);

        for (int i =1; i<11;i++){
            assert playerScore.getAccumulatedScoreForFrameNumber(i).equals(0);
        }

    }

    @Test
    void whenCalculateAddTwentyOneFailsThrowsFail(){

        playerScore = new PlayerScore("testPlayer",0);
        for(int i =0; i<20;i++) {
            ruleEngine.fillScoreInFrame(playerScore, 0);
        }

        RuntimeException thrown =  assertThrows(
                        RuntimeException.class,
                        () ->ruleEngine.fillScoreInFrame(playerScore, 0),
                        "Expected to fail, since frame information are full, but it didn't"
        );
        assert thrown.getMessage().contains("The input is not in correct format, more than expected scores");

    }
}
