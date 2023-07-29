package com.jobsity.tenpinbowling.islassolution.model;

import java.util.List;

import com.jobsity.tenpinbowling.islassolution.dto.LineDto;

public class Request {

    private final String input;

    private List<LineDto> lines;

    public Request(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public List<LineDto> getLines() {
        return lines;
    }

    public void setLines(List<LineDto> lines) {
        this.lines = lines;
    }
}
