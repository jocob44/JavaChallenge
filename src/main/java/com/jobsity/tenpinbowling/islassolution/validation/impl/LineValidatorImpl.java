package com.jobsity.tenpinbowling.islassolution.validation.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.islassolution.utils.LineReader;
import com.jobsity.tenpinbowling.islassolution.validation.LineValidator;

@Service
public class LineValidatorImpl
                implements LineValidator {



    @Value("${column-separator}") private String COLUMN_SEPARATOR;

    @Override
    public void validate(String line) {

        List<String> lineInfo = Arrays.asList(line.split(COLUMN_SEPARATOR));

        if (lineInfo.size() != 2) {
            throw new RuntimeException("The input is not in correct format, the pattern is : {name} tab {Number}");
        }
        int score;

        if(lineInfo.get(1).equals("F")){
            score = 0;
        }else {
            try {
                score =  Integer.valueOf(lineInfo.get(1));
            } catch (NumberFormatException e) {
                throw new RuntimeException("The input is not in correct format, check the score column");
            }
        }

        if(score<0){
            throw new RuntimeException("The input is not in correct format, the score should greater than or equal 0");
        }

        if(score>10){
            throw new RuntimeException("The input is not in correct format, the score should less than or equal 10");
        }
    }
}
