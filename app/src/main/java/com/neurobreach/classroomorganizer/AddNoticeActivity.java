package com.neurobreach.classroomorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoticeActivity extends AppCompatActivity {

    EditText title,msg;
    Button submitNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        title=findViewById(R.id.title);
        msg=findViewById(R.id.msg);


        submitNotice=findViewById(R.id.submit_notice);
        submitNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strTitle=title.getText().toString();
                String strMsg=msg.getText().toString();

                if(strMsg.equals("")||strTitle.equals("")){

                }else{
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference("notice");
                    DatabaseReference myRef = database.child(strTitle);

                    myRef.setValue(strMsg);
                }
            }
        });

    }
}
