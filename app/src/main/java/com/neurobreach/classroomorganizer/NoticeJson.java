package com.neurobreach.classroomorganizer;

import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class NoticeJson {
    public static ArrayList<NoticeItem> getJsonData(String JsonFile){

        ArrayList<NoticeItem> items=new ArrayList<>();

        try {
            if(JsonFile!=null) {

                NoticeItem item;
                JSONObject root = new JSONObject(JsonFile);
                JSONArray keys = root.names ();
                for (int i = 0; i < keys.length (); ++i) {

                    String key = keys.getString (i); // Here's your key
                    String value = root.getString (key); // Here's your value

                    item=new NoticeItem(key,value);
                    item.setT(key);
                    item.setDesc(value);
//                    NoticesActivity.mGridData.add(item);
                    items.add(item);

                }

               /* Iterator<String> keys = root.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    if (jObject.get(key) instanceof JSONObject) {

                    }
                }*/
                /*JSONArray resultsArray = root.optJSONArray("results");
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject eachItem = resultsArray.optJSONObject(i);
//
                    item = new MovieItem(title, posterPath,movieId);
                    item.setTitle(title);
                    item.setPosterPath(posterPath);
                    item.setMovieId(movieId);
                    MovieAdapter.mGridData.add(item);
                } */
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    return items;
    }
}
