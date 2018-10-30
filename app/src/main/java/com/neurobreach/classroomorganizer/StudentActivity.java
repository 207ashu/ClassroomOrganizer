package com.neurobreach.classroomorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentActivity extends AppCompatActivity {
    Button att,res,notice,functions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        functions=findViewById(R.id.fun);
        att=findViewById(R.id.att);
        notice=findViewById(R.id.not);
        res=findViewById(R.id.res);

        functions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentActivity.this,FunctionsActivity.class);
                startActivity(i);
            }
        });
        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentActivity.this,AttendanceActivity.class);
                startActivity(i);
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentActivity.this,NoticesActivity.class);
                startActivity(i);
            }
        });
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentActivity.this,ResActivity.class);
                startActivity(i);
            }
        });

    }

}
