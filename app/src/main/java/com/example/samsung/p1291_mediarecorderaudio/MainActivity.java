package com.example.samsung.p1291_mediarecorderaudio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = Environment.getExternalStorageDirectory() + "/record.3gpp";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
        releaseRecorder();
    }

    public void onClickBtn(View view) {

        switch (view.getId()) {

            case R.id.btnRecStart:
                releaseRecorder();

                try {
                    File outFile = new File(fileName);
                    if (outFile.exists()) {
                        outFile.delete();
                    }
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setOutputFile(fileName);
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnRecStop:
                if (mediaRecorder != null) {
                    mediaRecorder.stop();
                }
                break;
            case R.id.btnPlayStart:
                releasePlayer();

                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(fileName);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnPlayStop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
        }
    }

    private void releasePlayer() {

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void releaseRecorder() {

        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
