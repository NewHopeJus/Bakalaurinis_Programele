package com.mastercoding.bakalaurinis.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mastercoding.bakalaurinis.R;

public class Garden extends AppCompatActivity {

    Button btn;
    ImageView cloud1;
    ImageView cloud2;
    boolean isFirstImageVisible = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
//        btn = findViewById(R.id.button);
//        cloud1 =findViewById(R.id.imageView);
//        cloud2 = findViewById(R.id.imageView2);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isFirstImageVisible) {
//                    cloud1.setVisibility(View.INVISIBLE);
//                } else {
//                    cloud1.setVisibility(View.INVISIBLE);
//                    cloud2.setVisibility(View.INVISIBLE);
//                }
//                isFirstImageVisible = !isFirstImageVisible;
//            }
//        });
    }
}