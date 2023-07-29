package com.jobsity.tenpinbowling.islassolution.utils;

import java.util.List;

import com.jobsity.tenpinbowling.islassolution.dto.LineDto;

public interface LineReader {

    List<String> parseLine(String content);

    List<LineDto> parseContent(String content);
}
