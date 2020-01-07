package com.example.instagramclone;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */

public class ProfileTab extends Fragment implements View.OnClickListener{

    private EditText edtName,edtBio,edtProfession,edtHobbies,edtFavouriteSport;
    private Button btnUpdate;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtName = view.findViewById(R.id.edtProfileName);
        edtBio = view.findViewById(R.id.edtProfileBio);
        edtProfession = view.findViewById(R.id.edtProfileProfession);
        edtHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtFavouriteSport = view.findViewById(R.id.edtProfileSport);
        btnUpdate = view.findViewById(R.id.btnProfileUpdate);

        btnUpdate.setOnClickListener(this);

        edtFavouriteSport.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                        onClick(btnUpdate);
                    }
                return false;
            }
        });

        final ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser!=null){
            try {
                edtName.setText(parseUser.get("ProfileName").toString());
                edtBio.setText(parseUser.get("ProfileBio").toString());
                edtProfession.setText(parseUser.get("ProfileProfession").toString());
                edtHobbies.setText(parseUser.get("ProfileHobbies").toString());
                edtFavouriteSport.setText(parseUser.get("ProfileSport").toString());
            }
            catch (NullPointerException ex){

            }
        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
                    AlertDialog.Builder logoutAlert = new AlertDialog.Builder(getContext());
                    logoutAlert.setCancelable(true);
                    logoutAlert.setTitle("Logout!");
                    logoutAlert.setMessage("Do you really want to log out?");
                    logoutAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ParseUser.logOut();
                            getActivity().finish();
                        }
                    });
                    logoutAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    logoutAlert.show();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {


        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (!edtName.getText().toString().equals("") && !edtBio.getText().toString().equals("") && !edtProfession.getText().toString().equals("") && !edtHobbies.getText().toString().equals("") && !edtFavouriteSport.getText().toString().equals("")){

            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Updating Info...");
            progressDialog.show();


            parseUser.put("ProfileName", edtName.getText().toString());
                parseUser.put("ProfileBio", edtBio.getText().toString());
                parseUser.put("ProfileProfession", edtProfession.getText().toString());
                parseUser.put("ProfileHobbies", edtHobbies.getText().toString());
                parseUser.put("ProfileSport", edtFavouriteSport.getText().toString());
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(getContext(), "Information Updated!",
                                    FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                        } else {
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.LENGTH_SHORT
                                    , FancyToast.ERROR, true).show();
                        }
                        progressDialog.dismiss();
                    }
                });
            }
            else{
                FancyToast.makeText(getContext(),"Enter Details!",FancyToast.LENGTH_SHORT,
                        FancyToast.CONFUSING,true).show();
            }
    }

}
