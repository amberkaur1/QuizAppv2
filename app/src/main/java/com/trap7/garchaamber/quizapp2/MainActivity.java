package com.trap7.garchaamber.quizapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.TimerTask;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView textView, scoreboard;
    EditText editText;
    Timer timer = new Timer();
    TextView time_text;
    double duration = 0.0;
    int qcount = 0;
    int numQuestions = 5;
    DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        textView = findViewById(R.id.textField);
        scoreboard = findViewById(R.id.scoreboard);
        editText = findViewById(R.id.editText);
        //timer = findViewById(R.id.timer);
        time_text = findViewById(R.id.timer);
        startButton.setOnClickListener(new View.OnClickListener(){
            String question = "";
            String answer = "";
            int score = 0;

            public void onClick(View view) {

                if (startButton.getText().equals("Submit Name")) {

                    textView.setText("Hi " + editText.getText() + "!");
                    startButton.setText("Start Game");
                    editText.setText("");
                    editText.setHint("");

                }
                else if (startButton.getText().equals("Next")||startButton.getText().equals("Start Game")){
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time_text.setText("Time: "+df.format(duration));
                                    duration+=0.01;
                                    if(textView.getText().toString().contains("Game Over!"))
                                        timer.cancel();
                                }
                            });
                        }
                    }, 10, 10);
                    if (qcount==numQuestions){
                        textView.setText("Game Over! \n Score: "+score+"\n Time: "+df.format(duration));
                    }
                    else {
                        int a = (int) (Math.random() * 10 + 1); //num 1-10
                        int b = (int) (Math.random() * 10 + 1);
                        int op = (int) (Math.random() * 3 + 1);
                        question = a + " + " + b;
                        switch (op) {
                            case 1:
                                question = a + " + " + b;
                                answer = "" + (a + b);
                                break;
                            case 2:
                                question = a + " - " + b;
                                answer = "" + (a - b);
                                break;
                            case 3:
                                question = a + " * " + b;
                                answer = "" + (a * b);
                                break;
                        }
                        textView.setText(question);
                        editText.setText("");
                        qcount++;
                        editText.setHint("Type your answer here");
                        startButton.setText("Submit Answer");
                    }
                }
               else if (startButton.getText().equals("Submit Answer")){
                    if(editText.getText().toString().trim().equals(answer)){
                        textView.setText("You are correct!");
                        score+=100;
                        scoreboard.setText("Score: "+score);
                    }
                    else{
                        textView.setText("Sorry, that answer is incorrect. The correct answer is "+answer);
                        score-=50;
                        scoreboard.setText("Score: "+score);
                    }
                    startButton.setText("Next");

                }
               else if (startButton.getText().equals("Next")){
                   //add code here
                }
            }
        });
    }
}
