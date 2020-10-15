package com.example.p428;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Person> persons;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        //리스트 클릭 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater layoutInflater = getLayoutInflater();
                View dview = layoutInflater.inflate(R.layout.dialog,
                        (ViewGroup) findViewById(R.id.dlayout));
                ImageView dimg = dview.findViewById(R.id.imageView2);
                dimg.setImageResource(persons.get(position).getImg());
                builder.setView(dview);


                builder.setTitle("NAME:" + persons.get(position).getName());
                //여기 datas를 사용하려면 final이 되어야 한다
                builder.setMessage(persons.get(position).id + "");

                //OK
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //NO를 누르면 그대로
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();

            }
        });

    }// end onCreate



    // 나만의 어댑터를 만든다
    class PersonAdapter extends BaseAdapter {

        ArrayList<Person> datas;

        public PersonAdapter(ArrayList<Person> datas) {
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            LayoutInflater inflater =
                    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(
                    R.layout.person,
                    container,
                    true

            );
            ImageView im = view.findViewById(R.id.imageView);
            TextView tx_id = view.findViewById(R.id.tx_id);
            TextView tx_name = view.findViewById(R.id.tx_name);
            TextView tx_age = view.findViewById(R.id.tx_age);
            Person p = datas.get(position);
            im.setImageResource(p.getImg());
            tx_id.setText(p.getId());
            tx_name.setText(p.getName());
            tx_age.setText("" + p.getAge());

            return view;
        }
    }

    public void setList(final ArrayList<Person> persons) {
        final PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }


    public void getData() {
        persons = new ArrayList<>();
        persons.add(new Person(R.drawable.p1, "id01", "Lee MAlsook", 10));
        persons.add(new Person(R.drawable.p2, "id02", "Kim MAlsook", 20));
        persons.add(new Person(R.drawable.p3, "id03", "Lim MAlsook", 30));
        persons.add(new Person(R.drawable.p4, "id04", "OH MAlsook", 40));
        persons.add(new Person(R.drawable.p5, "id05", "HONG MAlsook", 50));
        persons.add(new Person(R.drawable.p6, "id06", "Ko MAlsook", 60));
        persons.add(new Person(R.drawable.p7, "id07", "Jung MAlsook", 70));
        persons.add(new Person(R.drawable.p8, "id08", "Jin MAlsook", 80));
        persons.add(new Person(R.drawable.p9, "id09", "Cha MAlsook", 90));
        setList(persons);
    }


    public void ckbt(View v) {
        getData();
    }

}