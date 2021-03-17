package com.android.dodam.SqLite;

import java.sql.Blob;

public class Diary {
    // Field
    private int diaryNumber;
    private String diaryTitle;
    private String diaryContent;
    private Blob diaryImage;
    private String diaryDate;
    private Blob diaryEmotion;

    // Constructor
    public Diary(int diaryNumber, String diaryTitle, String diaryContent, Blob diaryImage, String diaryDate, Blob diaryEmotion) {
        this.diaryNumber = diaryNumber;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryImage = diaryImage;
        this.diaryDate = diaryDate;
        this.diaryEmotion = diaryEmotion;
    }


    // Getter & Setter
    public int getDiaryNumber() {
        return diaryNumber;
    }

    public void setDiaryNumber(int diaryNumber) {
        this.diaryNumber = diaryNumber;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent;
    }

    public Blob getDiaryImage() {
        return diaryImage;
    }

    public void setDiaryImage(Blob diaryImage) {
        this.diaryImage = diaryImage;
    }

    public String getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(String diaryDate) {
        this.diaryDate = diaryDate;
    }

    public Blob getDiaryEmotion() {
        return diaryEmotion;
    }

    public void setDiaryEmotion(Blob diaryEmotion) {
        this.diaryEmotion = diaryEmotion;
    }
}
