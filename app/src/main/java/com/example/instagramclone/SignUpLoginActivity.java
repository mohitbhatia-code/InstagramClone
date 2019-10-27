package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    EditText edtNameSignUp,edtPassSignUp,edtNameLogin,edtPassLogin;
    Button btnSignUp,btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtNameSignUp=findViewById(R.id.nameSignup);
        edtPassSignUp=findViewById(R.id.passSignup);
        edtNameLogin=findViewById(R.id.nameLogin);
        edtPassLogin=findViewById(R.id.passLogin);

        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogin=findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtNameSignUp.getText().toString());
                appUser.setPassword(edtPassSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,edtNameSignUp.getText().toString()+"is Signed up!",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                        }
                        else{
                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtNameLogin.getText().toString()
                        , edtPassLogin.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e==null){
                                    FancyToast.makeText(SignUpLoginActivity.this,user.getUsername()+"is Logged in!",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                                }
                                else{
                                    FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                                }
                            }
                        });
            }
        });
    }
}
