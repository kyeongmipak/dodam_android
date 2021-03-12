package com.android.dodam.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.dodam.Adapter.ViewPageAdapter;
import com.android.dodam.R;
import com.google.android.material.tabs.TabLayout;

// 21.03.12 지은 생성 및 추가 ***************************
public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    Context mContext;
    // -------------------------------------------------------

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Dodam");



        tabLayout = findViewById(R.id.tabLayout_id);
        viewPager = findViewById(R.id.pageViewId);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        //     Add Fragment
        viewPageAdapter.AddFrmt(new Frmt_Home(),"");
        viewPageAdapter.AddFrmt(new Frmt_Category(),"");
        viewPageAdapter.AddFrmt(new Frmt_Fav(),"");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        mContext = this;

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_calendar).setText("달력");
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline).setText("모아보기");
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_chart).setText("통계");

        // 21.03.12 지은 추가 선택되는 탭 상단 색 바꾸기 --- > 교체 예정
        tabLayout.setSelectedTabIndicatorColor(Color.YELLOW);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        //****************************************



    }


    // 옵션 메뉴 선언
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    // 옵션 메뉴
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    // 뒤로가기 두번 클릭시 어플 종료
    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }
    //**********************************************



}
