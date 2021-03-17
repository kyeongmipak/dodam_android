package com.android.dodam.Diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dodam.R;

public class EmotionRegisterActivity extends AppCompatActivity {

    Toolbar toolbar;
    ActionBar actionBar;

    TextView emotion_tv, emotion_date_tv;
    ImageButton emotion_sleep, emotion_frown, emotion_pain, emotion_surprised, emotion_angry, emotion_lovely,
            emotion_sad, emotion_shame, emotion_pleasure, emotion_normal, emotion_bored, emotion_unknown;
    Button emotion_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_register);


        // 액션바 +++++++++++++++++
        toolbar = findViewById(R.id.emotion_RegisterToolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);
        // +++++++++++++++++++++

        // 이미지 버튼 -> 감정
        emotion_tv = findViewById(R.id.emotion_tv);
        emotion_sleep = findViewById(R.id.emotion_sleep);
        emotion_frown = findViewById(R.id.emotion_frown);
        emotion_pain = findViewById(R.id.emotion_pain);
        emotion_surprised = findViewById(R.id.emotion_surprised);
        emotion_angry = findViewById(R.id.emotion_angry);
        emotion_lovely = findViewById(R.id.emotion_lovely);
        emotion_sad = findViewById(R.id.emotion_sad);
        emotion_shame = findViewById(R.id.emotion_shame);
        emotion_pleasure = findViewById(R.id.emotion_pleasure);
        emotion_normal = findViewById(R.id.emotion_normal);
        emotion_bored = findViewById(R.id.emotion_bored);
        emotion_unknown = findViewById(R.id.emotion_unknown);

        emotion_sleep.setOnClickListener(emotionCilckListener);
        emotion_frown.setOnClickListener(emotionCilckListener);
        emotion_pain.setOnClickListener(emotionCilckListener);
        emotion_surprised.setOnClickListener(emotionCilckListener);
        emotion_angry.setOnClickListener(emotionCilckListener);
        emotion_lovely.setOnClickListener(emotionCilckListener);
        emotion_sad.setOnClickListener(emotionCilckListener);
        emotion_shame.setOnClickListener(emotionCilckListener);
        emotion_pleasure.setOnClickListener(emotionCilckListener);
        emotion_normal.setOnClickListener(emotionCilckListener);
        emotion_bored.setOnClickListener(emotionCilckListener);
        emotion_unknown.setOnClickListener(emotionCilckListener);
        // ++++++++++++++++++++++++++++++++++++++++
        emotion_date_tv = findViewById(R.id.emotion_date_tv);
        // 다음으로 넘기기++++++
        emotion_btn = findViewById(R.id.emotion_btn);

        emotion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emotion_tv.getText().equals("")){
                    Toast.makeText(EmotionRegisterActivity.this, "감정이 선택되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(EmotionRegisterActivity.this, DiaryRegisterActivity.class);
                    intent.putExtra("emotion_img", emotion_tv.getText());
                    intent.putExtra("register_date", emotion_date_tv.getText());
                    startActivity(intent);
                }
            }
        });

    }

    View.OnClickListener emotionCilckListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.emotion_sleep:
                    emotion_tv.setText("졸림");
                    break;
                case R.id.emotion_frown:
                    emotion_tv.setText("찡얼");
                    break;
                case R.id.emotion_pain:
                    emotion_tv.setText("아픔");
                    break;
                case R.id.emotion_surprised:
                    emotion_tv.setText("놀람");
                    break;
                case R.id.emotion_angry:
                    emotion_tv.setText("화남");
                    break;
                case R.id.emotion_lovely:
                    emotion_tv.setText("애교");
                    break;
                case R.id.emotion_sad:
                    emotion_tv.setText("슬픔");
                    break;
                case R.id.emotion_shame:
                    emotion_tv.setText("부끄");
                    break;
                case R.id.emotion_pleasure:
                    emotion_tv.setText("기쁨");
                    break;
                case R.id.emotion_normal:
                    emotion_tv.setText("그냥");
                    break;
                case R.id.emotion_bored:
                    emotion_tv.setText("따분");
                    break;
                case R.id.emotion_unknown:
                    emotion_tv.setText("모름");
                    break;
                default:
                    break;
            }
        }
    };




    // 옵션 메뉴
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : // 메뉴 버튼
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}