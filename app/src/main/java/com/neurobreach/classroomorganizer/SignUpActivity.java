package com.neurobreach.classroomorganizer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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
    static String strCategory,strGender;
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
                else if(i==R.id.student){
                    strCategory="Student";
                    enroll.setVisibility(View.VISIBLE);
                    enrollmentNo.setVisibility(View.VISIBLE);


                }
                else
                    strCategory="none";
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

int prob=0;

            if(category.getCheckedRadioButtonId()==R.id.student){
                strEnroll=enrollmentNo.getText().toString();
            }
            else if(category.getCheckedRadioButtonId()==R.id.teacher){
                strEnroll="0";
            }
            else{
                Toast.makeText(SignUpActivity.this,"Please Enter all Details",Toast.LENGTH_SHORT).show();
                return;
            }

            /*if(strCategory.equalsIgnoreCase("student")){
                strEnroll=enrollmentNo.getText().toString();

            }else if (strCategory.equalsIgnoreCase("teacher")){
                strEnroll="0";

            }
            else{
                Toast.makeText(SignUpActivity.this,"Please Select Category",Toast.LENGTH_SHORT).show();
                return;
            }
*/
            if(strEmail.equals("")||strPwd.equals("")||
                    strBranch.equals("")||strEnroll.equals("") ||
                    strGender.equals("")||strCategory.equals("")||name.equals("")){
                Toast.makeText(this,"Please Enter all Details",Toast.LENGTH_SHORT).show();
            }else {
                if(!strEmail.contains("@")&&!strEmail.contains(".")){
                    prob=1;

            }
            if(strBranch.contains("1")||strBranch.contains("2")||strBranch.contains("3")||
                    strBranch.contains("4")||strBranch.contains("5")||strBranch.contains("6")||strBranch.contains("7")||
                    strBranch.contains("8")||strBranch.contains("9"))
                prob=2;
                if(strName.contains("1")||strName.contains("2")||strName.contains("3")||
                        strName.contains("4")||strName.contains("5")||strName.contains("6")||strName.contains("7")||
                        strName.contains("8")||strName.contains("9"))
                    prob=3;
                if(alreadyReg(strEmail))
                    prob=4;

                if(prob==1){
                    Toast.makeText(SignUpActivity.this,"Please Enter Correct Email",Toast.LENGTH_SHORT).show();

                }
                else if(prob==2)
                    Toast.makeText(SignUpActivity.this,"Please Enter Correct Branch",Toast.LENGTH_SHORT).show();
                else if(prob==3)
                    Toast.makeText(SignUpActivity.this,"Please Enter Correct Name",Toast.LENGTH_SHORT).show();
                else if(prob==4)
                    Toast.makeText(SignUpActivity.this,"Already Registered",Toast.LENGTH_SHORT).show();

                else
                {
                    sd.execSQL("insert into tname values('" +strName+"', '"+strGender+"', '"+strEnroll+"','" +strEmail+"','"+strPwd+"','"+strBranch+"','"+strCategory+"')");
                    Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }



            }


    }

    public void CreateDatabase(){
        sd= openOrCreateDatabase("dbname",0,null);

        sd.execSQL("Create table if not exists tname(name varchar(250), gender varchar(250),roll integer(250)," +
               "email varchar(250),password varchar(250),branch varchar(250),category vrchar(25))");

    }
    public boolean alreadyReg(String mail){
        boolean reg=false;


        Cursor cursor = sd.rawQuery("select * from tname", null);
        while (cursor.moveToNext()) {


            String strEmail=cursor.getString(3).toString();


            if (mail.equals(strEmail)) {
                reg=true;

            }

        }
        return reg;

    }


}
