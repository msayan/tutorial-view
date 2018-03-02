package com.hololo.tutorial.library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StepFragment extends StepView {

    private TextView title;
    private TextView content;
    private TextView summary;
    private ImageView imageView;
    private LinearLayout layout;


    static StepFragment createFragment(Step step) {
        StepFragment fragment = new StepFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("step", step);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        step = getArguments().getParcelable("step");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        initViews(view);
        initData();

        return view;
    }

    private void initData() {
        title.setText(step.getTitle());
        content.setText(step.getContent());
        summary.setText(step.getSummary());
        imageView.setImageResource(step.getDrawable());
        layout.setBackgroundColor(step.getBackgroundColor());
    }

    private void initViews(View view) {
        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);
        summary = (TextView) view.findViewById(R.id.summary);
        imageView = (ImageView) view.findViewById(R.id.image);
        layout = (LinearLayout) view.findViewById(R.id.container);
    }

}
