package com.neurobreach.classroomorganizer;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AttendanceActivity extends AppCompatActivity {

    EditText enroll;
    Button attendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        attendance=findViewById(R.id.attendance);
        enroll=findViewById(R.id.enrollment_no_att);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String en=enroll.getText().toString();
                if(en.equalsIgnoreCase(""))
                    Toast.makeText(AttendanceActivity.this,"Enter Correct Details",Toast.LENGTH_SHORT).show();
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Attendance");
                    builder.setMessage("Your Attendance is 65 %");
                    builder.show();
                }
            }
        });
    }
}
