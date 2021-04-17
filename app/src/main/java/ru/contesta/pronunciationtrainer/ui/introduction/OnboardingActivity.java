package ru.contesta.pronunciationtrainer.ui.introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.contesta.pronunciationtrainer.R;
import ru.contesta.pronunciationtrainer.ui.MainActivity;

public class OnboardingActivity extends AppCompatActivity {

    Button completeOnboarding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        completeOnboarding = findViewById(R.id.complete_onboarding);

        completeOnboarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToOnboarding = new Intent(OnboardingActivity.this, MainActivity.class);
                startActivity(intentToOnboarding);
            }
        });
    }
}