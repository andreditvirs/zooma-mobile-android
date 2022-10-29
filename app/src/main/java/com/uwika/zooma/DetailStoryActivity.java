package com.uwika.zooma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class DetailStoryActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextView txtV_title, txtV_subtitle, txtV_text;
    ImageView imgV_cover;
    TextToSpeech tts;
    AppCompatButton cBtn_speak;
    int title, cover, text, isTtsPlayed, isFirstTtsPlayed;
    private final int TTS_SPEAK_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);

        title = getIntent().getExtras().getInt("title");
        cover = getIntent().getExtras().getInt("cover");
        text = getIntent().getExtras().getInt("text");

        txtV_title = findViewById(R.id.txtV_detail_story_title);
        txtV_text = findViewById(R.id.txtV_detail_story_text);
        imgV_cover = findViewById(R.id.imgV_detail_story_cover);
        cBtn_speak = findViewById(R.id.cBtn_detail_story_speak);

        txtV_title.setText(title);
        txtV_text.setText(text);
        imgV_cover.setImageResource(cover);

        tts = new TextToSpeech(this, this);
        tts.setSpeechRate((float) 0.7);
        tts.setPitch((float) 1.8);
        tts.setLanguage(Locale.US);

        this.isTtsPlayed = 0;
    }

    public void startStoryTelling(View v)
    {
        if(this.isTtsPlayed == 0){
            String text = "Cerita " + getResources().getString(this.title) + getResources().getString(this.text);
            System.out.println("CERITA"+text);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            this.isTtsPlayed = 1;

            if(this.isFirstTtsPlayed == 1){
                cBtn_speak.setText("Hentikan Cerita!");
            }else{
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        cBtn_speak.setText("Cerita dimulai dalam 1s");

                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                cBtn_speak.setText("Cerita dimulai dalam 2s");

                                new Handler().postDelayed(new Runnable(){
                                    @Override
                                    public void run() {
                                        cBtn_speak.setText("Cerita dimulai dalam 3s");

                                        new Handler().postDelayed(new Runnable(){
                                            @Override
                                            public void run() {
                                                cBtn_speak.setText("Hentikan Cerita!");
                                            }
                                        }, TTS_SPEAK_LENGTH);
                                    }
                                }, TTS_SPEAK_LENGTH);
                            }
                        }, TTS_SPEAK_LENGTH);

                    }
                }, TTS_SPEAK_LENGTH);
                this.isFirstTtsPlayed = 1;
            }
        }else{
            this.isTtsPlayed = 0;
            tts.stop();

            cBtn_speak.setText("Dengarkan Cerita!");
        }
    }

    @Override
    public void onInit(int i) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tts.shutdown();
        tts = null;
    }
}