package com.hee1kang.klibdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hee1kang.klib.Tog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tog.te("");
    }


}
