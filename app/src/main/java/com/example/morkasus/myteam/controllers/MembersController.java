package com.example.morkasus.myteam.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.morkasus.myteam.commonhelpers.Listener;
import com.example.morkasus.myteam.commonhelpers.ParseConstans;
import com.example.morkasus.myteam.manager.MemberAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.Collections;
import java.util.List;

/**
 * Created by morkasus on 19/12/2015.
 */
public class MembersController {


    private static final String TAG = MemberAdapter.class.getSimpleName();
    private Context mContext;
    private Listener mListener;
    private List<ParseUser> mFriends;
    private ParseRelation<ParseUser> mFriendsRelation;
    private ParseUser mCurrentUser;
    private ParseUser newWorker;


    public MembersController(Context context) {
        mContext = context;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public List<ParseUser> getAllWorkers() {

        mCurrentUser = ParseUser.getCurrentUser();
        mFriendsRelation = mCurrentUser.getRelation(ParseConstans.KEY_FRRIEND_RELATION);

        ParseQuery<ParseUser> query = mFriendsRelation.getQuery();
        query.addAscendingOrder(ParseConstans.KEY_USERNAME);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> friends, ParseException e) {

                if (e == null) {
                    mFriends = friends;
                }
            }
        });

        if (mFriends != null) {
            return mFriends;
        } else {
            return Collections.emptyList();
        }
    }

    public void removeWorker(ParseUser worker) {
        //remove from parse
    }

}
