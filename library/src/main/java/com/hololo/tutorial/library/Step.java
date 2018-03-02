package com.hololo.tutorial.library;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
    private String title;
    private String content;
    private String summary;
    private int drawable;
    private int backgroundColor;

    public Step() {
    }

    protected Step(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.summary = in.readString();
        this.drawable = in.readInt();
        this.backgroundColor = in.readInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.summary);
        dest.writeInt(this.drawable);
        dest.writeInt(this.backgroundColor);
    }

    public static class Builder {

        private Step step;

        public Builder() {
            step = new Step();
        }

        public Step build() {
            return step;
        }

        public Builder setTitle(String title) {
            step.title = title;
            return this;
        }

        public Builder setContent(String content) {
            step.content = content;
            return this;
        }

        public Builder setSummary(String summary) {
            step.summary = summary;
            return this;
        }

        public Builder setDrawable(int drawable) {
            step.drawable = drawable;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            step.backgroundColor = backgroundColor;
            return this;
        }
    }
}
