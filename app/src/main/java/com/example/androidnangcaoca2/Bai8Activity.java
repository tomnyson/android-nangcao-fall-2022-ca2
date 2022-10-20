package com.example.androidnangcaoca2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Bai8Activity extends AppCompatActivity {
  private ImageButton btnTakeCam, btnPlay;
  private ImageView prevImage;
  private MediaPlayer mediaPlayer;
  boolean isPlay = false;
  public  String audionLink = "https://sachnoiviet.net/audio/du-duyen.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai8);
        btnTakeCam = findViewById(R.id.btnCamera);
        btnTakeCam.setImageResource(R.drawable.ic_baseline_camera_alt_24);
        prevImage = findViewById(R.id.imageView);
        btnTakeCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // check cam
                if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                    Toast.makeText(Bai8Activity.this, "Ok", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 120);
                } else {
                    Toast.makeText(Bai8Activity.this, "No", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPlay = findViewById(R.id.btnPlay);
        mediaPlayer = new MediaPlayer();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(audionLink);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // chua choi nhac
//                if(isPlay) {
//                    mediaPlayer.pause();
//                    btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
//                    isPlay = false;
//                } else {
//                    if(mediaPlayer.isPlaying()) {
//                        mediaPlayer.start();
//                    } {
//                        btnPlay.setImageResource(R.drawable.ic_baseline_pause_circle_24);
//                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                        try {
//                            mediaPlayer.setDataSource(audionLink);
//                        } catch (IOException e) {
//                            try {
//                                mediaPlayer.prepare();
//                                mediaPlayer.start();
//                            } catch (IOException ex) {
//                                ex.printStackTrace();
//                            }
//
//                            e.printStackTrace();
//                        }
//
//                    }
//                    isPlay = true;
                }
                // choi nhac

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 120) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            prevImage.setImageBitmap(photo);
        }
    }
}
