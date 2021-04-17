package ru.contesta.pronunciationtrainer.ui.introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ru.contesta.pronunciationtrainer.R;

public class SplashScreenActivity extends AppCompatActivity {

    Button completeSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        completeSplashScreen = (Button) findViewById(R.id.complete_splash_screen);

        completeSplashScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSelectNativeLanguageActivity = new Intent(SplashScreenActivity.this, SelectNativeLanguageActivity.class);
                startActivity(intentToSelectNativeLanguageActivity);
                Log.d("VALUES_CHANGED", "переход произошёл");
            }
        });
    }
}