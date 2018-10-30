package com.neurobreach.classroomorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ResActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);

        ArrayList<SubjectDetails> word=new ArrayList<SubjectDetails>();

        word.add(new SubjectDetails("Mathematics -IV","BCA202","53 (B)","21","32"));

        word.add(new SubjectDetails("Web Technologies","BCA204","53 (B+)","21","37"));

        word.add(new SubjectDetails("Java Programming","BCA206","62 (B+)","23","39"));

        word.add(new SubjectDetails("Software Engineering","BCA208","61 (B+)","24","37"));

        word.add(new SubjectDetails("Computer Networks","BCA210","44 (P)","23","21"));

        word.add(new SubjectDetails("Practial VII Java Lab","BCA252","80 (A+)","32","48"));

        word.add(new SubjectDetails("Practical VIII WebTech Lab","BCA254","86 (A+)","35","51"));

        word.add(new SubjectDetails("Personality Development Skills","BCA256","75 (A+)","0","75"));



        SubAdapter itemsAdapter = new SubAdapter(ResActivity.this,word);
        ListView listView=(ListView)findViewById(R.id.myList);
        listView.setAdapter(itemsAdapter);
    }
}
