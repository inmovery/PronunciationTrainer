package ru.contesta.pronunciationtrainer.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.contesta.pronunciationtrainer.R;
import ru.contesta.pronunciationtrainer.api.models.AudioRequest;
import ru.contesta.pronunciationtrainer.api.models.AuthorizationRequest;
import ru.contesta.pronunciationtrainer.api.models.Symbol;
import ru.contesta.pronunciationtrainer.api.responses.AudioResponse;
import ru.contesta.pronunciationtrainer.api.responses.AuthorizationResponse;
import ru.contesta.pronunciationtrainer.api.responses.WordResponse;
import ru.contesta.pronunciationtrainer.api.service.NetworkService;
import ru.contesta.pronunciationtrainer.services.RecordingService;
import ru.contesta.pronunciationtrainer.ui.auth.SignInActivity;
import ru.contesta.pronunciationtrainer.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button testApi;
    private Button startRecord;
    private Button stopRecord;
    private Button playRecord;

    private Button getWord;

    private TextView wordView;

    private MediaRecorder audioRecorder;
    private String outputFilename;

    private String wordData;

    public static final int RECORD_AUDIO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Blue);
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

        startRecord = findViewById(R.id.start_record);
        stopRecord = findViewById(R.id.stop_record);
        playRecord = findViewById(R.id.play_record);
        testApi = findViewById(R.id.api_test);

        getWord = findViewById(R.id.get_word);

        wordView = findViewById(R.id.wordView);

        stopRecord.setEnabled(false);

        outputFilename = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.wav";
        Log.e("OUTPUT_FILENAME", outputFilename);

        startRecord.setOnClickListener(this);
        stopRecord.setOnClickListener(this);
        playRecord.setOnClickListener(this);
        testApi.setOnClickListener(this);
        getWord.setOnClickListener(this);
    }

    private void getWordData(){
        NetworkService.getInstance()
                .getRequestsApi()
                .getWord()
                .enqueue(new Callback<WordResponse>() {
                    @Override
                    public void onResponse(Call<WordResponse> call, Response<WordResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                            String word = response.body().getWord();
                            String phonemes = response.body().getPhonemes();

                            wordData = word;
                            wordView.setText(word + " [" + phonemes + "]");

                        } else {
                            Toast.makeText(MainActivity.this, "request is not correct :(", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WordResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void launchApiTest(){
        Toast.makeText(MainActivity.this, "Test API", Toast.LENGTH_SHORT).show();

        File file = new File(outputFilename);
        RequestBody requestBodyAudio = RequestBody.create(MediaType.parse("audio/wav"), file);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part audio_part = MultipartBody.Part.createFormData("apple_file", file.getName(), requestBody);
        // handleAudioTwo(audio_part, word)

        RequestBody wordRequest = RequestBody.create(MediaType.parse("text/plain"), wordData);

        NetworkService.getInstance()
                .getRequestsApi()
                .handleAudio(wordRequest, requestBodyAudio)
                .enqueue(new Callback<AudioResponse>() {
                    @Override
                    public void onResponse(Call<AudioResponse> call, Response<AudioResponse> response) {
                        if (response.isSuccessful()) {
                            List<Symbol> symbols = response.body().getSymbols();
                            String result = "";

                            for(int i = 0; i < symbols.size();i++){
                                String symbol = symbols.get(i).getSymbol();
                                boolean isCorrect = symbols.get(i).getIsCorrect();
                                result += (symbol + "[" + isCorrect + "]\n");
                            }

                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, "request is not correct :(", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AudioResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });
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

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, RECORD_AUDIO);
            return false;
        } else {
            return true;
        }
    }

    private void onRecord() {
        Intent intent = new Intent(this, RecordingService.class);

        File folder = new File(Environment.getExternalStorageDirectory() + "/MySoundRec");

        if (!folder.exists()) {
            folder.mkdir();
        }

        this.startActivity(intent);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void startRecording() {
        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(AudioFormat.ENCODING_PCM_16BIT);
        audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        audioRecorder.setAudioChannels(1);
        audioRecorder.setAudioEncodingBitRate(128000);
        audioRecorder.setAudioSamplingRate(48000);
        audioRecorder.setOutputFile(outputFilename);

        try {
            audioRecorder.prepare();
        } catch (IOException ioException) {
            Toast.makeText(MainActivity.this, "IOException", Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException illegalStateException) {
            Toast.makeText(MainActivity.this, "IllegalStateException", Toast.LENGTH_SHORT).show();
        }

        audioRecorder.start();

        stopRecord.setEnabled(true);
        Toast.makeText(MainActivity.this, "Recording started", Toast.LENGTH_SHORT).show();
    }

    private void stopRecording() {
        audioRecorder.stop();
        audioRecorder.release();
        audioRecorder = null;
        stopRecord.setEnabled(true);
    }

    private void playRecord() {
        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(outputFilename);
            mediaPlayer.prepare();
            mediaPlayer.start();

            Toast.makeText(MainActivity.this, "Recording started", Toast.LENGTH_SHORT).show();
        } catch (IOException ioException) {
            Toast.makeText(MainActivity.this, "IOException", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_record: {
                if (checkPermission()) {
                    startRecording();
                }
                break;
            }

            case R.id.stop_record: {
                if (checkPermission()) {
                    stopRecording();
                }
                break;
            }

            case  R.id.play_record: {
                if (checkPermission()) {
                    playRecord();
                }
                break;
            }

            case R.id.api_test: {
                launchApiTest();
                break;
            }

            case R.id.get_word: {
                getWordData();
                break;
            }
        }
    }

}