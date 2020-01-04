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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText medtEmail,mEdtName,mEdtPassword;
    private Button mSignUp,mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000CC")));

        medtEmail=findViewById(R.id.edtEmail);
        mEdtName=findViewById(R.id.edtName);
        mEdtPassword=findViewById(R.id.edtPassword);

        mEdtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(mSignUp);
                }
                return false;
            }
        });


        mSignUp=findViewById(R.id.btnSign);
        mLogin=findViewById(R.id.btnLog);


        mSignUp.setOnClickListener(this);
        mLogin.setOnClickListener(this);


        if (ParseUser.getCurrentUser()!=null) {
            // ParseUser.getCurrentUser().logOut();
            transitionToSocialMedia();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSign:
                if (!mEdtName.getText().toString().equals("") &&
                        !mEdtPassword.getText().toString().equals("") &&
                        !medtEmail.getText().toString().equals("")) {
                    final ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(mEdtName.getText().toString());
                    parseUser.setEmail(medtEmail.getText().toString());
                    parseUser.setPassword(mEdtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up :" + parseUser.getUsername());
                    progressDialog.show();

                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this,
                                        parseUser.getUsername() + "signed Up!",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS, true).show();
                                transitionToSocialMedia();
                            } else {
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                else{
                    Toast.makeText(this,"Enter Details!",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnLog:
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
        }
    }

    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e){

        }
    }

    private void transitionToSocialMedia(){
        Intent intent = new Intent(this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
