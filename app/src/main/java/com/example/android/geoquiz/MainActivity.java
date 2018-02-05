package com.example.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // define the needed variables
    int pointsMegacities, pointsMountainAlp, pointsAswanLocated, pointsEditTextHighestMountain, score = 0;
    String rightAnswerHighestMountain = "mount everest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Methods to check the radio buttons
    public void radioButtonMegacities(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked and get one point for the right answer
        switch (view.getId()) {
            case R.id.megacities_24:
                if (checked)
                    pointsMegacities = 0;
                break;
            case R.id.megacities_38:
                if (checked)
                    pointsMegacities = 0;
                break;
            case R.id.megacities_47:
                if (checked)
                    pointsMegacities = 1;
                break;
            case R.id.megacities_57:
                if (checked)
                    pointsMegacities = 0;
                break;
        }
    }

    public void radioButtonsHighestMountainAlp(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked and get one point for the right answer
        switch (view.getId()) {
            case R.id.highest_mountain_alp_mont_blanc:
                if (checked)
                    pointsMountainAlp = 1;
                break;
            case R.id.highest_mountain_alp_Matterhorn:
                if (checked)
                    pointsMountainAlp = 0;
                break;
            case R.id.highest_mountain_alp_Zugspitze:
                if (checked)
                    pointsMountainAlp = 0;
                break;
            case R.id.highest_mountain_alp_Großglockner:
                if (checked)
                    pointsMountainAlp = 0;
                break;
        }
    }

    public void radioButtonAswanLocated(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked and get one point for the right answer
        switch (view.getId()) {
            case R.id.aswan_located_kongo:
                if (checked)
                    pointsAswanLocated = 0;
                break;
            case R.id.aswan_located_nil:
                if (checked)
                    pointsAswanLocated = 1;
                break;
            case R.id.aswan_located_kasai:
                if (checked)
                    pointsAswanLocated = 0;
                break;
            case R.id.aswan_located_sambesi:
                if (checked)
                    pointsAswanLocated = 0;
                break;
        }
    }

    //Method that is called by clicking the answer Button
    public void displayScore(View view) {

    /*    get the answer from the EditText field and store it in a string to compare it (format the input to a string,
        make the input to lower case, and delete spaces with .trim*/
        EditText userAnswerHighestMountain = (EditText) findViewById(R.id.highest_mountain_world);
        String stringUserAnswerHighestMountain = userAnswerHighestMountain.getText().toString().toLowerCase().trim();

        if (stringUserAnswerHighestMountain.equals(rightAnswerHighestMountain))
            pointsEditTextHighestMountain = 1;
        else
            pointsEditTextHighestMountain = 0;

        //Figure out if the checkBox "Berlin" is clicked and store the value
        CheckBox checkBoxBerlin = (CheckBox) findViewById(R.id.cities_germany_berlin);
        boolean isCheckedBerlin = checkBoxBerlin.isChecked();

        //Figure out if the checkBox "Köln" is clicked and store the value
        CheckBox checkBoxKoeln = (CheckBox) findViewById(R.id.cities_germany_köln);
        boolean isCheckedKoeln = checkBoxKoeln.isChecked();

        //Figure out if the checkBox "Liverpool" is clicked and store the value
        CheckBox checkBoxLiverpool = (CheckBox) findViewById(R.id.cities_germany_liverpool);
        boolean isCheckedLiverpool = checkBoxLiverpool.isChecked();

        //Figure out if the checkBox "Rotterdam" is clicked and store the value
        CheckBox checkBoxRotterdam = (CheckBox) findViewById(R.id.cities_germany_rotterdam);
        boolean isCheckedRotterdam = checkBoxRotterdam.isChecked();

        int pointsCheckBox = calculateCheckBoxPoints(isCheckedBerlin, isCheckedKoeln,
                isCheckedLiverpool, isCheckedRotterdam);

        // add the points for the right answers and show the toast
        score = pointsMegacities + pointsMountainAlp + pointsAswanLocated + pointsEditTextHighestMountain + pointsCheckBox;
        Toast.makeText(this, "Score= " + score + " out of 6", Toast.LENGTH_SHORT).show();
    }

    //Calculate the points for the checkboxes
    private int calculateCheckBoxPoints(boolean isCheckedBerlin, boolean isCheckedKoeln,
                                        boolean isCheckedLiverpool, boolean isCheckedRotterdam) {

        int pointsCheckBoxes = 0;

        //Add 2 Points if both correct answers
        if (isCheckedBerlin && isCheckedKoeln && !isCheckedLiverpool && !isCheckedRotterdam) {
            pointsCheckBoxes = pointsCheckBoxes + 2;
        }

        //Add 1 Points if one correct answer is checked
        else if (isCheckedBerlin && !isCheckedKoeln && !isCheckedLiverpool && !isCheckedRotterdam) {
            pointsCheckBoxes = pointsCheckBoxes + 1;
        }

        //Add 1 Points if one correct answer is checked
        else if (isCheckedKoeln && !isCheckedBerlin && !isCheckedLiverpool && !isCheckedRotterdam) {
            pointsCheckBoxes = pointsCheckBoxes + 1;
        }

        //set points for CheckBoxes to zero if a wrong answer is selected
        else {
            pointsCheckBoxes = 0;
        }

        //calculates the total price
        return pointsCheckBoxes;
    }

    //This Method is called when the reset Button is clicked
    public void resetQuiz(View view) {

        //resets the radiogroups
        RadioGroup radioGroupMegacities = (RadioGroup) findViewById(R.id.radio_Group_Megacities);
        radioGroupMegacities.clearCheck();
        RadioGroup radioGroupAswan = (RadioGroup) findViewById(R.id.radio_group_Aswan);
        radioGroupAswan.clearCheck();
        RadioGroup radioGroupMountainAlp = (RadioGroup) findViewById(R.id.radio_group_mountain_alp);
        radioGroupMountainAlp.clearCheck();

        //resets the check boxes
        CheckBox citiesGermanyBerlin = (CheckBox) findViewById(R.id.cities_germany_berlin);
        citiesGermanyBerlin.setChecked(false);
        CheckBox citiesGermanyRotterdam = (CheckBox) findViewById(R.id.cities_germany_rotterdam);
        citiesGermanyRotterdam.setChecked(false);
        CheckBox citiesGermanyLiverpool = (CheckBox) findViewById(R.id.cities_germany_liverpool);
        citiesGermanyLiverpool.setChecked(false);
        CheckBox citiesGermanykoeln = (CheckBox) findViewById(R.id.cities_germany_köln);
        citiesGermanykoeln.setChecked(false);

        //resets the text input
        EditText userAnswerHighestMountain = (EditText) findViewById(R.id.highest_mountain_world);
        userAnswerHighestMountain.setText("");

        //resets the points
       score= 0;
       pointsAswanLocated = 0;
       pointsMountainAlp = 0;
       pointsMegacities = 0;
       pointsEditTextHighestMountain = 0;

    }
}

