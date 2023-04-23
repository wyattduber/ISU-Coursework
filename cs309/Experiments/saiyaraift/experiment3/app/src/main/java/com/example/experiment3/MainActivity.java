package com.example.experiment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected ImageButton eightB;

    protected TextView answerTv;

    //array of all possible responses
    protected String[] answersArr = {"It is certain", "It is decidedly so", "Without a doubt", "Yes definitely", "You may rely on it", "As I see it, yes",
            "Most likely", "Outlook good", "Yes", "Signs point to yes", "Reply hazy try again", "Ask again later",
            "Better not tell you now", "Cannot predict now", "Concentrate and ask again", "Don't count on it",
            "My reply is no", "My sources say no", "Outlook not so good", "Very doubtful"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//assigns the variables to the actual elements
        eightB = findViewById(R.id.eightBall);
        answerTv = findViewById(R.id.answer);

        //uses the override function
      eightB.setOnClickListener((View.OnClickListener) this);

    }

    //specifies the on click so it causes the answer text view to change
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.eightBall:
                //randomly chooses a spot in the array and displays corresponding message
                int rand = new Random().nextInt(answersArr.length);
                answerTv.setText(answersArr[rand]);

                break;
        }
    }
}