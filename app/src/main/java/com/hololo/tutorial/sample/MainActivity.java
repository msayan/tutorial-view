package com.hololo.tutorial.sample;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class MainActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(
                new PermissionStep
                        .Builder()
                        .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                        .setTitle(getString(R.string.permission_title)).setContent(getString(R.string.permission_detail))
                        .setBackgroundColor(Color.parseColor("#FF0957"))
                        .setDrawable(R.drawable.ss_1)
                        .setSummary(getString(R.string.continue_and_learn))
                        .build());
        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.automatic_data))
                        .setContent(getString(R.string.gm_finds_photos))
                        .setBackgroundColor(Color.parseColor("#FF0957"))
                        .setDrawable(R.drawable.ss_1)
                        .setSummary(getString(R.string.continue_and_learn))
                        .build());
        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.choose_the_song))
                        .setContent(getString(R.string.swap_to_the_tab))
                        .setBackgroundColor(Color.parseColor("#00D4BA"))
                        .setDrawable(R.drawable.ss_2)
                        .setSummary(getString(R.string.continue_and_update))
                        .build());
        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.edit_data))
                        .setContent(getString(R.string.update_easily))
                        .setBackgroundColor(Color.parseColor("#1098FE"))
                        .setDrawable(R.drawable.ss_3)
                        .setSummary(getString(R.string.continue_and_result))
                        .build());
        addFragment(
                new Step.Builder()
                        .setTitle(getString(R.string.result_awesome))
                        .setContent(getString(R.string.after_updating))
                        .setBackgroundColor(Color.parseColor("#CA70F3"))
                        .setDrawable(R.drawable.ss_4)
                        .setSummary(getString(R.string.thank_you))
                        .build());
    }

    @Override
    public void finishTutorial() {
        Toast.makeText(this, "Tutorial finished", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void currentFragmentPosition(int position) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_SHORT).show();
    }
}
