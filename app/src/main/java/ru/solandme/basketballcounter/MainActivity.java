package ru.solandme.basketballcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Team teamOne, teamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialTeams();
    }

    private void initialTeams() {
        teamOne = new Team();
        teamOne.setScore(0);
        teamTwo = new Team();
        teamTwo.setScore(0);
    }
}
