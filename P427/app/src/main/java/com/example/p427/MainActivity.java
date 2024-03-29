package com.example.p427;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Person> persons;
    ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
    }

    // 나만의 어댑터를 만든다
    class PersonAdapter extends BaseAdapter{

        ArrayList<Person> datas;

        public PersonAdapter(ArrayList<Person> datas){
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
           TextView tx_name = view.findViewById(R.id.tx_name);
           TextView tx_phone = view.findViewById(R.id.tx_phone);
           Person p = datas.get(position);
           im.setImageResource(p.getId());
           tx_name.setText(p.getName());
           tx_phone.setText(p.getPhone());

           return view;
        }
    }

    public void setList(ArrayList<Person> persons){
        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }




    public void getData(){
        persons = new ArrayList<>();
        persons.add(new Person(R.drawable.p1,"Lee MAlsook","010-9098-2983"));
        persons.add(new Person(R.drawable.p2,"Kim MAlsook","010-9098-2983"));
        persons.add(new Person(R.drawable.p3,"Lim MAlsook","010-9098-2983"));
        persons.add(new Person(R.drawable.p4,"OH MAlsook","010-9098-2983"));
        persons.add(new Person(R.drawable.p5,"HONG MAlsook","010-9098-2983"));
        persons.add(new Person(R.drawable.p6,"Ko MAlsook","010-9098-2983"));
        persons.add(new Person(R.drawable.p7,"Jung MAlsook","010-9098-2983"));
        persons.add(new Person(R.drawable.p8,"Jin MAlsook","010-9098-2983"));
        persons.add(new Person(R.drawable.p9,"Cha MAlsook","010-9098-2983"));
        setList(persons);
    }


    public void ckbt(View v){
        getData();
    }

}