package com.jobsity.tenpinbowling.islassolution.utils.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.islassolution.dto.LineDto;
import com.jobsity.tenpinbowling.islassolution.utils.LineReader;
import com.jobsity.tenpinbowling.islassolution.validation.LineValidator;

@Service
public class LineReaderImpl
                implements LineReader {

    @Autowired LineValidator lineValidator;

    @Value("${column-separator}") private String COLUMN_SEPARATOR;

    @Value("${line-separator}") private String LINE_SEPARATOR;

    @Override public List<String> parseLine(String content) {
        return Arrays.asList(content.split(COLUMN_SEPARATOR));
    }

    @Override public List<LineDto> parseContent(String content) {

        return Arrays.stream(content.split(LINE_SEPARATOR)).map(a -> {
            lineValidator.validate(a);
            return parseLine(a);
        }).map(b -> new LineDto(b.get(0), b.get(1))).collect(Collectors.toList());
    }
}
