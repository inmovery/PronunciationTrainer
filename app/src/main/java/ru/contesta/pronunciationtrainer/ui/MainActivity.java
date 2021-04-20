package ru.contesta.pronunciationtrainer.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.contesta.pronunciationtrainer.R;
import ru.contesta.pronunciationtrainer.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            String token = intent.getStringExtra("token");
            // do something more
        }

        // Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //NavController navController = Navigation.findNavController(this,  R.id.fragment);
        //NavigationUI.setupWithNavController(bottomNavigationView, navController);

        // Setting Home Fragment as main fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();

    }

    // Listener nav bar
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedElement = null;

            switch (item.getItemId()){
                case R.id.home:
                    selectedElement = new HomeFragment();
                    break;
                case R.id.settings:
                    selectedElement = new SettingsFragment();
                    break;
                case R.id.profile:
                    selectedElement = new ProfileFragment();
                    break;
            }

            // Begin Transaction
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedElement).commit();

            return true;
        }
    };

}