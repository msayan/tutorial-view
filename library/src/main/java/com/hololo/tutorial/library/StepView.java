package com.hololo.tutorial.library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class StepView extends Fragment {

    Step step;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        step = getArguments().getParcelable("step");
    }
}
