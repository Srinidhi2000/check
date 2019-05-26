package com.example.android.countheaven;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView;

public class guess extends Activity{

    TextView result = (TextView) findViewById(R.id.result);
    TextView correct = (TextView) findViewById(R.id.win);
    TextView wrong = (TextView) findViewById(R.id.lost);
    TextView noofguess = (TextView) findViewById(R.id.noofguess);
    EditText number = (EditText) findViewById(R.id.guessedage);
    LinearLayout colours = (LinearLayout) findViewById(R.id.colours);
    Button button = (Button) findViewById(R.id.end);
    TextView nogh = (TextView) findViewById(R.id.nogh);
    LinearLayout removing = (LinearLayout) findViewById(R.id.remove);
    ScrollView sv = (ScrollView) findViewById(R.id.sv);
    Button b = (Button) findViewById(R.id.guess);
    MainActivity x;
    int agetrack = x.age2;
    int guesstrack = x.guess2;
    final int j = x.guess2;
    int cnt = 0;int ageno;
    static int nowon=0;static int nolost=0;
    String over = "GAME OVER..You have exhausted the no. of guesses already!";
    String high="That's too long..The sole must be reaped earlier!";
    String low="Oops..You are reaping a young soul!";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        result.setVisibility(View.INVISIBLE);
        correct.setVisibility(View.INVISIBLE);
        wrong.setVisibility(View.INVISIBLE);
        noofguess.setText(String.valueOf("Guess The age to reap to reap the soul\nYou can make " + j + "no. of guesses"));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                colours.setVisibility(View.INVISIBLE);
                String gage = number.getText().toString();
                check(gage);

            }


        });

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String newage = savedInstanceState.getString("newage");
        int no = savedInstanceState.getInt("count");
        int agex=savedInstanceState.getInt("agex");
        int guess1=savedInstanceState.getInt("guesstrack");
        String text=savedInstanceState.getString("text");
        number.setText(newage);
        cnt = no;
        ageno=agex;
        guesstrack=guess1;
        result.setText(text);
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        String text = result.getText().toString();
        String gage = number.getText().toString();
        int no = cnt;
        int guess1=guesstrack;
        int agex=ageno;
        outstate.putString("text",text);
        outstate.putInt("agex",agex);
        outstate.putString("newage", gage);
        outstate.putInt("count", no);
        outstate.putInt("guesstrack",guess1);


    }


    //TO start the game from the beginning
    public void reset(View view) {
        Intent myIntent = new Intent(guess.this, MainActivity.class);
        startActivity(myIntent);

    }

    //To set the backgroung colour based on how close the guessed answer is to the the actual age
    private void checkcolour(int x) {
        int a = agetrack - x;
        if (a > 0) {
            a = a + 0;
        }
        if (a < 0) {
            a = a * (-1);
        }
        /*if difference is
        1.=0:Green
        2.(+/-)10:Bluish-green
        3.(+/-)11 to 25:Yellow
        4.(+/-)26 to 40:Orange
        5.(+/-)41-49:Red
         */
        if (a == 0)
            sv.setBackgroundColor(Color.parseColor("#4caf50"));
        if (a > 0 && a <= 10)
            sv.setBackgroundColor(Color.parseColor("#26a69a"));
        if (a > 10 && a <= 25)
            sv.setBackgroundColor(Color.parseColor("#ffd600"));
        if (a > 25 && a <= 50)
            sv.setBackgroundColor(Color.parseColor("#ff9800"));
        if (a > 51 && a <= 99)
            sv.setBackgroundColor(Color.parseColor("#dd2c00"));
    }

    //If the last guess is wrong this function is called
    private void finishguess(int x) {
        nolost++;
        if (x < agetrack) {
            result.setText(String.valueOf(low + "\n GAME OVER\n Correct Answer=" + agetrack));
        }
        if (x > agetrack) {
            result.setText(String.valueOf(high + "\nGAME OVER \nCorrect Answer=" + agetrack));
        }
        button.setText(String.valueOf("START AGAIN"));
        correct.setVisibility(View.VISIBLE);
        correct.setText(String.valueOf("No.of correct guess= " + nowon));
        wrong.setVisibility(View.VISIBLE);
        wrong.setText(String.valueOf("No. of wrong guess= " + nolost));
        removing.setVisibility(View.GONE);

        return;
    }

    private void check(String gage) {
        if (cnt != 0 && cnt != 1) {
            result.setVisibility(View.VISIBLE);
            result.setText(String.valueOf(over));
            button.setText(String.valueOf("Try Again"));
        }
        if (TextUtils.isEmpty(gage)) {
            number.setError("Please Enter The Age To Reap The Soul");
            number.requestFocus();
            return;
        }
        ageno = Integer.parseInt(gage);
        if (ageno < 1 || ageno > 100) {
            number.setError("Please Enter a no. between 1 and 100");
            number.requestFocus();
            return;
        }

        guesstrack = guesstrack - 1;
        if (guesstrack == 0) {
            cnt = 1;
        }
        nogh.setText(String.valueOf("Guess" + (j - guesstrack)));

        if (ageno >= 1 && ageno <= 100) {
            if (cnt != 0 && cnt != 1) {
                result.setVisibility(View.VISIBLE);
                result.setText(String.valueOf(over));
                button.setText(String.valueOf("START AGAIN"));
            } else {
                result.setVisibility(View.VISIBLE);

                if (ageno == agetrack) {
                    nowon++;
                    checkcolour(ageno);
                    result.setText(String.valueOf("Yay!!You got the right answer.." + "\nCorrect Answer=" + agetrack));
                    correct.setVisibility(View.VISIBLE);
                    correct.setText(String.valueOf("No. of correct guess= " + nowon));
                    wrong.setVisibility(View.VISIBLE);
                    wrong.setText(String.valueOf("No.of wrong guess= " + nolost));
                    button.setText(String.valueOf("START AGAIN"));
                    removing.setVisibility(View.GONE);
                    cnt = 2;

                } else if (ageno < agetrack) {
                    if (cnt == 1) {
                        checkcolour(ageno);
                        finishguess(ageno);
                        cnt = 2;
                    } else {
                        checkcolour(ageno);
                        result.setText(String.valueOf(low));
                    }
                } else if (ageno > agetrack) {
                    if (cnt == 1) {
                        checkcolour(ageno);
                        finishguess(ageno);
                        cnt = 2;

                    } else {
                        checkcolour(ageno);
                        result.setText(String.valueOf(high));
                    }
                }


            }
        }

    }}

