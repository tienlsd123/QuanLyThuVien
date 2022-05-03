package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ChoiceAccActivity extends AppCompatActivity {
    ImageView img_tt, img_ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_acc);
        img_ad = findViewById(R.id.img_admin);
        img_tt = findViewById(R.id.img_thuThu);
        img_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_choice);
                img_tt.startAnimation(animation);
                Intent intent = new Intent(getApplicationContext(), LoginThuThuActivity.class);
                startActivity(intent);
            }
        });

        img_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_choice);
                img_ad.startAnimation(animation);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}