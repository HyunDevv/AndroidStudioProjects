package com.example.ws;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Fragment1 extends Fragment {

    ListView listView;
    LinearLayout container;
    ArrayList<Movie> movies; // 서버에 있는 JSON데이터를 관리
    ArrayList<String> datas;

    Integer[] imgarr = {R.drawable.m1,R.drawable.m2,R.drawable.m3,R.drawable.m4,R.drawable.m5,
            R.drawable.m6,R.drawable.m7,R.drawable.m8,R.drawable.m9,R.drawable.m10};

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup viewGroup = null;
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_1,container,false);
        listView = viewGroup.findViewById(R.id.listView);
        movies = new ArrayList<>();

        getData();

        //리스트 클릭 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


                builder.setTitle(movies.get(position).getMovieNm());
                builder.setMessage("일별 박스오피스 "+movies.get(position).rank + "위\n개봉일 : "+movies.get(position).openDt+"\n일별관람객 수 : "+movies.get(position).showCnt+"명");

                //OK
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();

            }
        });


        return viewGroup;
    } // end onCreateView


    // 나만의 어댑터를 만든다
    class MovieAdapter extends BaseAdapter {

        ArrayList<Movie> movies;

        public MovieAdapter(ArrayList<Movie> movies) {
            this.movies = movies;
        }

        @Override
        public int getCount() {
            return movies.size();
        }

        @Override
        public Object getItem(int position) {
            return movies.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            LayoutInflater inflater =
                    //getSystemService 앞에 getActivity().을 붙여야한다!
                    (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(
                    R.layout.item,
                    container,
                    true

            );
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView = view.findViewById(R.id.textView);
            TextView textView2 = view.findViewById(R.id.textView2);

            textView.setText(movies.get(position).getRank()+"위");
            textView2.setText(movies.get(position).getMovieNm());
            imageView.setImageResource(imgarr[position]);



            return view;
        }
    }


    public void getData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);

        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt="+sdf.format(cal.getTime());
        MovieAsync movieAsync = new MovieAsync();
        movieAsync.execute(url);


        //setList(movies);

    }



//    public void setList(final ArrayList<Movie> movies){
//        //아답터를 넣어 줌으로서 데이터를 뿌린다
//
//        final MovieAdapter movieAdapter = new MovieAdapter(movies);
//        listView.setAdapter(movieAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent,
//                                    View view, final int position, long id) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Hi");
//                //여기 datas를 사용하려면 final이 되어야 한다
//                builder.setMessage(datas.get(position) + " Delete?");
//
//                //OK
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // 해당 데이터를 제거
//                        datas.remove(position);
//                        // 제거 후 다시 화면을 refresh 한다!!!!
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//                //NO를 누르면 그대로
//                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            }
//        });
//    }




    class MovieAsync extends AsyncTask<String,Void,String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
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
                    movies.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("[TEST]","error");
            }

            MovieAdapter movieAdapter = new MovieAdapter(movies);
            listView.setAdapter(movieAdapter);

        }
    } // end AsyncTask

}

