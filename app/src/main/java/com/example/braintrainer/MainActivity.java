package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView resultTextview;
    TextView question;
    TextView score;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    TextView clock;
    ConstraintLayout game;
    int correct_answer;
    int total;
    int correct;
    boolean gameFinished;

    //listener for "GO!" button
    public void game_start(View view){
        goButton.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);
    }

    //listener for all answer buttons
    public void chooseAnswer(View view){
        if(gameFinished){
            return;
        }
        if(view.getTag().toString().equals(Integer.toString(correct_answer))){
            resultTextview.setText("Correct!");
            correct ++;
        }
        else{
            resultTextview.setText("Wrong :<");
        }
        total++;
        score.setText(Integer.toString(correct)+"/"+Integer.toString(total));
        startQuestion();
    }

    //reset the game
    public void resetGame(View view){
        total = 0;
        correct = 0;
        gameFinished = false;
        score.setText("0/0");
        clock.setText("30s");
        playAgain.setVisibility(View.INVISIBLE);
        resultTextview.setText("");
        startQuestion();
        //Timer
        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                clock.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextview.setText("DONE!");
                playAgain.setVisibility(View.VISIBLE);
                gameFinished = true;
            }
        }.start();
    }

    //start a new question
    public void startQuestion(){
        //Get question
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        question.setText(Integer.toString(a) + " + " + Integer.toString(b));
        //Get answer choice
        ArrayList<String> choice = new ArrayList<>();
        correct_answer = random.nextInt(4);
        for(int i=0; i<4; i++){
            int c;
            if(i == correct_answer){
                c = a + b;
            }
            else{
                do{
                    c = random.nextInt(41);
                }while(c == a+b);
            }
            choice.add(Integer.toString(c));
        }
        button0.setText(choice.get(0));
        button1.setText(choice.get(1));
        button2.setText(choice.get(2));
        button3.setText(choice.get(3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Components
        goButton = findViewById(R.id.goButton);
        question = findViewById(R.id.Question_textView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextview = findViewById(R.id.Result_textView);
        score = findViewById(R.id.Score_textView);
        playAgain = findViewById(R.id.playagainButton);
        clock = findViewById(R.id.Time_textView);
        game = findViewById(R.id.Constraintlayout);

        //Set up question
        game.setVisibility(View.INVISIBLE);
        resetGame(goButton);
    }
}