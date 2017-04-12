package ru.solandme.basketballcounter;

public class Game {

    private long timeCounter;
    private int period;

    public Game() {
        timeCounter = 0;
        period = 1;
    }

    public Game(long timeCounter, int period) {
        this.timeCounter = timeCounter;
        this.period = period;
    }

    public long getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(long timeCounter) {
        this.timeCounter = timeCounter;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
