package com.jobsity.tenpinbowling.islassolution.strategy.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.islassolution.model.BonusType;
import com.jobsity.tenpinbowling.islassolution.strategy.Strategy;
import com.jobsity.tenpinbowling.islassolution.strategy.StrategyResolver;

@Service
public class StrategyResolverImpl implements StrategyResolver {

    Map<BonusType,Strategy> strategies = new HashMap<>();

    public StrategyResolverImpl(@Autowired @Qualifier("regularStrategy") Strategy regularStrategy, @Autowired @Qualifier("spareStrategy")  Strategy spareStrategy, @Autowired @Qualifier("strikeStrategy")  Strategy strikeStrategy ) {
        strategies.put(BonusType.NONE, regularStrategy);
        strategies.put(BonusType.SPARE, spareStrategy);
        strategies.put(BonusType.STRIKE, strikeStrategy);
    }

    @Override public Strategy get(BonusType bonusType) {
        return strategies.get(bonusType);
    }
}
