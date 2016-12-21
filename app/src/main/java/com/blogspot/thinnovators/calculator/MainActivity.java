package com.blogspot.thinnovators.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.thinnovators.calculator.util.ExpressionEvaluator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewAnswer;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonC, buttonPlusMinus, buttonDivide, buttonMultiply, buttonMinus, buttonPlus, buttonEquals, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    /**
     * A method which initializes all the views present in the layout
     */
    private void initializeViews() {
        textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonPlusMinus = (Button) findViewById(R.id.buttonPlusMinus);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonEquals = (Button) findViewById(R.id.buttonEquals);
        buttonDot = (Button) findViewById(R.id.buttonDot);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonC:
                textViewAnswer.setText("0");
                break;
            case R.id.buttonEquals:
                try {
                    double answer = ExpressionEvaluator.evaluateExpression(textViewAnswer.getText().toString());
                    textViewAnswer.setText(Double.toString(answer));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid Input :-(", Toast.LENGTH_SHORT).show();
                    textViewAnswer.setText("0");
                }
                break;
            default:
                Button current = (Button) v;
                String buttonContent = current.getText().toString();
                String textViewContent = textViewAnswer.getText().toString();
                if (textViewContent.contentEquals("0") || textViewContent.contentEquals("NaN")) {
                    textViewAnswer.setText(buttonContent);
                } else {
                    textViewAnswer.append(buttonContent);
                }
                break;
        }
    }
}
