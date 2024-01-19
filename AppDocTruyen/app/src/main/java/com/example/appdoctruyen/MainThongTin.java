package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainThongTin extends AppCompatActivity {

    TextView txtThongtinapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);

        txtThongtinapp = findViewById(R.id.textviewthongtin);
        String thongtin = "Ứng dụng đuợc phát bởi Hào Đoàn\n" +
                " Uy tín 100%\n" +
                " OK";
        txtThongtinapp.setText(thongtin);
    }
}