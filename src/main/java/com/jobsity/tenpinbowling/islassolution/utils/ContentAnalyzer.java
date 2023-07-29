package com.jobsity.tenpinbowling.islassolution.utils;

import java.util.List;

import com.jobsity.tenpinbowling.islassolution.dto.LineDto;

public interface ContentAnalyzer {

    boolean isMultiplayer(List<LineDto> content);
}
