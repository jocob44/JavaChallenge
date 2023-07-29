package com.jobsity.tenpinbowling.islassolution.strategy;

import com.jobsity.tenpinbowling.islassolution.model.BonusType;

public interface StrategyResolver {

    Strategy get(BonusType bonusType);
}
