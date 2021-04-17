package ru.contesta.pronunciationtrainer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.contesta.pronunciationtrainer.R;
import ru.contesta.pronunciationtrainer.api.responses.AuthorizationResponse;
import ru.contesta.pronunciationtrainer.api.service.NetworkService;
import ru.contesta.pronunciationtrainer.services.RecordingService;

public class AudioTest extends AppCompatActivity implements View.OnClickListener {

    private Button apiTest;

    private Button startRecord;
    private Button stopRecord;
    private Button playRecord;

    private MediaRecorder audioRecorder;
    private String outputFilename;

    public static final int RECORD_AUDIO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_test);

        apiTest = findViewById(R.id.api_test);

        startRecord = findViewById(R.id.start_record);
        stopRecord = findViewById(R.id.stop_record);
        playRecord = findViewById(R.id.play_record);

        stopRecord.setEnabled(false);

        outputFilename = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        Log.e("OUTPUT_FILENAME", outputFilename);

        startRecord.setOnClickListener(this);
        stopRecord.setOnClickListener(this);
        playRecord.setOnClickListener(this);
        apiTest.setOnClickListener(this);

    }

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
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        audioRecorder.setOutputFile(outputFilename);

        try {
            audioRecorder.prepare();
        } catch (IOException ioException) {
            Toast.makeText(AudioTest.this, "IOException", Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException illegalStateException) {
            Toast.makeText(AudioTest.this, "IllegalStateException", Toast.LENGTH_SHORT).show();
        }

        audioRecorder.start();

        stopRecord.setEnabled(true);
        Toast.makeText(AudioTest.this, "Recording started", Toast.LENGTH_SHORT).show();
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

            Toast.makeText(AudioTest.this, "Recording started", Toast.LENGTH_SHORT).show();
        } catch (IOException ioException) {
            Toast.makeText(AudioTest.this, "IOException", Toast.LENGTH_SHORT).show();
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
        }
    }

    private void launchApiTest(){
        /*
        NetworkService.getInstance()
                .getRequestsApi()
                .handleAudio()
                .enqueue(new Callback<AuthorizationResponse>() {
                    @Override
                    public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AudioTest.this, response.body().getAccessToken(), Toast.LENGTH_SHORT).show();
                            AuthorizationResponse authorizationResponse = response.body();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(AudioTest.this, MainActivity.class).putExtra("token", authorizationResponse.getAccessToken()));
                                }
                            }, 700);

                        } else {
                            Toast.makeText(AudioTest.this, "login not correct :(", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                        Toast.makeText(AudioTest.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });
         */
    }
}