package com.example.p5333;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    LinearLayout container;
    ArrayList<Movie> list; // 서버에 있는 JSON데이터를 관리

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        list = new ArrayList<>();
        getData();


        //리스트 클릭 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);


                builder.setTitle(list.get(position).getMovieNm());
                builder.setMessage("일별 박스오피스 "+list.get(position).rank + "위\n개봉일 : "+list.get(position).openDt+"\n일별관람객 수 : "+list.get(position).showCnt+"명");

                //OK
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();

            }
        });
    }

    private void getData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Log.d("[TEST]",sdf.format(cal.getTime()));

        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt="+sdf.format(cal.getTime());
        MovieAsync itemAsync = new MovieAsync();
        itemAsync.execute(url);
    }

    class MovieAsync extends AsyncTask<String,Void,String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SecondActivity.this);
            progressDialog.setTitle("Get Data ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //Manifest 수정 필요!
            String url = strings[0];
            String result = HttpConnect.getString(url); //result는 JSON
            return result;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            JSONArray ja = null;
            JSONObject jo = null;


            try {
                jo = new JSONObject(s);
                String str = jo.getString("boxOfficeResult");
                jo = new JSONObject(str);
                str = jo.getString("dailyBoxOfficeList");
                s = str;
                ja = new JSONArray(s);
                for(int i=0; i<ja.length(); i++){
                    JSONObject job = ja.getJSONObject(i);
                    String rank = job.getString("rank");
                    String openDt = job.getString("openDt");
                    String movieNm = job.getString("movieNm");
                    String showCnt = job.getString("showCnt");
                    Movie movie = new Movie(rank,openDt,movieNm,showCnt);
                    list.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("[TEST]","error");
            }

            MovieAdapter movieAdapter = new MovieAdapter();
            listView.setAdapter(movieAdapter);

        }
    } // end AsyncTask



    class MovieAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater =
                    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.movie, container, true);
            TextView tx_rank = itemView.findViewById(R.id.textView);
            TextView tx_movieNm = itemView.findViewById(R.id.textView3);

            tx_rank.setText(list.get(position).getRank()+"위");
            tx_movieNm.setText(list.get(position).getMovieNm());

            return itemView;
        }

    } // end Adapter

}