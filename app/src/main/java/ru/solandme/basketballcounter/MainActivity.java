package ru.solandme.basketballcounter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
    CountdownView mCvCountdownView;

    boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTeamOne = (TextView) findViewById(R.id.scoreTeamOne);
        scoreTeamTwo = (TextView) findViewById(R.id.scoreTeamTwo);
        teamOneName = (EditText) findViewById(R.id.teamNameOne);
        teamTwoName = (EditText) findViewById(R.id.teamNameTwo);
        initialGame();
    }

    private void initialTimer() {
        mCvCountdownView = (CountdownView) findViewById(R.id.cv_countdownViewTest211);
    }

    private void initialGame() {
        game = new Game();
        initialTeams();
        initialTimer();
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
        outState.putLong("timer", game.getTimeCounter());
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

    public void onClickBtn(View view) {

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
            case R.id.arrowUpOneMin:
                updateCountDownTimer(game, 60 * 1000);
                break;
            case R.id.arrowDownOneMin:
                updateCountDownTimer(game, -60 * 1000);
                break;
            case R.id.arrowUpTenMin:
                updateCountDownTimer(game, 10 * 60 * 1000);
                break;
            case R.id.arrowDownTenMin:
                updateCountDownTimer(game, -10 * 60 * 1000);
                break;
            case R.id.btnStart:
                if (isTimerRunning) {
                    mCvCountdownView.stop();
                    isTimerRunning = false;
                    game.setTimeCounter(mCvCountdownView.getRemainTime());
                } else {
                    if (game.getTimeCounter() != 0) {
                        mCvCountdownView.start(game.getTimeCounter());
                        isTimerRunning = true;
                        mCvCountdownView.setOnCountdownEndListener(this);
                    }
                }
                break;
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

    private void updateCountDownTimer(Game game, long time) {
        long currentTimeCounter = game.getTimeCounter();
        if (currentTimeCounter + time >= 0) {
            game.setTimeCounter(currentTimeCounter + time);
        }

        mCvCountdownView.updateShow(game.getTimeCounter());
        if (isTimerRunning) {
            mCvCountdownView.start(game.getTimeCounter());
        }
    }

    @Override
    public void onEnd(CountdownView cv) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Time is Up!")
                .setMessage("The time is up. Add time if you want to continue the game!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
