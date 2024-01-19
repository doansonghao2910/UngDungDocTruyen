package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainNoiDung extends AppCompatActivity {

    TextView txtTenTruyen, txtNoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noi_dung);

        txtTenTruyen = findViewById(R.id.TenTruyen);
        txtNoiDung = findViewById(R.id.noidung);

        //Lấy dữ liệu
        Intent intent = getIntent();
        String tentruyen = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");

        txtTenTruyen.setText(tentruyen);
        txtNoiDung.setText(noidung);

        //Cho phép cuộn nội dung chuyện
        txtNoiDung.setMovementMethod(new ScrollingMovementMethod());
    }
}