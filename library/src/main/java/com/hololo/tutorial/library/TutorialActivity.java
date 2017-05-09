package com.hololo.tutorial.library;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Step> steps;
    private StepPagerAdapter adapter;

    private ViewPager pager;
    private Button next, prev;
    private LinearLayout indicatorLayout;
    private FrameLayout containerLayout;
    private RelativeLayout buttonContainer;

    private int currentItem;

    private String backText, nextText, finishText, cancelText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.TutorialStyle);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial);
        steps = new ArrayList<>();
        initTexts();
        initViews();
        initAdapter();
    }

    private void initTexts() {
        backText = "Back";
        cancelText = "Cancel";
        finishText = "Finish";
        nextText = "Next";
    }

    private void initAdapter() {
        adapter = new StepPagerAdapter(getSupportFragmentManager(), steps);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                controlPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeStatusBarColor(int backgroundColor) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(backgroundColor);
    }

    private void controlPosition(int position) {
        if (position == steps.size() - 1) {
            next.setText(finishText);
            prev.setText(backText);
        } else if (position == 0) {
            prev.setText(cancelText);
            next.setText(nextText);
        } else {
            prev.setText(backText);
            next.setText(nextText);
        }

        containerLayout.setBackgroundColor(steps.get(position).getBackgroundColor());
        buttonContainer.setBackgroundColor(steps.get(position).getBackgroundColor());
    }

    private void initViews() {
        currentItem = 0;

        pager = (ViewPager) findViewById(R.id.viewPager);
        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);
        indicatorLayout = (LinearLayout) findViewById(R.id.indicatorLayout);
        containerLayout = (FrameLayout) findViewById(R.id.containerLayout);
        buttonContainer = (RelativeLayout) findViewById(R.id.buttonContainer);

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
    }

    public void addFragment(Step step) {
        steps.add(step);
        adapter.notifyDataSetChanged();
        notifyIndicator();
        controlPosition(currentItem);
    }

    public void addFragment(Step step, int position) {
        steps.add(position, step);
        adapter.notifyDataSetChanged();
        notifyIndicator();
    }

    public void notifyIndicator() {
        if (indicatorLayout.getChildCount() > 0)
            indicatorLayout.removeAllViews();

        for (int i = 0; i < steps.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_brightness_1_black_24dp);

            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFragment(finalI);
                }
            });

            indicatorLayout.addView(imageView);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.next) {
            changeFragment(true);
        } else if (v.getId() == R.id.prev) {
            changeFragment(false);
        }
    }

    private void changeFragment(int position) {
        pager.setCurrentItem(position, true);
    }

    private void changeFragment(boolean isNext) {
        int item = currentItem;
        if (isNext) {
            item++;
        } else {
            item--;
        }

        if (item < 0 || item == steps.size()) {
            finish();
        } else
            pager.setCurrentItem(item, true);
    }

    public void setBackText(String text) {
        backText = text;
    }

    public void setNextText(String text) {
        nextText = text;
    }

    public void setFinishText(String text) {
        finishText = text;
    }

    public void setCancelText(String text) {
        cancelText = text;
    }

}