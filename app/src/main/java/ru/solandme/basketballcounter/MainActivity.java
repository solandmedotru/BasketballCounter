package ru.solandme.basketballcounter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.TextKeyListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import cn.iwgang.countdownview.CountdownView;

public class MainActivity extends AppCompatActivity implements CountdownView.OnCountdownEndListener {

    private Game game;
    private Team teamOne;
    private Team teamTwo;

    private TextView scoreTeamOne;
    private TextView scoreTeamTwo;
    private TextView teamOneName;
    private TextView teamTwoName;
    private CountdownView mCvCountdownView;

    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTeamOne = (TextView) findViewById(R.id.scoreTeamOne);
        scoreTeamTwo = (TextView) findViewById(R.id.scoreTeamTwo);
        teamOneName = (TextView) findViewById(R.id.teamNameOne);
        teamTwoName = (TextView) findViewById(R.id.teamNameTwo);
        mCvCountdownView = (CountdownView) findViewById(R.id.cv_countdownViewTest211);

        initialGame();
        updateUI();
    }

    private void initialGame() {
        game = new Game();
        initialTeams();
    }

    private void initialTeams() {
        teamOne = new Team(getString(R.string.team_a));
        teamTwo = new Team(getString(R.string.team_b));
    }

    private void updateUI() {
        teamOneName.setText(teamOne.getName());
        teamTwoName.setText(teamTwo.getName());
        scoreTeamOne.setText(String.format(Locale.getDefault(), "%d", teamOne.getScore()));
        scoreTeamTwo.setText(String.format(Locale.getDefault(), "%d", teamTwo.getScore()));
        mCvCountdownView.updateShow(game.getTimeCounter());
    }

    public void addPoints(Team team, int points) {
        if (team.getScore() + points >= 0) {
            team.setScore(team.getScore() + points);
        } else team.setScore(0);
        updateUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        game.setTimeCounter(mCvCountdownView.getRemainTime());
        outState.putParcelable("TeamOne", teamOne);
        outState.putParcelable("TeamTwo", teamTwo);
        outState.putParcelable("Game", game);
        outState.putBoolean("isTimerRunning", isTimerRunning);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState == null) {
            initialGame();
        } else {
            game = savedInstanceState.getParcelable("Game");
            teamOne = savedInstanceState.getParcelable("TeamOne");
            teamTwo = savedInstanceState.getParcelable("TeamTwo");
            isTimerRunning = savedInstanceState.getBoolean("isTimerRunning");

            updateUI();
            if (isTimerRunning) {
                mCvCountdownView.start(game.getTimeCounter());
            }
        }
    }

    public void onClickBtn(View view) {

        switch (view.getId()) {
            case R.id.btnPlusThreePointToTeamOne:
                addPoints(teamOne, 3);
                break;
            case R.id.btnPlusTwoPointToTeamOne:
                addPoints(teamOne, 2);
                break;
            case R.id.btnPlusOnePointToTeamOne:
                addPoints(teamOne, 1);
                break;
            case R.id.btnPlusThreePointToTeamTwo:
                addPoints(teamTwo, 3);
                break;
            case R.id.btnPlusTwoPointToTeamTwo:
                addPoints(teamTwo, 2);
                break;
            case R.id.btnPlusOnePointToTeamTwo:
                addPoints(teamTwo, 1);
                break;
            case R.id.btnMinusThreePointFromTeamOne:
                addPoints(teamOne, -3);
                break;
            case R.id.btnMinusTwoPointFromTeamOne:
                addPoints(teamOne, -2);
                break;
            case R.id.btnMinusOnePointFromTeamOne:
                addPoints(teamOne, -1);
                break;
            case R.id.btnMinusThreePointFromTeamTwo:
                addPoints(teamTwo, -3);
                break;
            case R.id.btnMinusTwoPointFromTeamTwo:
                addPoints(teamTwo, -2);
                break;
            case R.id.btnMinusOnePointFromTeamTwo:
                addPoints(teamTwo, -1);
                break;
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
    }

    private void updateCountDownTimer(Game game, long time) {
        long currentTimeCounter = game.getTimeCounter();
        if (currentTimeCounter + time >= 0) {
            game.setTimeCounter(currentTimeCounter + time);
        } else game.setTimeCounter(0);

        updateUI();
        if (isTimerRunning) {
            mCvCountdownView.start(game.getTimeCounter());
        }
    }

    @Override
    public void onEnd(CountdownView cv) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.time_up)
                .setMessage(R.string.time_up_mesaage)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionReset:
                initialGame();
                updateUI();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void onClickTeamName(View view) {
        switch (view.getId()) {
            case R.id.teamNameOne:
                showEditTeamNameDialog(teamOne);
                break;
            case R.id.teamNameTwo:
                showEditTeamNameDialog(teamTwo);
                break;

        }
    }

    private void showEditTeamNameDialog(final Team team) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.edit_team_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText name = (EditText) dialogView.findViewById(R.id.editName);
        name.setText(team.getName());

        dialogBuilder.setTitle(R.string.team_name);
        dialogBuilder.setMessage(R.string.enter_team_name);
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                team.setName(name.getText().toString());
                updateUI();
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
