package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class OpenActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        imageView = findViewById(R.id.img_logo);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.open_anim);
        imageView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(OpenActivity.this, ChoiceAccActivity.class);
                startActivity(intent);
            }
        }, 2500);

    }
}