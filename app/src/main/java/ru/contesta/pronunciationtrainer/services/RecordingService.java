package ru.contesta.pronunciationtrainer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

public class RecordingService extends Service {

    MediaRecorder mediaRecorder;

    long startingTimeMilliseconds = 0;
    long elapsedTimeMilliseconds = 0;

    File file;

    String fileName;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    private void startRecording() {
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();

        fileName = "audio" + ts;
        file = new File(Environment.getExternalStorageDirectory() + "/MySoundRec/" + fileName + ".mp3");
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mediaRecorder.setOutputFile(file.getAbsoluteFile());
        }

        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioChannels(1);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();

            startingTimeMilliseconds = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        mediaRecorder.stop();
        elapsedTimeMilliseconds = (System.currentTimeMillis() - startingTimeMilliseconds);
        mediaRecorder.release();
        Toast.makeText(getApplicationContext(), "Recording saved " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();

        // add to database
    }

    @Override
    public void onDestroy() {
        if (mediaRecorder != null) {
            stopRecording();
        }

        super.onDestroy();
    }
}
