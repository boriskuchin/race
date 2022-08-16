package ru.bvkuchin;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private ArrayList<Stage> stages;

    private boolean hasWinner;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setHasWinner() {
        this.hasWinner = true;

    }

    public boolean isHasWinner() {
        return hasWinner;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}