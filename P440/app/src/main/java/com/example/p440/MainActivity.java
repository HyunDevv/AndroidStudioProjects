package com.example.p440;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Person> persons;

    TextView textView;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    ListView listView;
    LinearLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persons = new ArrayList<>();

        listView = findViewById(R.id.listView);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

    }


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
            TextView tx_name = view.findViewById(R.id.tx_name);
            TextView tx_birth = view.findViewById(R.id.tx_birth);
            TextView tx_phone = view.findViewById(R.id.tx_phone);
            Person p = datas.get(position);
            tx_name.setText(p.getName());
            tx_birth.setText(p.getBirth());
            tx_phone.setText("" + p.getPhone());

            return view;
        }
    }



    public void setList(final ArrayList<Person> persons) {
        final PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }

    public void getData(String name,String birth,String phone){

        persons.add(new Person(name,birth,phone));
        setList(persons);

    }



    public void ckbt(View v){
       if(v.getId() == R.id.button){

        String name = editText1.getText().toString();
        String birth = editText2.getText().toString();
        String phone = editText3.getText().toString();

        getData(name,birth,phone);

       }
    }

}