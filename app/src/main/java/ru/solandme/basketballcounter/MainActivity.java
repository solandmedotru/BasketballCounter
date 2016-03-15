package ru.solandme.basketballcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Team teamOne, teamTwo;
    TextView scoreTeamOne, scoreTeamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTeamOne = (TextView) findViewById(R.id.scoreTeamOne);
        scoreTeamTwo = (TextView) findViewById(R.id.scoreTeamTwo);
        initialTeams();
    }

    public void addPoints(Team team, int points) {
        team.setScore(team.getScore() + points);
    }

    private void initialTeams() {
        teamOne = new Team();
        teamOne.setName(getString(R.string.team_a));
        teamTwo = new Team();
        teamTwo.setName(getString(R.string.team_b));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreTeamA", teamOne.getScore());
        outState.putInt("scoreTeamB", teamTwo.getScore());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState == null) {
            initialTeams();
        } else {
            teamOne.setScore(savedInstanceState.getInt("scoreTeamA"));
            scoreTeamOne.setText(Integer.toString(teamOne.getScore()));
            teamTwo.setScore(savedInstanceState.getInt("scoreTeamB"));
            scoreTeamTwo.setText(Integer.toString(teamTwo.getScore()));
        }
    }

    public void onClickPlusBtn(View view) {
        switch (view.getId()) {
            case R.id.btnPlusThreePointToTeamOne:
                addPoints(teamOne, 3);
                scoreTeamOne.setText(Integer.toString(teamOne.getScore()));
                break;
            case R.id.btnPlusTwoPointToTeamOne:
                addPoints(teamOne, 2);
                scoreTeamOne.setText(Integer.toString(teamOne.getScore()));
                break;
            case R.id.btnPlusOnePointToTeamOne:
                addPoints(teamOne, 1);
                scoreTeamOne.setText(Integer.toString(teamOne.getScore()));
                break;
            case R.id.btnPlusThreePointToTeamTwo:
                addPoints(teamTwo, 3);
                scoreTeamTwo.setText(Integer.toString(teamTwo.getScore()));
                break;
            case R.id.btnPlusTwoPointToTeamTwo:
                addPoints(teamTwo, 2);
                scoreTeamTwo.setText(Integer.toString(teamTwo.getScore()));
                break;
            case R.id.btnPlusOnePointToTeamTwo:
                addPoints(teamTwo, 1);
                scoreTeamTwo.setText(Integer.toString(teamTwo.getScore()));
                break;
            case R.id.btnClearScore:
                teamOne.setScore(0);
                teamTwo.setScore(0);
                scoreTeamOne.setText(Integer.toString(teamOne.getScore()));
                scoreTeamTwo.setText(Integer.toString(teamTwo.getScore()));
                break;

        }


    }
}
