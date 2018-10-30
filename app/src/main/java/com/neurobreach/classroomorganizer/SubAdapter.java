package com.neurobreach.classroomorganizer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends ArrayAdapter<SubjectDetails> {
    public SubAdapter(Activity context, ArrayList<SubjectDetails> word){
        super(context,0, (List<SubjectDetails>) word);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.single_res_item, parent, false);
        }
        SubjectDetails currentWord = getItem(position);

        TextView tvSubName=listItemView.findViewById(R.id.sub_name);
        tvSubName.setText(currentWord.getSubName());
        TextView tvSubCode=listItemView.findViewById(R.id.sub_code);
        tvSubCode.setText(currentWord.getSubCode());
        TextView total=listItemView.findViewById(R.id.total_marks);
        total.setText(currentWord.getTotal());
        TextView tvInt=listItemView.findViewById(R.id.int_marks);
        tvInt.setText(currentWord.getIntMarks());
        TextView tvExt=listItemView.findViewById(R.id.ext_marks);
        tvExt.setText(currentWord.getExtMarks());

        /*TextView tvMiwok = (TextView) listItemView.findViewById(R.id.tvMiwok);
        tvMiwok.setText(currentWord.getMiwokTranslation());
        TextView tvDefault = (TextView) listItemView.findViewById(R.id.tvDefault);
        tvDefault.setText(currentWord.getDefaultTranslation());*/

        return listItemView;
    }
}
