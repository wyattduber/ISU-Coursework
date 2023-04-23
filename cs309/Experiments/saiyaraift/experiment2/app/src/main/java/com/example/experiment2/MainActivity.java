package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    protected EditText number1;
    protected EditText number2;

    protected EditText resultLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       number1 = findViewById(R.id.number1);
       number2 = findViewById(R.id.number2);
       resultLabel = findViewById(R.id.resultLabel);

    }
    public void btnAdd(View view) {
        int firstNum = Integer.parseInt(number1.getText().toString());
        int secondNum = Integer.parseInt(number2.getText().toString());

        int sum = firstNum + secondNum;
        resultLabel.setText(String.valueOf(sum));
    }

    public void btnSub(View view){
        int firstNum = Integer.parseInt(number1.getText().toString());
        int secondNum = Integer.parseInt(number2.getText().toString());

        int sum = firstNum - secondNum;
        resultLabel.setText(String.valueOf(sum));
    }
    public void btnMult(View view){
        int firstNum = Integer.parseInt(number1.getText().toString());
        int secondNum = Integer.parseInt(number2.getText().toString());

        int sum = firstNum * secondNum;
        resultLabel.setText(String.valueOf(sum));
    }
    public void btnDiv(View view){
        int firstNum = Integer.parseInt(number1.getText().toString());
        int secondNum = Integer.parseInt(number2.getText().toString());

        int sum = firstNum / secondNum;
        resultLabel.setText(String.valueOf(sum));
    }

}