package com.jobsity.tenpinbowling.islassolution.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerScore {

    private String name;

    private List<Frame> frames = new ArrayList<>();

    private String nextPlayer;

    public PlayerScore(String playerName, Integer scoreInt) {
        this.setName(playerName);
        Score score = Score.getScoreFromInt(scoreInt);
        Frame frame = new Frame(score);
        frame.setFrameNumber(1);
        this.getFrames().add(frame);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(String nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Frame getLastFrame() {
        return this.frames.get(this.frames.size() - 1);
    }

    public void addNewFrame(Integer integer) {
        Frame frame = new Frame(Score.getScoreFromInt(integer));
        frame.setFrameNumber(this.getLastFrame().getFrameNumber() + 1);
        this.getFrames().add(frame);
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerScore that = (PlayerScore) o;
        return Objects.equals(name, that.name) && Objects.equals(frames, that.frames) && Objects.equals(nextPlayer,
                        that.nextPlayer);
    }

    @Override public int hashCode() {
        return Objects.hash(name, frames, nextPlayer);
    }

    @Override public String toString() {
        return "PlayerScore{" + "name='" + name + '\'' + ", frames=" + frames + ", nextPlayer='" + nextPlayer + '\'' + '}';
    }

    public Integer getTwoThrowsPoints(Integer frameNumber) {

        Frame frame = this.getFrames().get(frameNumber - 1);

        Integer result = frame.getFirstChance().getPoints();

        if (frame.getSecondChance().equals(Score.NONE)) {
                result += this.getFirstThrowPoints(frameNumber + 1);
        } else {
            result += frame.getSecondChance().getPoints();
        }
        return result;
    }

    public Integer getFirstThrowPoints(Integer frameNumber) {
        return this.getFrames().get(frameNumber - 1).getFirstChance().getPoints();
    }

    public Integer getAccumulatedScoreForFrameNumber(Integer frameNumber) {
        if (frameNumber <= 0) {return 0;}

        return this.getFrames().get(frameNumber - 1).getAccumulatedScore();
    }
}
