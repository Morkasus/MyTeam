package com.example.morkasus.myteam.manager;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.morkasus.myteam.R;
import com.example.morkasus.myteam.commonhelpers.AppConsts;
import com.example.morkasus.myteam.commonhelpers.Listener;
import com.example.morkasus.myteam.commonhelpers.ParseConstans;
import com.example.morkasus.myteam.controllers.MembersController;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Collections;
import java.util.List;


public class MembersFragment extends ListFragment implements Listener{


    private static final String TAG = MembersFragment.class.getSimpleName();
    private MembersController mMembersController;
    private String password = "123456";
    private List<ParseUser> mFriends;
    private ParseRelation<ParseUser> mFriendsRelation;
    private ParseUser mCurrentUser;
    private boolean checkWorkerAdded = false;


    // Store instance variables
    public String title;
    public int page;

    private MemberAdapter mAdapter;

    private int memberSendTaskPosition;

    public static MembersFragment newInstance(int page, String title) {
        MembersFragment fragmentFirst = new MembersFragment();
        Bundle args = new Bundle();
        args.putInt(AppConsts.BUNDLE_PAGE, page);
        args.putString(AppConsts.BUNDLE_TITLE, title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(AppConsts.BUNDLE_PAGE, 0);
        title = getArguments().getString(AppConsts.BUNDLE_TITLE);
        mMembersController = new MembersController(getActivity());
        mMembersController.setListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new MemberAdapter(getActivity(), getAllWorkers(), this);
        setListAdapter(mAdapter);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_members_fragment, container, false);

        ImageView addWorker = (ImageView) view.findViewById(R.id.addMember);
        addWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMemberActivity.class);
                startActivityForResult(intent, AppConsts.RESULT_OK);
            }
        });

        return view;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConsts.RESULT_OK) {
            String username = data.getStringExtra(ParseConstans.KEY_USERNAME);
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{username});
            i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
            i.putExtra(Intent.EXTRA_TEXT , "body of email");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
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

    @Override
    public void updateAdapterAboutChange(ParseUser worker) {
        mAdapter.add(worker);
    }

}
