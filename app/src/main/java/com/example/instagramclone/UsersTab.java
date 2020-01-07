package com.example.instagramclone;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment {

    private ListView mListView;
    private ArrayList mArrayList;
    private ArrayAdapter mArrayAdapter;
    private TextView mTextView;

    public UsersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_users_tab, container, false);

        mArrayList = new ArrayList();
        mListView = view.findViewById(R.id.listView);
        mArrayAdapter = new ArrayAdapter(getContext(),R.layout.list_textview,R.id.list_content,mArrayList);
        mTextView = view.findViewById(R.id.tvLoadingUser);

        final ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());


        mTextView.setTextColor(Color.WHITE);

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e==null){
                    for(ParseUser parseUser:users){
                        mArrayList.add(parseUser.getUsername());
                    }
                    mListView.setAdapter(mArrayAdapter);
                    mTextView.animate().alpha(0).setDuration(2000);
                    mListView.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;

    }

}
