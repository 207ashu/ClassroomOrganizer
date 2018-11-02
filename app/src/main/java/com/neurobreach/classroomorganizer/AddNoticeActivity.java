package com.neurobreach.classroomorganizer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                final String strTitle=title.getText().toString();
                final String strMsg=msg.getText().toString();

                if(strMsg.equals("")||strTitle.equals("")){

                }else{
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference("notice");
                    DatabaseReference myRef = database.child(strTitle);
                    final AlertDialog[] dialog = new AlertDialog[1];

                    myRef.setValue(strMsg);
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(AddNoticeActivity.this);
                            builder.setTitle("Notice Submitted \n"+strTitle);
                            builder.setMessage(""+strMsg);
                            dialog[0] =builder.create();
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    dialog[0].dismiss();
                                }
                            });


                            dialog[0].show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Toast.makeText(AddNoticeActivity.this,"Couldn't Submit Notice. Try again later",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}
