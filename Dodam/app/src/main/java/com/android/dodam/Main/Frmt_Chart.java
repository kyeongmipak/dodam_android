package com.android.dodam.Main;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.dodam.R;
import com.android.dodam.SQLite.DodamDiary;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Frmt_Chart extends Fragment {

    final static String TAG = "Frmt_Chart";
    View v;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM");

    // diary sqlite 선언
    DodamDiary dodamDiary;

    // db형식으로 넣기 위해 선언
    SQLiteDatabase DB;

    HorizontalBarChart horizontalBarChart;
    TextView selectMonth;
    Button selectMonthBtn;

    public Frmt_Chart() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frmt_chart,container,false);
        horizontalBarChart = v.findViewById(R.id.emotionMonthly_chart);
        selectMonth = v.findViewById(R.id.selectMonth_chart);
        selectMonthBtn = v.findViewById(R.id.selectMonthBtn_chart);

        selectMonth.setText(getTime());

        dodamDiary = new DodamDiary(getActivity());

        selectMonth.setOnClickListener(mClickListener);
        selectMonthBtn.setOnClickListener(mClickListener);
//        BarDataSet barDataSet = new BarDataSet(data(), "dataset");
//
//        barDataSet.setColor(Color.BLUE);
//        BarData barData = new BarData();
//        barData.addDataSet(barDataSet);
//
//        barChart.setData(barData);
//        barChart.setFitBars(false);
//        barChart.animateXY(1000,1000);//        // data 갱신
//        barChart.invalidate();


        return v;
    }
    public class CategoryBarChartXaxisFormatter extends ValueFormatter implements IAxisValueFormatter {

        ArrayList<String> mValues;

        public CategoryBarChartXaxisFormatter(ArrayList<String> values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value) {
            int val = (int) value;
            String label = "";
            if (val >= 0 && val < mValues.size()) {
                label = mValues.get(val);
            } else {
                label = "";
            }
            return label;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.selectMonth_chart:

                    break;
                case R.id.selectMonthBtn_chart:
                    chartSetting();
                    break;
            }
        }
    };

    // 현재 날짜
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }


    private void chartSetting(){
        ArrayList<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        horizontalBarChart.setDrawBarShadow(false);
        horizontalBarChart.setDrawValueAboveBar(true);
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setDrawGridBackground(false);


        XAxis xl = horizontalBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        CategoryBarChartXaxisFormatter xaxisFormatter = new CategoryBarChartXaxisFormatter(labels);
        xl.setValueFormatter(xaxisFormatter);
        xl.setGranularity(1);

        YAxis yl = horizontalBarChart.getAxisLeft();
        yl.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yl.setDrawGridLines(false);
        yl.setEnabled(false);
        yl.setAxisMinimum(0f);

        YAxis yr = horizontalBarChart.getAxisRight();
        yr.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < 6; i++) {
            yVals1.add(new BarEntry(i, (i+1)*10));
        }

        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "DataSet 1");
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(.9f);
        horizontalBarChart.setData(data);
        horizontalBarChart.getLegend().setEnabled(false);

    }

    private void selectChart(){
        try {
            DB = dodamDiary.getReadableDatabase();
            String query = "SELECT count(diaryEmotion) FROM dodamDiary WHERE diaryDate LIKE '" + selectMonth.getText() + "%' GROUP BY diaryEmotion ORDER BY diaryEmotion;";
            // 한 줄 선택되어 있는 data 가져오기
            Cursor cursor = DB.rawQuery(query, null);
            StringBuffer stringBuffer = new StringBuffer();

            while(cursor.moveToNext()){
                // SELECT 절 처음 순서부터 0,1,2, ...
                String diaryEmotion = cursor.getString(0);

                stringBuffer.append("diaryEmotion : " + diaryEmotion + "\n");
            }

            Log.v(TAG, stringBuffer.toString());
            cursor.close();
            dodamDiary.close();
            Toast.makeText(getActivity(), "Select OK!", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "Select Error", Toast.LENGTH_SHORT).show();
        }

        return;

    }

//    private ArrayList<BarEntry> data() {
//        ArrayList<BarEntry> data_val = new ArrayList<>();
//        data_val.add((new BarEntry(0, 20)));
//        data_val.add((new BarEntry(1, 30)));
//        data_val.add((new BarEntry(2, 10)));
//
//        return  data_val;
//    }
//
//    private ArrayList<String> getXAxisValues() {
//        ArrayList<String> xAxis = new ArrayList<>();
//        xAxis.add("JAN");
//        xAxis.add("FEB");
//        xAxis.add("MAR");
//        xAxis.add("APR");
//        xAxis.add("MAY");
//        xAxis.add("JUN");
//        return xAxis;
//    }

}//----------
