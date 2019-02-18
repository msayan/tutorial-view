package com.hololo.tutorial.library;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
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

public abstract class TutorialActivity extends AppCompatActivity implements View.OnClickListener, CurrentFragmentListener {

    private List<Step> steps;
    private StepPagerAdapter adapter;

    private ViewPager pager;
    private Button next, prev;
    private LinearLayout indicatorLayout;
    private FrameLayout containerLayout;
    private RelativeLayout buttonContainer;
    private CurrentFragmentListener currentFragmentListener;

    private int currentItem;

    private String prevText, nextText, finishText, cancelText, givePermissionText;
    private int selectedIndicator = R.drawable.circle_black, indicator = R.drawable.circle_white;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.TutorialStyle);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        currentFragmentListener = this;
        init();
    }

    private void init() {
        steps = new ArrayList<>();
        initTexts();
        initViews();
        initAdapter();
    }

    private void initTexts() {
        prevText = "Back";
        cancelText = "Cancel";
        finishText = "Finish";
        nextText = "Next";
        givePermissionText = "Give";
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
                currentFragmentListener.currentFragmentPosition(position);
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
        notifyIndicator();

        if (position == steps.size() - 1) {
            next.setText(finishText);
            prev.setText(prevText);
        } else if (position == 0) {
            prev.setText(cancelText);
            next.setText(nextText);
        } else {
            prev.setText(prevText);
            next.setText(nextText);
        }

        if (controlPermission()) {
            prepareNormalView();
        } else {
            preparePermissionView();
        }
        if (!steps.isEmpty()) {
            containerLayout.setBackgroundColor(steps.get(position).getBackgroundColor());
            buttonContainer.setBackgroundColor(steps.get(position).getBackgroundColor());
        }
    }

    private void prepareNormalView() {
        pager.setOnTouchListener(null);
    }

    private void preparePermissionView() {
        next.setText(givePermissionText);

        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
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
            imageView.setPadding(8, 8, 8, 8);
            int drawable = indicator;
            if (i == currentItem)
                drawable = selectedIndicator;

            imageView.setImageResource(drawable);

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
    public void onBackPressed() {
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            changeFragment(false);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.next) {
            if (controlPermission())
                changeFragment(true);
            else
                requestPermissions(((PermissionStep) steps.get(pager.getCurrentItem())).getPermissions(), 1903);
        } else if (v.getId() == R.id.prev) {
            changeFragment(false);
        }
    }

    private void changeFragment(int position) {
        if (controlPermission())
            pager.setCurrentItem(position, true);
    }

    private boolean controlPermission() {
        if (!steps.isEmpty() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && steps.get(pager.getCurrentItem()) instanceof PermissionStep) {

            for (String permission : ((PermissionStep) steps.get(pager.getCurrentItem())).getPermissions()) {
                int permissionResult = checkSelfPermission(permission);

                if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void changeFragment(boolean isNext) {
        int item = currentItem;
        if (isNext) {
            item++;
        } else {
            item--;
        }

        if (item < 0 || item == steps.size()) {
            finishTutorial();
        } else
            pager.setCurrentItem(item, true);
    }

    public void finishTutorial() {
        finish();
    }

    public void setPrevText(String text) {
        prevText = text;
        controlPosition(0);
    }

    public void setNextText(String text) {
        nextText = text;
        controlPosition(0);
    }

    public void setFinishText(String text) {
        finishText = text;
        controlPosition(0);
    }

    public void setCancelText(String text) {
        cancelText = text;
        controlPosition(0);
    }

    public void setGivePermissionText(String text) {
        givePermissionText = text;
        controlPosition(0);
    }

    public void setIndicatorSelected(int drawable) {
        selectedIndicator = drawable;
    }

    public void setIndicator(int drawable) {
        indicator = drawable;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            changeFragment(true);
        }
    }


}