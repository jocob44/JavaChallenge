package com.jobsity.tenpinbowling.islassolution.model;

public enum Score {
    NONE(0), CIRO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), STRIKE(10);

    private final int points;

    Score(int levelCode) {
        this.points = levelCode;
    }

    public int getPoints() {
        return points;
    }
    public static Score getScoreFromInt(Integer scoreInt) {
        Score score = null;
        switch (scoreInt) {
        case 0:
            score = Score.CIRO;
            break;
        case 1:
            score = Score.ONE;
            break;
        case 2:
            score = Score.TWO;
            break;
        case 3:
            score = Score.THREE;
            break;
        case 4:
            score = Score.FOUR;
            break;
        case 5:
            score = Score.FIVE;
            break;
        case 6:
            score = Score.SIX;
            break;
        case 7:
            score = Score.SEVEN;
            break;
        case 8:
            score = Score.EIGHT;
            break;
        case 9:
            score = Score.NINE;
            break;
        case 10:
            score = Score.STRIKE;
            break;

        }
        return score;
    }
}
