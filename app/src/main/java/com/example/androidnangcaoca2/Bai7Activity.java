package com.example.androidnangcaoca2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Bai7Activity extends AppCompatActivity {
    private Button btnClick;
    private ImageView imvAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai7);
        btnClick = findViewById(R.id.btnRotate);
        imvAnimation = findViewById(R.id.imvAnimation);

        Animation animationButton = AnimationUtils.loadAnimation(getBaseContext(), R.anim.up);
        btnClick.setAnimation(animationButton);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imvAnimation.setImageResource(R.drawable.ic_launcher_background);
//                imvAnimation.setImageResource(R.drawable.all);
//                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake);
//                imvAnimation.setAnimation(animation);
                YoYo.with(Techniques.FadeInUp)
                        .duration(700)
                        .repeat(5)
                        .playOn(findViewById(R.id.imvAnimation));
            }
        });
    }
}
