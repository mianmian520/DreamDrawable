package com.boge.dreamdrawable;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.boge.library.IconView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {


    @Bind(R.id.iv1)
    IconView iv1;
    @Bind(R.id.iv2)
    IconView iv2;
    @Bind(R.id.iv3)
    IconView iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        iv1.getmDrawable().setmContentText("雷");
        iv2.getmDrawable().setmTextColor(Color.RED).setmBgColor(Color.YELLOW).setmContentText("L");
        iv3.getmDrawable().setmBgColor(Color.RED).setmBitMap(BitmapFactory.decodeResource(getResources(), R.drawable.timg1));
    }
}
