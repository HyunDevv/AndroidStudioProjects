package com.example.p351;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    SettingFragment settingFragment;
    BottomNavigationView bottomNavigationView;

    ActionBar actionBar; // 메뉴 만들기 (androidx를 가져와야 한다)

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        settingFragment = new SettingFragment();

        //처음 화면을 fragment1로 설정한다
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(
                R.id.frame_layout, fragment1).commit();

        actionBar = getSupportActionBar();
        actionBar.setTitle("P351");
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_SHOW_TITLE);

        //하단탭 설정
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.menu1){
                    fragmentManager.beginTransaction().replace(
                            R.id.frame_layout, fragment1).commit();
                }else if(menuItem.getItemId() == R.id.menu2){
                    fragmentManager.beginTransaction().replace(
                            R.id.frame_layout, fragment2).commit();
                }else if(menuItem.getItemId() == R.id.menu3){
                    fragmentManager.beginTransaction().replace(
                            R.id.frame_layout, fragment3).commit();
                }
                return false;
            }
        });  // end function

    }

    //메뉴(...)를 붙임
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //메뉴(...)에 이벤트를 생성
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.m1) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.frame_layout, settingFragment
            ).commit();

        }
        return super.onOptionsItemSelected(item);
    }
}