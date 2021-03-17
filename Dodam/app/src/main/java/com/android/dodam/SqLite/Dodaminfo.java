package com.android.dodam.SqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Dodaminfo extends SQLiteOpenHelper {

    final static String TAG = "Dodaminfo";

    public Dodaminfo(@Nullable Context context) {
        super(context, "Dodam.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "onCreate()");

        // 다이어리에 관한 디비 생성
        String query_diary = "CREATE TABLE dodamDiary(diaryNumber INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "diaryTitle TEXT, diaryContent TEXT, diaryImage BLOB, diaryDate TEXT, diaryEmotion BLOB);";


        // 유저에 관한 디비 생성
        String query_user = "CREATE TABLE dodamUser(userNo INTEGER PRIMARY KEY AUTOINCREMENT," +
                " userName TEXT, userBirth TEXT, userImage BLOB);";
        db.execSQL(query_diary + query_user);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "upgrade()");
        String query_diary = "DROP TABLE IF EXISTS dodamDiary;";

        String query_user = "DROP TABLE IF EXISTS dodamUser;";

        db.execSQL(query_diary + query_user);
        onCreate(db);
    }
}
