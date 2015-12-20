package com.example.morkasus.myteam.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.morkasus.myteam.R;
import com.example.morkasus.myteam.common.LoginActivity;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManagerMainActivity extends AppCompatActivity {

    private static final String TAG = ManagerMainActivity.class.getSimpleName();
    private static Boolean isTeamCreated = false;
    @Bind(R.id.tool_bar) Toolbar mToolbar;
    @Bind(R.id.TeamViewButton) Button mTeamViewButton;
    @Bind(R.id.manageTeamButton) Button mManageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);
        ButterKnife.bind(this);
        mToolbar.setTitle("My Zone");
        setSupportActionBar(mToolbar);

        if (!isTeamCreated) {
            mTeamViewButton.setEnabled(true);
            mManageButton.setEnabled(true);
        } else {
            mTeamViewButton.setEnabled(false);
            mManageButton.setEnabled(false);
        }
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
        Intent intent = new Intent(ManagerMainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    @OnClick(R.id.createTeamButton)
    public void createTeam() {

    }

    @OnClick(R.id.TeamViewButton)
    public void tasksView(){
        Intent intent = new Intent(ManagerMainActivity.this, TeamViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.profileButton)
    public void toProfile() {

    }

    @OnClick(R.id.manageTeamButton)
    public void manageTeam() {

    }
}
