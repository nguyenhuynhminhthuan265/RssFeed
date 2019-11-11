package com.example.rssfeed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class MainActivity extends Activity {
    // Main GUI - A NEWS application based on National Public Radio RSS material
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;
    SingleItem selectedNewsItem;
    TextView txtTitle;
    String title;
    String channel;
    int option; // number of lines from text file

    // hard-coding main NEWS categories (TODO: use a resource file)


    @Override
    public void onBackPressed() {
       onCreate(null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);
        final Button btnVnexpress = findViewById(R.id.btnVnexpress);
        final Button btnThanhNien = findViewById(R.id.btnThanhNien);
        final Button btn24h = findViewById(R.id.btn24h);
        final Button btnNguoiDuaTin = findViewById(R.id.btnNguoiDuaTin);
        //final Button btnDanTri = findViewById(R.id.btnThanhNien);
        btnVnexpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                txtTitle=findViewById(R.id.txtTitle);
                channel = "VNEXPRESS";
                title="CHANNELS IN VNEXPRESS";
                txtTitle.setText(title);
                String[][] myUrlCaptionMenu = {

                        {"https://vnexpress.net/rss/thoi-su.rss", "THỜI SỰ"},
                        {"https://vnexpress.net/rss/the-gioi.rss", "THẾ GIỚI"},
                        {"https://vnexpress.net/rss/kinh-doanh.rss", "KINH DOANH"},
                        {"https://vnexpress.net/rss/the-thao.rss", "THỂ THAO"},
                        {"https://vnexpress.net/rss/suc-khoe.rss", "SỨC KHỎE"},
                        {"https://vnexpress.net/rss/du-lich.rss", "DU LỊCH"}
                };

                //define convenient URL and CAPTIONs arrays

                channel(myUrlCaptionMenu,channel);

            }
        });
        btnThanhNien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                txtTitle=findViewById(R.id.txtTitle);
                channel = "THANH NIÊN";
                title="CHANNELS IN THANH NIÊN";
                txtTitle.setText(title);
                String[][] myUrlCaptionMenu = {

                        {"https://thanhnien.vn/rss/viet-nam.rss", "THỜI SỰ"},
                        {"https://thanhnien.vn/rss/the-gioi.rss", "THẾ GIỚI"},
                        {"https://thanhnien.vn/rss/doi-song.rss", "ĐỜI SỐNG"},
                        {"https://thanhnien.vn/rss/kinh-doanh.rss", "KINH DOANH"},
                        {"https://thanhnien.vn/rss/the-gioi-tre.rss", "GIỚI TRẺ"},
                        {"https://thanhnien.vn/rss/cong-nghe-thong-tin.rss", "CÔNG NGHỆ"}
                };

                //define convenient URL and CAPTIONs arrays

                channel(myUrlCaptionMenu,channel);
            }
        });


        btn24h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                txtTitle=findViewById(R.id.txtTitle);
                channel = "24h";
                title="CHANNELS IN 24H.COM.VN";
                txtTitle.setText(title);
                String[][] myUrlCaptionMenu = {

                        {"https://www.24h.com.vn/upload/rss/bongda.rss", "BÓNG ĐÁ"},
                        {"https://www.24h.com.vn/upload/rss/amthuc.rss", "ẨM THỰC"},
                        {"https://www.24h.com.vn/upload/rss/thoitrang.rss", "THỜI TRANG"},
                        {"https://www.24h.com.vn/upload/rss/taichinhbatdongsan.rss", "TÀI CHÍNH-BẤT ĐỘNG SẢN"}

                };

                //define convenient URL and CAPTIONs arrays

                channel(myUrlCaptionMenu,channel);
            }
        });


        btnNguoiDuaTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                txtTitle=findViewById(R.id.txtTitle);
                channel = "NGƯỜI ĐƯA TIN";
                title="CHANNELS IN NGƯỜI ĐƯA TIN";
                txtTitle.setText(title);
                String[][] myUrlCaptionMenu = {

                        {"https://www.nguoiduatin.vn/rss/phap-luat.rss", "PHÁP LUẬT"},
                        {"https://www.24h.com.vn/upload/rss/amthuc.rss", "NHỊP SỐNG"},
                        {"https://www.nguoiduatin.vn/rss/kinh-doanh.rss", "KINH DOANH"},
                        {"https://www.nguoiduatin.vn/rss/cong-nghe.rss", "CÔNG NGHỆ"}

                };

                //define convenient URL and CAPTIONs arrays

                channel(myUrlCaptionMenu,channel);
            }
        });









    }//onCreate

    private void channel(String[][] myUrlCaptionMenu, final String channel) {
        final String [] myUrlCaption = new String[myUrlCaptionMenu.length];
        final String [] myUrlAddress = new String[myUrlCaptionMenu.length];
        for (int i = 0; i < myUrlCaptionMenu.length; i++) {
            myUrlAddress[i] = myUrlCaptionMenu[i][0];
            myUrlCaption[i] = myUrlCaptionMenu[i][1];
        }


        context = getApplicationContext();
        this.setTitle("NPR Headline News\n" + niceDate());
        // user will tap on a ListView’s row to request category’s headlines
        myMainListView = this.findViewById(R.id.myListView);
        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> _av, View _v,
                                    int _index, long _id)
            {


                String urlAddress = myUrlAddress[_index];
                String urlCaption = myUrlCaption[_index];
                //create an Intent to talk to activity: ShowHeadlines
                Intent callShowHeadlines = new Intent(MainActivity.this,
                        ShowHeadlines.class);
                Bundle myData = new Bundle();
                myData.putString("urlAddress", urlAddress);
                myData.putString("urlCaption", urlCaption);
                myData.putString("channel", channel);
                callShowHeadlines.putExtras(myData);
                startActivity(callShowHeadlines);
            }
        });
        // fill up the Main-GUI’s ListView with main news categories
        adapterMainSubjects = new ArrayAdapter<String>(this,
                /*android.R.layout.simple_list_item_1*/
                R.layout.my_simple_list_item_1, myUrlCaption);

        myMainListView.setAdapter(adapterMainSubjects);
    }

    // method returns a value such as "Monday Apr 7, 2014"
    public static String niceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM d, yyyy ", Locale.US);
        return sdf.format(new Date() );
    }


}

