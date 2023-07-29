package com.jobsity.tenpinbowling.islassolution.model;

import java.util.Objects;

public class Frame {

    private Score firstChance;
    private Score secondChance = Score.NONE;
    private Score bonusChance = Score.NONE;

    private Integer accumulatedScore;
    private Integer frameNumber;
    private BonusType bonusType = BonusType.NONE;

    public Frame(Score firstChance) {
        this.firstChance = firstChance;
        if (this.firstChance.equals(Score.STRIKE)) {
            this.bonusType = BonusType.STRIKE;
        }
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    public Score getFirstChance() {
        return firstChance;
    }

    public void setFirstChance(Score firstChance) {
        this.firstChance = firstChance;
    }

    public Score getSecondChance() {
        return secondChance;
    }

    public void setSecondChance(Score secondChance) {
        this.secondChance = secondChance;
        if (this.firstChance.getPoints() + this.secondChance.getPoints() == 10) {
            this.bonusType = BonusType.SPARE;
        }
    }

    public Integer getAccumulatedScore() {
        return accumulatedScore;
    }

    public void setAccumulatedScore(Integer accumulatedScore) {
        this.accumulatedScore = accumulatedScore;
    }

    public Integer getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(Integer frameNumber) {
        this.frameNumber = frameNumber;
    }

    public Score getBonusChance() {
        return bonusChance;
    }

    public void setBonusChance(Score bonusChance) {
        this.bonusChance = bonusChance;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Frame frame = (Frame) o;
        return firstChance == frame.firstChance && secondChance == frame.secondChance && bonusChance == frame.bonusChance
                        && Objects.equals(accumulatedScore, frame.accumulatedScore) && Objects.equals(frameNumber,
                        frame.frameNumber) && bonusType == frame.bonusType;
    }

    @Override public int hashCode() {
        return Objects.hash(firstChance, secondChance, bonusChance, accumulatedScore, frameNumber, bonusType);
    }

    @Override public String toString() {
        return "Frame{" + "firstChance=" + firstChance + ", secondChance=" + secondChance + ", bonusChance=" + bonusChance
                        + ", accumulatedScore=" + accumulatedScore + ", frameNumber=" + frameNumber + ", frameBonus=" + bonusType
                        + '}';
    }

    public Integer getKnockedDownPins() {
        return this.getFirstChance().getPoints() + this.getSecondChance().getPoints() + this.getBonusChance().getPoints();
    }
}
