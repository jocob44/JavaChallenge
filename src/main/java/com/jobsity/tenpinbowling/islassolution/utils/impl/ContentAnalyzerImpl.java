package com.jobsity.tenpinbowling.islassolution.utils.impl;

import java.util.List;

import com.jobsity.tenpinbowling.islassolution.dto.LineDto;
import com.jobsity.tenpinbowling.islassolution.utils.ContentAnalyzer;

public class ContentAnalyzerImpl
                implements ContentAnalyzer {
    @Override public boolean isMultiplayer(List<LineDto> content) {

        String firstPlayer = content.get(0).getName();
        for (LineDto line : content) {
            if (!line.getName().equals(firstPlayer))
                return true;
        }
        return false;
    }
}
