package com.jobsity.tenpinbowling.islassolution.configuration;

import java.io.File;
import java.io.FileReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jobsity.tenpinbowling.islassolution.handler.Handler;
import com.jobsity.tenpinbowling.islassolution.handler.impl.CalculationHandler;
import com.jobsity.tenpinbowling.islassolution.handler.impl.ParseHandler;
import com.jobsity.tenpinbowling.islassolution.handler.impl.ReadInputHandler;
import com.jobsity.tenpinbowling.islassolution.handler.impl.ValidatorHandler;
import com.jobsity.tenpinbowling.islassolution.strategy.Strategy;
import com.jobsity.tenpinbowling.islassolution.strategy.impl.RegularStrategy;
import com.jobsity.tenpinbowling.islassolution.strategy.impl.SpareStrategy;
import com.jobsity.tenpinbowling.islassolution.strategy.impl.StrikeStrategy;

@Configuration
public class IslasSolutionSpringConfig {

    @Bean
    public Handler readerHandler(){
      return new ReadInputHandler();
    }
    @Bean
    public Handler validatorHandler(){
       return new ValidatorHandler();
    }
    @Bean
    public Handler calculationHandler(){
        return new CalculationHandler();
    }
    @Bean
    public Handler parseHandler(){
        return new ParseHandler();
    }
    @Bean
    public Strategy regularStrategy(){
        return new RegularStrategy();
    }
    @Bean
    public Strategy strikeStrategy(){
        return new StrikeStrategy();
    }
    @Bean
    public Strategy spareStrategy(){
        return new SpareStrategy();
    }
}
