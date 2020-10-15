package com.example.p426;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
    }

    public void setList(final ArrayList<String> datas){
        //아답터를 넣어줌으로서 데이터를 뿌린다
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        datas
                );
        listView.setAdapter(adapter);

        //리스트 클릭 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Hi");
                //여기 datas를 사용하려면 final이 되어야 한다
                builder.setMessage(datas.get(position) + " Delete?");

                //OK
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 해당 데이터를 제거
                        datas.remove(position);
                        // 제거 후 다시 화면을 refresh 한다!!!!
                        adapter.notifyDataSetChanged();
                    }
                });
                //NO를 누르면 그대로
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                //builder.show()로 해도 됨.. 위 2줄 대신

            }
        });
    }

    // 임의의 리스트 데이터를 만든다
    public void getData(){
        datas = new ArrayList<>();
        for(int i=0; i<=20; i++){
            datas.add("Item:"+i);
        }
        setList(datas);
    }

    public void ckbt(View v){
        getData();
    }
}