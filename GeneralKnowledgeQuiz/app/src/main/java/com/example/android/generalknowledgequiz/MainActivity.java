package com.example.android.generalknowledgequiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.android.generalknowledgequiz.R.id.radioButton_Q1_opt3;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // function to return the score value after attempting question 1
    public int check_ques_1() {
        RadioButton radioButton = (RadioButton) findViewById(radioButton_Q1_opt3);
        if (radioButton.isChecked()) {
            return 1;
        } else return 0;
    }

    // function to return the score value after attempting question 2
    public int check_ques_2() {
        CheckBox checkedBoxValue1 = (CheckBox) findViewById(R.id.checkBox_Q2_opt1);
        CheckBox checkedBoxValue2 = (CheckBox) findViewById(R.id.checkBox_Q2_opt2);
        CheckBox checkedBoxValue3 = (CheckBox) findViewById(R.id.checkBox_Q2_opt3);
        CheckBox checkedBoxValue4 = (CheckBox) findViewById(R.id.checkBox_Q2_opt4);
        if (checkedBoxValue4.isChecked()) {
            return 0;
        } else if (checkedBoxValue1.isChecked() && checkedBoxValue2.isChecked() && checkedBoxValue3.isChecked()) {
            return 1;
        } else return 0;
    }

    // function to return the score value after attempting question 3
    public int check_ques_3() {
        EditText textView = (EditText) findViewById(R.id.Q3_edit_text);
        String answer3 = textView.getText().toString().trim();
        if (answer3.equalsIgnoreCase("mica")) {
            return 1;
        } else return 0;
    }

    // function to return the score value after attempting question 4
    public int check_ques_4() {
        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton_Q4_opt2);
        if (radioButton.isChecked()) {
            return 1;
        } else return 0;
    }

    // function to return the score value after attempting question 5
    public int check_ques_5() {
        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton_Q5_opt2);
        if (radioButton.isChecked()) {
            return 1;
        } else return 0;
    }

    //function to display the score on submission of the quiz
    public void show_score(View view) {
        int score = 0;
        EditText textView = (EditText) findViewById(R.id.name_edit_view);
        String name = textView.getText().toString().trim();
        score += check_ques_1();
        score += check_ques_2();
        score += check_ques_3();
        score += check_ques_4();
        score += check_ques_5();

        // to display the score using toast message
        Context context = getApplicationContext();
        Toast.makeText(context, "Your Score is " + Integer.toString(score) + "out of 5", Toast.LENGTH_LONG).show();

        // to forward the score of the quiz using email intent
        String mail_body = "Hi " + name + "!\n" + "Your Score from this Quiz is " + score + " out of 5";
        String mail_subject = "Your Score";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));        // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, mail_body);
        intent.putExtra(Intent.EXTRA_SUBJECT, mail_subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
