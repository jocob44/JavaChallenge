package com.jobsity.tenpinbowling.islassolution.validation.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.islassolution.dto.LineDto;
import com.jobsity.tenpinbowling.islassolution.model.BonusType;
import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;
import com.jobsity.tenpinbowling.islassolution.model.Response;
import com.jobsity.tenpinbowling.islassolution.model.Score;
import com.jobsity.tenpinbowling.islassolution.utils.LineReader;
import com.jobsity.tenpinbowling.islassolution.validation.ContentValidator;

@Service
public class ContentValidatorImpl
                implements ContentValidator {

    @Autowired LineReader lineReader;

    @Override public void validate(List<LineDto> content) {

        if (content.isEmpty()) {
            throw new RuntimeException("The input is not in correct format, you should include the frame scores");
        }
        validateThrowsPerPlayer(content);
    }

    @Override public void validateThrowsPerPlayer(List<LineDto> content) {
        Integer chanceNumber;
        Map<String, Integer> userThrows = new HashMap<>();

        for (LineDto line : content) {
            if (userThrows.containsKey(line.getName())) {
                chanceNumber = userThrows.get(line.getName());
                chanceNumber++;
                if (chanceNumber > 21) {
                    throw new RuntimeException(
                                    "The input is not in correct format, there are more than 21 throws in a same player");
                }
            } else {
                chanceNumber = 1;
            }
            userThrows.put(line.getName(), chanceNumber);
        }
    }

    @Override public void validateResponse(Response response) {

        if (response.getPlayerScore().isEmpty()) {
            throw new RuntimeException("The input is not in correct format, an empty file is not valid");

        }
        response.getPlayerScore().forEach(this::validateResponse);

    }


    @Override public void validateResponse(PlayerScore playerScore) {

        if (playerScore.getFrames().isEmpty() || playerScore.getFrames().size() != 10) {
            throw new RuntimeException("The input is not in correct format, each player should have data for 10 frame ");

        }

        playerScore.getFrames().forEach(this::validateFrame);

    }

    @Override public void validateFrame(Frame frame) {

        if (frame.getFrameNumber() == 10 && (!frame.getBonusType().equals(BonusType.NONE)) && (
                        frame.getSecondChance().equals(Score.NONE) || frame.getBonusChance().equals(Score.NONE))) {
            throw new RuntimeException(
                            "The input is not in correct format, the frame 10 should have 3 throws for Spare or Strike ");

        }

    }
}
