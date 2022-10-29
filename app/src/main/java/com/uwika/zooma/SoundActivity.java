package com.uwika.zooma;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;

public class SoundActivity extends AppCompatActivity {

    ImageButton btn_elephant, btn_horse, btn_cat, btn_rooster, btn_tiger, btn_cow;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        btn_elephant = findViewById(R.id.btn_elephant);
        btn_horse = findViewById(R.id.btn_horse);
        btn_cat = findViewById(R.id.btn_cat);
        btn_rooster = findViewById(R.id.btn_rooster);
        btn_tiger = findViewById(R.id.btn_tiger);
        btn_cow = findViewById(R.id.btn_cow);

        setImageButton(btn_elephant, R.raw.elephant);
        setImageButton(btn_horse, R.raw.horse);
        setImageButton(btn_cat, R.raw.cat);
        setImageButton(btn_rooster, R.raw.rooster);
        setImageButton(btn_tiger, R.raw.tiger);
        setImageButton(btn_cow, R.raw.cow);

    }

    public void setImageButton(ImageButton img_button, int raw){
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(raw);
            }
        });
    }

    public void playSound(int raw)
    {
        if(mp != null && mp.isPlaying()){
            mp.stop();
            mp.release();
            mp = null;
        }

        mp = new MediaPlayer();
        Uri myUri = Uri.parse("android.resource://" + SoundActivity.this.getPackageName() + "/" + raw);
        try {
            mp.setDataSource(SoundActivity.this, myUri);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }
}