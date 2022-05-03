package com.example.asm_dam_tienbxph18636;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView nav;
    DrawerLayout drawerLayout;
    FragmentManager fm;
    GioiThieuFragment gioiThieuFragment;
    MainFragment mainFragment;
    Main2Fragment main2Fragment;
    DoiMatKhauFragment doiMatKhauFragment;
    String quyen = "";
    int index = 0;

    public String getQuyen() {
        return quyen;
    }

    public int getIndex() {
        return index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.bar_main);
        drawerLayout = findViewById(R.id.drw_layout);
        nav = findViewById(R.id.drw_nav);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        gioiThieuFragment = new GioiThieuFragment();
        mainFragment = new MainFragment();
        main2Fragment = new Main2Fragment();
        doiMatKhauFragment = new DoiMatKhauFragment();

        Intent intent = getIntent();
        quyen = intent.getStringExtra("quyen");
        index = intent.getIntExtra("idtt", 0);


        fm = getSupportFragmentManager();
        if(quyen.equals("admin")){
            fm.beginTransaction().add(R.id.fr_container, main2Fragment).commit();
        }else{
            fm.beginTransaction().add(R.id.fr_container, mainFragment).commit();
        }


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_trangchu:
                        if(quyen.equals("admin")){
                            fm.beginTransaction().add(R.id.fr_container, main2Fragment).commit();
                        }else{
                            fm.beginTransaction().add(R.id.fr_container, mainFragment).commit();
                        }
                        break;
                    case R.id.item_gt:
                        fm.beginTransaction().add(R.id.fr_container, gioiThieuFragment).commit();
                        break;

                    case R.id.item_changepass:
                        fm.beginTransaction().add(R.id.fr_container, doiMatKhauFragment).commit();
                        break;
                    case R.id.item_out:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startActivity(startMain);
                        finish();
                }

                drawerLayout.closeDrawer(nav);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(nav);
        }

        return super.onOptionsItemSelected(item);
    }
}