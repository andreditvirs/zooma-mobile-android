package com.uwika.zooma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playSoundBg();
    }

    public void goToSound(View v){
        mp.stop();
        mp.release();
        mp = null;

        Intent mainIntent = new Intent(MainActivity.this, SoundActivity.class);
        this.startActivity(mainIntent);
    }

    public void goToStory(View v){
        mp.stop();
        mp.release();
        mp = null;

        Intent mainIntent = new Intent(MainActivity.this, StoryActivity.class);
        this.startActivity(mainIntent);
    }

    public void playSoundBg()
    {
        if(mp != null && mp.isPlaying()){
            mp.stop();
            mp.release();
            mp = null;
        }

        mp = new MediaPlayer();

        Uri myUri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.bg_sound);
        try {
            mp.setDataSource(this, myUri);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
        mp.setLooping(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playSoundBg();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mp.stop();
        mp.release();
        mp = null;
    }
}