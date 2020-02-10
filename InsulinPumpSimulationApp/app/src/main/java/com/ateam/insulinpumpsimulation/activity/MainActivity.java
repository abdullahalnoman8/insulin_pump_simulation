package com.ateam.insulinpumpsimulation.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.ateam.insulinpumpsimulation.R;
import com.ateam.insulinpumpsimulation.fragment.HomeFragment;
import com.ateam.insulinpumpsimulation.fragment.MoreOperationFragment;
import com.ateam.insulinpumpsimulation.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Fade;
import androidx.transition.Slide;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = HomeFragment.newInstance();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = SettingsFragment.newInstance();
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = MoreOperationFragment.newInstance();
                    break;
            }

            // Defines enter transition for all fragment views
            Slide slideTransition = new Slide(Gravity.RIGHT);
            slideTransition.setDuration(500);
            selectedFragment.setEnterTransition(slideTransition);

            Fade slideFadeTransition = new Fade(Gravity.LEFT);
            slideFadeTransition.setDuration(500);
            selectedFragment.setReturnTransition(slideFadeTransition);


            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //removing title and set full screen activity
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ButterKnife.bind(this);

//        Manually displaying the first fragment - one time only
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        fragmentTransaction.commit();
    }
}
