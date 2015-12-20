package com.example.morkasus.myteam.manager;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morkasus.myteam.R;
import com.example.morkasus.myteam.commonhelpers.ParseConstans;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by morkasus on 19/12/2015.
 */
public class MemberAdapter extends BaseAdapter {

    private Context mContext;
    private List<ParseUser> mWorkers;
    private MembersFragment mMembersFragment;
    private static LayoutInflater inflater=null;


    public MemberAdapter(Context context, List<ParseUser> workers, MembersFragment fragment) {
        mContext = context;
        mWorkers = workers;
        mMembersFragment = fragment;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mWorkers.size();
    }

    @Override
    public Object getItem(int position) {
        return mWorkers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        View view;
        view = inflater.inflate(R.layout.member_item, null);
        holder.workerNameTextView = (TextView) view.findViewById(R.id.memberNameLabel);
        holder.workerImageView = (ImageView) view.findViewById(R.id.memberIconImageView);
        holder.removeWorkerButton = (Button) view.findViewById(R.id.removeMemberButton);

        holder.workerNameTextView.setText(mWorkers.get(position).get(ParseConstans.KEY_USERNAME).toString());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "You Clicked " + mWorkers.get(position), Toast.LENGTH_LONG).show();
            }
        });

        holder.removeWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }


    public void add(ParseUser worker) {
        mWorkers.add(worker);
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        mWorkers.remove(position);
        notifyDataSetChanged();
    }

    public static class ViewHolder{

        public TextView workerNameTextView;
        public Button removeWorkerButton;
        public ImageView workerImageView;



    }
}
