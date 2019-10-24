package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Button msaveBtn;
    EditText edtName,edtPSpeed,edtPPower,edtKSpeed,edtKPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ParseInstallation.getCurrentInstallation().saveInBackground();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_sign);

        msaveBtn=findViewById(R.id.btnSave);
        edtName=findViewById(R.id.edtName);
        edtPSpeed=findViewById(R.id.edtPunchSpeed);
        edtPPower=findViewById(R.id.edtPunchPower);
        edtKSpeed=findViewById(R.id.edtKickSpeed);
        edtKPower=findViewById(R.id.edtKickPower);

        msaveBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name",edtName.getText().toString());
        kickBoxer.put("punchSpeed",Integer.parseInt(edtPSpeed.getText().toString()));
        kickBoxer.put("punchPower",Integer.parseInt(edtPPower.getText().toString()));
        kickBoxer.put("kickSpeed",Integer.parseInt(edtKSpeed.getText().toString()));
        kickBoxer.put("kickPower",Integer.parseInt(edtKPower.getText().toString()));

        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){
                    FancyToast.makeText(SignUp.this,kickBoxer.get("name")+"data's stored!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                }
                else{
                    FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
            }
        });
    }

//    public void helloWorldTapped(View view){
////        ParseObject boxer = new ParseObject("Boxer");
////        boxer.put("punch_speed",200);
////        boxer.saveInBackground(new SaveCallback() {
////            @Override
////            public void done(ParseException e) {
////                if (e==null){
////                    Toast.makeText(SignUp.this,"boxer object is saved",Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//        ParseObject kickBoxer = new ParseObject("KickBoxer");
//        kickBoxer.put("punchSpeed",200);
//        kickBoxer.put("punchPower",100);
//        kickBoxer.put("kickSpeed",300);
//        kickBoxer.put("kickPower",150);
//
//        kickBoxer.saveEventually(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e==null){
//                    Toast.makeText(SignUp.this,"Data stored!",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
}
