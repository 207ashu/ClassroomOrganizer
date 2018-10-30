package com.neurobreach.classroomorganizer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase sd;
    Button signUp,studLogin,tchrLogin;
    EditText email,pwd;
    String a,b;
    String category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signUp=findViewById(R.id.signUp);
        email=findViewById(R.id.email);
        pwd=findViewById(R.id.pwd);
        studLogin=findViewById(R.id.studentLogin);
        tchrLogin=findViewById(R.id.facultyLogin);


        sd = openOrCreateDatabase("dbname", 0, null);


        studLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn("student");
            }
        });
        tchrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn("teacher");
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
    public void signIn(String cat){
        a=email.getText().toString();
        b=pwd.getText().toString();


        Cursor cursor = sd.rawQuery("select * from tname", null);
        int flag=0;

        //Toast.makeText(SignUp.this,a + " " + b,Toast.LENGTH_SHORT).show();
        while (cursor.moveToNext()) {


            String strEmail=cursor.getString(3).toString();
            String strPwd=cursor.getString(4).toString();


            if (a.equals(strEmail) && b.equals(strPwd)) {
                flag=1;
                category=cursor.getString(6).toString();
            }

        }
        if(cat.equalsIgnoreCase(category)){
            flag=1;
        }else{
            flag=0;
        }
        if(flag==0){

            Toast.makeText(this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();
        }else{Intent i;

            if(category.equalsIgnoreCase("student"))
                 i = new Intent(LoginActivity.this, StudentActivity.class);
            else
                i = new Intent(LoginActivity.this, AddNoticeActivity.class);
            startActivity(i);
            finish();
        }
    }
}
