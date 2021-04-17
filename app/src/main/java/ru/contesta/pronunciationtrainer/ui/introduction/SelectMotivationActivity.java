package ru.contesta.pronunciationtrainer.ui.introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.contesta.pronunciationtrainer.R;

public class SelectMotivationActivity extends AppCompatActivity {

    Button travelsChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_motivation);

        travelsChoice = findViewById(R.id.travels_choice);

        travelsChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToOnboardingActivity = new Intent(SelectMotivationActivity.this, OnboardingActivity.class);
                startActivity(intentToOnboardingActivity);
            }
        });
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intentBackToSelectNativeLanguage = new Intent(SelectMotivationActivity.this, SelectNativeLanguageActivity.class);
            startActivity(intentBackToSelectNativeLanguage);
        } catch (Exception ex) {

        }
    }
}