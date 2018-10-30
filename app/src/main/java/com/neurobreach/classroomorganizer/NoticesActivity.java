package com.neurobreach.classroomorganizer;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

public class NoticesActivity extends AppCompatActivity {

    Boolean InternetStatus;

    private NoticeAdapter noticeAdapter;
    private static ArrayList<NoticeItem> mGridData;

   // public static ArrayList<Sandwich> mSandwichList;
    RecyclerView recyclerView;
   // SandwichAdapter adapter;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);
        mGridData = new ArrayList<>();
        recyclerView=findViewById(R.id.rv_notice);
        LinearLayoutManager llm = new LinearLayoutManager(NoticesActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        URL movieSearchUrl = NetworkUtility.buildUrl();
        new NoticeQueryTask().execute(movieSearchUrl);
    }

    public  boolean checkInternet(){
        boolean internetStatus=true;
        ConnectivityManager connec=(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        if(connec.getNetworkInfo(0).getState()== android.net.NetworkInfo.State.CONNECTED||connec.getNetworkInfo(1).getState()==android.net.NetworkInfo.State.CONNECTED
                ||connec.getNetworkInfo(0).getState()== NetworkInfo.State.CONNECTING||connec.getNetworkInfo(1).getState()== NetworkInfo.State.CONNECTING)
            internetStatus=true;
        else if(connec.getNetworkInfo(0).getState()== NetworkInfo.State.DISCONNECTED||connec.getNetworkInfo(1).getState()== NetworkInfo.State.DISCONNECTED)
            internetStatus=false;

        return internetStatus;
    }

    private class NoticeQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String SearchResults = null;
            try {
                InternetStatus=checkInternet();
                if(InternetStatus==true)
                    SearchResults = NetworkUtility.getResponseFromHttpUrl(searchUrl);
                else SearchResults=null;
            } catch (Exception e) {
                e.printStackTrace();
                SearchResults=null;
            }
            mGridData=NoticeJson.getJsonData(SearchResults);
            return SearchResults;
        }

        @Override
        protected void onPostExecute(String movieSearchResults) {
            if (movieSearchResults != null && !movieSearchResults.equals("")) {
                noticeAdapter = new NoticeAdapter(NoticesActivity.this,mGridData);

                recyclerView.setAdapter(noticeAdapter);
            } else {
                Toast.makeText(NoticesActivity.this,"Couldn't Load data",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
