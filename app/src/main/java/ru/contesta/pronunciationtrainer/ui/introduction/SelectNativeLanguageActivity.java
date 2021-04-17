package ru.contesta.pronunciationtrainer.ui.introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.contesta.pronunciationtrainer.R;

public class SelectNativeLanguageActivity extends AppCompatActivity {

    Button englishChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_native_language);

        englishChoice = findViewById(R.id.english_choice);

        englishChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSelectMotivationActivity = new Intent(SelectNativeLanguageActivity.this, SelectMotivationActivity.class);
                startActivity(intentToSelectMotivationActivity);
            }
        });
    }
}