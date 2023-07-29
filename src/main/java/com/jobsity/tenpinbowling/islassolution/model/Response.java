package com.jobsity.tenpinbowling.islassolution.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Response {

    List<PlayerScore> playerScore = new ArrayList<>();

    public List<PlayerScore> getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(List<PlayerScore> playerScore) {
        this.playerScore = playerScore;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Response response = (Response) o;
        return Objects.equals(playerScore, response.playerScore);
    }

    @Override public int hashCode() {
        return Objects.hash(playerScore);
    }

    @Override public String toString() {
        return "Response{" + "playerScore=" + playerScore + '}';
    }
}
