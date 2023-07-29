package com.jobsity.tenpinbowling.islassolution.utils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.islassolution.utils.FileReader;

@Service
public class FileReaderImpl implements FileReader {

    @Value("${inputPath}")
    private String inputPath;

    public String getInfoFromTxt() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputPath);
       return readFromInputStream(inputStream);
    }

    private String readFromInputStream(InputStream inputStream)
                    throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                        = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}
