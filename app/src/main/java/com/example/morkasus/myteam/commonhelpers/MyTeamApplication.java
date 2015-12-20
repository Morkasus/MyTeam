package com.example.morkasus.myteam.commonhelpers;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by morkasus on 16/12/2015.
 */
public class MyTeamApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
    }
}
