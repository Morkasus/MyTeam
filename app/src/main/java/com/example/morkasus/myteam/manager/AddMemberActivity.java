package com.example.morkasus.myteam.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.morkasus.myteam.R;
import com.example.morkasus.myteam.commonhelpers.AppConsts;
import com.example.morkasus.myteam.commonhelpers.ParseConstans;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMemberActivity extends Activity {

    @Bind(R.id.mailEditText) EditText mUserNameEditText;
    @Bind(R.id.nameEditText) EditText mNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.addButton)
    public void addMember() {

        String userName = mUserNameEditText.getText().toString();
        String name = mNameEditText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(ParseConstans.KEY_USERNAME,userName);
        intent.putExtra(ParseConstans.NAME,name);
        setResult(AppConsts.RESULT_OK, intent);
        finish();
    }
}
