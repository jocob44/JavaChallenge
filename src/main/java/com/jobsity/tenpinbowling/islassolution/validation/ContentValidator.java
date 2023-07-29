package com.jobsity.tenpinbowling.islassolution.validation;

import java.util.List;

import com.jobsity.tenpinbowling.islassolution.dto.LineDto;
import com.jobsity.tenpinbowling.islassolution.model.Frame;
import com.jobsity.tenpinbowling.islassolution.model.PlayerScore;
import com.jobsity.tenpinbowling.islassolution.model.Response;

public interface ContentValidator {

    void validate(List<LineDto> content);

    void validateThrowsPerPlayer(List<LineDto> content);

    void validateResponse(Response response);

    void validateResponse(PlayerScore playerScore);

    void validateFrame(Frame frame);
}
