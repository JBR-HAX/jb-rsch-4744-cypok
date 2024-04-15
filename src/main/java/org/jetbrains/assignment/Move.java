package org.jetbrains.assignment;

public class Move {
    private String direction;
    private int steps;

    public Move(String direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    // Getters
    public String getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }

    // Setters
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}