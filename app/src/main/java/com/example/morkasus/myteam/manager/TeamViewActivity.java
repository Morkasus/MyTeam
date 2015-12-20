package com.example.morkasus.myteam.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.morkasus.myteam.R;
import com.example.morkasus.myteam.commonhelpers.MyPagerAdapter;
import com.example.morkasus.myteam.commonhelpers.SmartFragmentStatePagerAdapter;
import com.example.morkasus.myteam.common.LoginActivity;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeamViewActivity extends AppCompatActivity {

    public static final int POSITION_MAP = 0;
    public static final int POSITION_ADDRESS = 1;
    public static final int POSITION_CONTACT = 2;

    private static final int RESULT_OK = 1;
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String NAME = "NAME";

    public static final String TAG = TeamViewActivity.class.getSimpleName();
    @Bind(R.id.tool_bar) Toolbar mToolbar;
    SmartFragmentStatePagerAdapter adapterViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_view);

        ButterKnife.bind(this);
        mToolbar.setTitle("Team View");
        setSupportActionBar(mToolbar);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(TeamViewActivity.this,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
//        adapterViewPager.getRegisteredFragment(0);
//        adapterViewPager.getRegisteredFragment(vpPager.getCurrentItem());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {

            case R.id.action_logout :
                ParseUser.logOut();
                moveToLoginScreen();
                break;

            case R.id.action_about :
                break;

            case R.id.action_setting :
                break;

            case R.id.action_MyTeam :
                break;

            case R.id.action_send_task :
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void moveToLoginScreen() {
        Intent intent = new Intent(TeamViewActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
