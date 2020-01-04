package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button mbtnLogin,mbtnSignUp;
    EditText medtEmail,mEdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF3399")));

        mbtnLogin=findViewById(R.id.btnLogIn);
        mbtnSignUp=findViewById(R.id.btnSignUp);
        medtEmail=findViewById(R.id.edtEmail);
        mEdtPassword=findViewById(R.id.edtPassword);

        mEdtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode==KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                        onClick(mbtnLogin);
                    }
                return false;
            }
        });


        if (ParseUser.getCurrentUser()!=null) {
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

        mbtnLogin.setOnClickListener(this);
        mbtnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogIn:
                if(!medtEmail.getText().toString().equals("") &&
                        !mEdtPassword.getText().toString().equals("")){

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Logging in: "+medtEmail.getText().toString());
                    progressDialog.show();

                    ParseUser.logInInBackground(medtEmail.getText().toString(),
                            mEdtPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(getApplicationContext(),
                                        user.getUsername()+ " logged in!",
                                        FancyToast.LENGTH_SHORT,
                                        FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();
                            }
                            else {
                                FancyToast.makeText(getApplicationContext(), e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.CONFUSING, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                else{
                    FancyToast.makeText(getApplicationContext(), "Please enter Details!", FancyToast.LENGTH_LONG, FancyToast.CONFUSING, true).show();
                }
                break;
            case R.id.btnSignUp:
                finish();
                break;
        }
    }

    public void root_Layout_Tapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e){

        }
    }
    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(this,SocialMediaActivity.class);
        startActivity(intent);
    }

}
