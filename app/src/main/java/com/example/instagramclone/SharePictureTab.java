package com.example.instagramclone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharePictureTab extends Fragment implements View.OnClickListener{

    private ImageView mImageView;
    private EditText mEditText;
    private Button mButton;

    public SharePictureTab() {
        // Required empty publ/ic constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_picture_tab, container, false);
        mImageView = view.findViewById(R.id.ivImgShare);
        mEditText = view.findViewById(R.id.edtDescription);
        mButton = view.findViewById(R.id.btnShareImage);

        mImageView.setOnClickListener(SharePictureTab.this);
        mButton.setOnClickListener(SharePictureTab.this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ivImgShare:

                break;
            case R.id.btnShareImage:
                break;
        }
    }
}
