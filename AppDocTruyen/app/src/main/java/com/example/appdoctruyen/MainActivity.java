package com.example.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.adapter.adapterchuyenmuc;
import com.example.appdoctruyen.adapter.adapterthongtin;
import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.TaiKhoan;
import com.example.appdoctruyen.model.Truyen;
import com.example.appdoctruyen.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;

    adapterTruyen adapterTruyen;

    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;

    databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);

        // Nhận dữ liệu ở màn đăng nhập
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanq", 0);
        int idd = intentpq.getIntExtra("idd", 0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        AnhXa();
        ActionBar();
        ActionViewFlipper();

        //Bắt sự kiện click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, MainNoiDung.class);

                    String tent = TruyenArraylist.get(i).getTenTruyen();
                    String noidungt = TruyenArraylist.get(i).getNoiDung();
                    intent.putExtra("tentruyen", tent);
                    intent.putExtra("noidung", noidungt);
                    startActivity(intent);
            }
        });

        // Bắt click item cho listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Đăng bài
                if(position == 0){
                    if(i == 2){
                        Intent intent = new Intent(MainActivity.this, MainAdmin.class);
                        //Gửi id tài khoản qua main admin
                        intent.putExtra("Id", idd);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài" , "Bạn không có quyền");
                    }
                }
                //Nếu vị trí ấn vào là thông tin sẽ chuyển qua màn hình thông tin app
                else  if(position == 1){
                    Intent intent = new Intent(MainActivity.this, MainThongTin.class);
                    startActivity(intent);
                }
                else  if(position == 2){
                        System.exit(0);
                }
            }
        });
    }

    // Thanh actionbar vs toolbar
    private void ActionBar() {
        // Hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    // Phương thức cho chạy quảnh cáo với ViewFlipper
    private void ActionViewFlipper() {
        // Mảng chứa tấm ảnh cho quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
        // Add vào mảng
        mangquangcao.add("https://cdn.marrybaby.vn/wp-content/uploads/2017/10/11/ke-chuyen-cho-be-ngu-2.jpg");
        mangquangcao.add("https://cdn.marrybaby.vn/wp-content/uploads/2017/10/11/ke-chuyen-cho-be-ngu-5.jpg");
        mangquangcao.add("https://cdn.marrybaby.vn/wp-content/uploads/2021/10/truyen-co-tich-cho-be-ngu-ngon_009.png");
        mangquangcao.add("https://cdn.marrybaby.vn/wp-content/uploads/2021/10/truyen-co-tich-cho-be-ngu-ngon-008.jpg");

        for (int i = 0; i < mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);
        }

        // Thiết lập tự động chay cho viewflipper trong 4 giay
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        // Gọi Anination cho vào và ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        // Gọi Animation vào trong viewFlipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private  void AnhXa(){
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewNew = findViewById(R.id.listviewNew);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArraylist = new ArrayList<>();

        Cursor cursor1 = databasedoctruyen.getData1();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id, tentruyen, noidung, anh, id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArraylist);
            listViewNew.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        //Thông tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan, email));
        adapterthongtin = new adapterthongtin(this, R.layout.navigation_thongtin, taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);

        //Chuyên mục
        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài", R.drawable.ic_baseline_post_add_24));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin", R.drawable.baseline_face_24));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất", R.drawable.baseline_login_24));

        adapterchuyenmuc = new adapterchuyenmuc(this, R.layout.chuyenmuc, chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);
    }

    // Thêm 1 menu tìm kiếm vào actionbar


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Nếu click vào hàm tìm kiếm sẽ chuyển qua màn hình tìm kiếm
        int x = R.id.menu1;
        if(item.getItemId() == x){
            Intent intent = new Intent(MainActivity.this, MainTimKiem.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}