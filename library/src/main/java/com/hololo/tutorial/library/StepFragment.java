package com.hololo.tutorial.library;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StepFragment extends Fragment {

    private TextView title;
    private TextView content;
    private TextView summary;
    private TutorialImageView imageView;
    private LinearLayout layout;

    private Step step;

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
        imageView = (TutorialImageView) view.findViewById(R.id.image);
        layout = (LinearLayout) view.findViewById(R.id.container);
    }

}
