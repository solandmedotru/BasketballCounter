package ru.solandme.basketballcounter;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.timeCounter);
        dest.writeInt(this.period);
    }

    protected Game(Parcel in) {
        this.timeCounter = in.readLong();
        this.period = in.readInt();
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
