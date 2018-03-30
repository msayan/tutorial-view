package com.hololo.tutorial.library;

import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class PermissionStep extends Step implements Parcelable {

    private String[] permissions;

    public String[] getPermissions() {
        return permissions;
    }

    public static class Builder {

        private PermissionStep step;

        public Builder() {
            step = new PermissionStep();
        }

        public PermissionStep build() {
            return step;
        }

        public Builder setTitle(String title) {
            step.setTitle(title);
            return this;
        }

        public Builder setContent(String content) {
            step.setContent(content);
            return this;
        }

        public Builder setSummary(String summary) {
            step.setSummary(summary);
            return this;
        }

        public Builder setDrawable(int drawable) {
            step.setDrawable(drawable);
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            step.setBackgroundColor(backgroundColor);
            return this;
        }

        public Builder setPermissions(String[] permissions) {
            step.permissions = permissions;
            return this;
        }

        public Builder setView(int view) {
            step.setViewType(view);
            return this;
        }
    }

}
