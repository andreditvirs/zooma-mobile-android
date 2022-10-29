package com.uwika.zooma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.IOException;
import java.lang.reflect.Array;

public class StoryActivity extends AppCompatActivity {

    LinearLayout lL_kisah_gajah_dan_semut, lL_kisah_kuda_dan_keledai, lL_kisah_kucing_dan_rubah, lL_kisah_ayam_jago_sombong, lL_kisah_harimau_dan_kancil, lL_kisah_sapi_dan_kerbau;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        lL_kisah_gajah_dan_semut = findViewById(R.id.lL_kisah_gajah_dan_semut);
        lL_kisah_kuda_dan_keledai = findViewById(R.id.lL_kisah_kuda_dan_keledai);
        lL_kisah_kucing_dan_rubah = findViewById(R.id.lL_kisah_kucing_dan_rubah);
        lL_kisah_ayam_jago_sombong = findViewById(R.id.lL_kisah_ayam_jago_sombong);
        lL_kisah_harimau_dan_kancil = findViewById(R.id.lL_kisah_harimau_dan_kancil);
        lL_kisah_sapi_dan_kerbau = findViewById(R.id.lL_kisah_sapi_dan_kerbau);

        setLinearLayoutButton(lL_kisah_gajah_dan_semut, new int[]{R.string.kisah_gajah_dan_semut_title, R.drawable.kisah_gajah_dan_semut, R.string.kisah_gajah_dan_semut_text});
        setLinearLayoutButton(lL_kisah_kuda_dan_keledai, new int[]{R.string.kisah_kuda_dan_keledai_title, R.drawable.kisah_kuda_dan_keledai, R.string.kisah_kuda_dan_keledai_text});
        setLinearLayoutButton(lL_kisah_kucing_dan_rubah, new int[]{R.string.kisah_kucing_dan_rubah_title, R.drawable.kisah_kucing_dan_rubah, R.string.kisah_kucing_dan_rubah_text});
        setLinearLayoutButton(lL_kisah_ayam_jago_sombong, new int[]{R.string.kisah_ayam_jago_sombong_title, R.drawable.kisah_ayam_jago_sombong, R.string.kisah_ayam_jago_sombong_text});
        setLinearLayoutButton(lL_kisah_harimau_dan_kancil, new int[]{R.string.kisah_harimau_dan_kancil_title, R.drawable.kisah_harimau_dan_kancil, R.string.kisah_harimau_dan_kancil_text});
        setLinearLayoutButton(lL_kisah_sapi_dan_kerbau, new int[]{R.string.kisah_sapi_dan_kerbau_title, R.drawable.kisah_sapi_dan_kerbau, R.string.kisah_sapi_dan_kerbau_text});

        playSoundBg();
    }

    public void setLinearLayoutButton(LinearLayout lL, int[] arr){
        lL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(StoryActivity.this, DetailStoryActivity.class);

                int title = arr[0];
                int cover = arr[1];
                int text = arr[2];
                Bundle mBundle = new Bundle();
                mBundle.putInt("title", title);
                mBundle.putInt("cover", cover);
                mBundle.putInt("text", text);
                mainIntent.putExtras(mBundle);

                if(mp != null){
                    mp.stop();
                    mp.release();
                    mp = null;
                }

                StoryActivity.this.startActivity(mainIntent);
            }
        });
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}