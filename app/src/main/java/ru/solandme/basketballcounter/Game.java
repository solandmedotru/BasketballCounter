package ru.solandme.basketballcounter;

public class Game {

    private int timeCounter;
    private int period;

    public Game() {
        timeCounter = 0;
        period = 1;
    }

    public Game(int timeCounter, int period) {
        this.timeCounter = timeCounter;
        this.period = period;
    }

    public int getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
