package com.android.dodam.Diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dodam.R;

public class DiaryRegisterActivity extends AppCompatActivity {

    // 액션바
    Toolbar toolbar;
    ActionBar actionBar;

    TextView diary_emotion_tv, diary_register_date; // 내가 띄울거
    ImageView diary_emotion_img; // 앞에서 내가 선택한 이모지 불러오기 및 이모지 재선택 가능
    EditText diary_register_title; // 내가 입력할 제목

    ImageButton diary_register_album, diary_register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_register);

        // 액션바 설정 ++++++++++++++++++++++++++++++
        toolbar = findViewById(R.id.diary_RegisterToolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);
        // +++++++++++++++++++++++++++++++++++++++

        diary_emotion_tv = findViewById(R.id.diary_emotion_tv);
        diary_register_date = findViewById(R.id.diary_register_date);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        diary_emotion_tv.setText(intent.getStringExtra("emotion_img"));
        diary_register_date.setText(intent.getStringExtra("register_date"));

        // 내가 선택한 감정 이모지 띄우는 곳
        diary_emotion_img = findViewById(R.id.diary_emotion_img);

        // 내가 입력할 제목 및 내용
        diary_register_title = findViewById(R.id.diary_register_title);

        // 하단의 앨범과 입력 버튼
        diary_register_album = findViewById(R.id.diary_register_album);
        diary_register_btn = findViewById(R.id.diary_register_btn);

        // 달력, 이모지, 앨범 및 카메라, 입력 에 대한 클릭 이벤트 선언
        diary_register_date.setOnClickListener(diaryRegisterCilckListener);
        diary_emotion_img.setOnClickListener(diaryRegisterCilckListener);
        diary_register_album.setOnClickListener(diaryRegisterCilckListener);
        diary_register_btn.setOnClickListener(diaryRegisterCilckListener);

        // 내가 선택한 감정 이미지 띄우기
        selectImg();
    }


    View.OnClickListener diaryRegisterCilckListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                // 달력(스피너) -> 선택한 날짜 가져오기
                case R.id.diary_register_date:
                    Toast.makeText(DiaryRegisterActivity.this, "달력을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                    break;
                // 이모지 재선택
                case R.id.diary_emotion_img:
                    Toast.makeText(DiaryRegisterActivity.this, "도담이모지 재변경을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                    break;
                // 앨범 불러오기 및 카메라 불러오기
                case R.id.diary_register_album:
                    Toast.makeText(DiaryRegisterActivity.this, "사진을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                    break;
                // 입력
                case R.id.diary_register_btn:
                    Toast.makeText(DiaryRegisterActivity.this, "입력을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };



    // 내가 선택한 감정 이미지 띄우기
    public void selectImg(){
        switch (diary_emotion_tv.getText().toString()){
            case "졸림":
                diary_emotion_img.setImageResource(R.drawable.emotion_sleep);
                break;
            case "찡얼":
                diary_emotion_img.setImageResource(R.drawable.emotion_frown);
                break;
            case "아픔":
                diary_emotion_img.setImageResource(R.drawable.emotion_pain);
                break;
            case "놀람":
                diary_emotion_img.setImageResource(R.drawable.emotion_surprised);
                break;
            case "화남":
                diary_emotion_img.setImageResource(R.drawable.emotion_angry);
                break;
            case "애교":
                diary_emotion_img.setImageResource(R.drawable.emotion_lovely);
                break;
            case "슬픔":
                diary_emotion_img.setImageResource(R.drawable.emotion_sad);
                break;
            case "부끄":
                diary_emotion_img.setImageResource(R.drawable.emotion_shame);
                break;
            case "기쁨":
                diary_emotion_img.setImageResource(R.drawable.emotion_pleasure);
                break;
            case "그냥":
                diary_emotion_img.setImageResource(R.drawable.emotion_normal);
                break;
            case "따분":
                diary_emotion_img.setImageResource(R.drawable.emotion_bored);
                break;
            case "모름":
                diary_emotion_img.setImageResource(R.drawable.emotion_unknown);
                break;
            default:
                break;
        }
    }


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


    // 키보드 아무곳이나 눌러서 숨기기
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}