package com.android.dodam.Main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.dodam.Diary.DiaryRegisterActivity;
import com.android.dodam.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Frmt_Home extends Fragment {

    final static String TAG = "Frmt_Home";
    View v;

    FloatingActionButton fabMain;   // 메안 플로팅 버튼

    public Frmt_Home() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frmt_home,container,false);

        fabMain = v.findViewById(R.id.fabMain);
        fabMain.setOnClickListener(homeBtnClickListener);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // 지은 추가 21.01.09-------------------------------
    // 지은 home 쪽에서 쓰이는 버튼 기능 수정
    View.OnClickListener homeBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                // 플로팅 버튼 관련 -------------------------
                case R.id.fabMain:
                    Intent intentRegister = new Intent(getActivity(), DiaryRegisterActivity.class);
                    startActivity(intentRegister);
                    break;

            }
        }
    };//------------------------------------------------

}//— 끝 ————