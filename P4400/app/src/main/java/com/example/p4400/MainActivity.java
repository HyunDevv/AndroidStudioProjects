package com.example.p4400;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

        listView = findViewById(R.id.listView);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);



    }


    public void getData(String name,String birth,String phone){

        persons.add(new Person);

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