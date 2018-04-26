package com.uber.rib.data;



import io.reactivex.annotations.Nullable;

/**
 * Created by cpu10639 on 11/04/2018.
 */

public class Task {
    String mTitle = "";

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    String mDescription = "";


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    long mId = -1;


    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }


    boolean mCompleted = false;


    public boolean getCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean mCompleted) {
        this.mCompleted = mCompleted;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {
        return !mCompleted;
    }

    public boolean isEmpty() {
        return mTitle.isEmpty() &&
                mDescription.isEmpty();
    }


    public Task(Long id, String title, String description, boolean isCompleted) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = isCompleted;

    }

}
