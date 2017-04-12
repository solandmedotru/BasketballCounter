package ru.solandme.basketballcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.iwgang.countdownview.CountdownView;

public class MainActivity extends AppCompatActivity implements CountdownView.OnCountdownEndListener {

    Game game;
    Team teamOne;
    Team teamTwo;
    TextView scoreTeamOne;
    TextView scoreTeamTwo;
    EditText teamOneName;
    EditText teamTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTeamOne = (TextView) findViewById(R.id.scoreTeamOne);
        scoreTeamTwo = (TextView) findViewById(R.id.scoreTeamTwo);
        teamOneName = (EditText) findViewById(R.id.teamNameOne);
        teamTwoName = (EditText) findViewById(R.id.teamNameTwo);
        initialGame();

        initialTimer();
    }

    private void initialTimer() {
        CountdownView mCvCountdownView = (CountdownView)findViewById(R.id.cv_countdownViewTest1);

        mCvCountdownView.start(5000);
        mCvCountdownView.setOnCountdownEndListener(this);

    }

    private void initialGame() {
        game = new Game();
        initialTeams();
    }

    private void initialTeams() {
        teamOne = new Team();
        teamOne.setName(getString(R.string.team_a));
        teamTwo = new Team();
        teamTwo.setName(getString(R.string.team_b));
    }

    public void addPoints(Team team, int points) {
        team.setScore(team.getScore() + points);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreTeamA", teamOne.getScore());
        outState.putInt("scoreTeamB", teamTwo.getScore());
        outState.putInt("timer", game.getTimeCounter());
        outState.putInt("period", game.getPeriod());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState == null) {
            initialGame();
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
//            case R.id.btnClearScore:
//                teamOne.setScore(0);
//                teamTwo.setScore(0);
//                scoreTeamOne.setText(Integer.toString(teamOne.getScore()));
//                scoreTeamTwo.setText(Integer.toString(teamTwo.getScore()));
//                break;

        }

        teamOneName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    TextKeyListener.clear((teamOneName).getText());
                    teamOne.setName((teamOneName).getText().toString());
                }
            }
        });

        teamTwoName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    TextKeyListener.clear((teamTwoName).getText());
                    teamTwo.setName((teamTwoName).getText().toString());
                }
            }
        });


    }

    @Override
    public void onEnd(CountdownView cv) {
        Toast.makeText(getApplicationContext(), "GameOver", Toast.LENGTH_LONG).show();
    }
}
