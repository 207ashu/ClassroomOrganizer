package com.neurobreach.classroomorganizer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    Button signUp;
    static EditText name,email,pwd,branch,enrollmentNo;
    String strCategory,strGender;
    RadioGroup gender,category;

    TextView enroll;
    SQLiteDatabase sd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp=findViewById(R.id.signup);
        email=findViewById(R.id.email_signup);
        name=findViewById(R.id.name);
        pwd=findViewById(R.id.pwd_signup);
        branch=findViewById(R.id.branch_or_dept);
        enrollmentNo=findViewById(R.id.enrollment_no);
        gender=findViewById(R.id.gender_group);
        category=findViewById(R.id.category_group);
        enroll=findViewById(R.id.enrollment_no_tv);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.gender_male)
                    strGender="Male";
                if(i==R.id.gender_female)
                    strGender="Female";
            }
        });

        category.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.teacher) {
                    strCategory = "Teacher";
                    enroll.setVisibility(View.GONE);
                    enrollmentNo.setVisibility(View.GONE);
                }
                if(i==R.id.student){
                    strCategory="Student";
                    enroll.setVisibility(View.VISIBLE);
                    enrollmentNo.setVisibility(View.VISIBLE);


                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regUser();
            }
        });


        CreateDatabase();

    }

    public void regUser(){
        String strEmail=email.getText().toString().trim();
        String strPwd=pwd.getText().toString().trim();
        String strEnroll;
        String strBranch=branch.getText().toString();
        String strName=name.getText().toString();

        if(strCategory.equalsIgnoreCase("student")){
            strEnroll=enrollmentNo.getText().toString();

        }else{
             strEnroll="0";

        }

        if(strEmail.equals("")||strPwd.equals("")||
                strBranch.equals("")||strEnroll.equals("") ||
                strGender.equals("")||strCategory.equals("")||name.equals("")){
            Toast.makeText(this,"Please Enter all Details",Toast.LENGTH_SHORT).show();
        }else {
            sd.execSQL("insert into tname values('" +strName+"', '"+strGender+"', '"+strEnroll+"','" +strEmail+"','"+strPwd+"','"+strBranch+"','"+strCategory+"')");
            Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(i);
            finish();


        }

    }

    public void CreateDatabase(){
        sd= openOrCreateDatabase("dbname",0,null);

        sd.execSQL("Create table if not exists tname(name varchar(250), gender varchar(250),roll integer(250)," +
                "email varchar(250),password varchar(250),branch varchar(250),category vrchar(25))");

    }


}
