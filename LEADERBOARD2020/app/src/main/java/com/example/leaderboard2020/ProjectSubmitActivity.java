package com.example.leaderboard2020;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.leaderboard2020.interfaces.SendPostInterface;
import com.example.leaderboard2020.retrofit_api.RetrofitPost;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.PorterDuff.Mode;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmitActivity extends AppCompatActivity {
    EditText firstname;
    EditText lastname;
    EditText email;
    EditText githublink;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar

        ImageView img = findViewById(R.id.logoXmarks);
        Resources res =getResources();

        final int newColor = res.getColor(R.color.white);
        img.setColorFilter(newColor, Mode.SRC_ATOP);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(x);
            }
        });


        firstname=findViewById(R.id.submit_firstname);
        lastname=findViewById(R.id.submit_lastname);
        email=findViewById(R.id.submit_email);
        githublink=findViewById(R.id.submit_githublink);
        submitBtn=findViewById(R.id.submit);



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isEmpty(firstname.getText().toString())&&!isEmpty(lastname.getText().toString())&&!isEmpty(email.getText().toString())
                        &&!isEmpty(githublink.getText().toString())){
                    confirmSubmit(firstname.getText().toString(),lastname.getText().toString(),email.getText().toString(),githublink.getText().toString());
                }
                else{
                    askToEnterValues();
                }
            }
        });
    }//end





    private boolean isEmpty(String xyz){
        return xyz==null||xyz.equals("");
    }//end


    private void confirmSubmit(final String fname, final String lname, final String email, final String glink){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this,R.style.AlertDialog);
        View view=getLayoutInflater().inflate(R.layout.submission_confirm_submit,null);
        alertDialog.setView(view);
        final AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        TextView submitBt=view.getRootView().findViewById(R.id.submit_yes);
        ImageView img_cancel=view.getRootView().findViewById(R.id.submit_cancel);



        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });


        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
                new RetrofitPostRequest(fname,lname,email,glink);
            }
        });

        alert.show();
    }//end


    private void askToEnterValues() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this,R.style.AlertDialog);
        alertDialog.setTitle("Empty Fields!");
        alertDialog.setMessage("Please fill out all input fields to continue");


        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        alert.show();
    }//end

    private void alertSendFailed(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this,R.style.AlertDialog);
        View view=getLayoutInflater().inflate(R.layout.submission_not_successful,null);
        alertDialog.setView(view);
        AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();
    }//end


    private void alertSendSuccessful(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this,R.style.AlertDialog);
        View view=getLayoutInflater().inflate(R.layout.submission_successful,null);
        alertDialog.setView(view);
        AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();
    }//end


    public class RetrofitPostRequest {
        public RetrofitPostRequest(String firstname, String lastname, String email, String githublink) {
            SendPostInterface poster = RetrofitPost.getRetrofit().create(SendPostInterface.class);
            Call<Void> call = poster.sendPost(firstname, lastname, email, githublink);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        //Works when successful
                        alertSendSuccessful();

                    } else {
                        alertSendFailed();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    alertSendFailed();
                    //If not successfully submitted, it has failed
                }
            });
        }
    }

}
