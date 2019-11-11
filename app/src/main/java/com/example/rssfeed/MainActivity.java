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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    // Main GUI - A NEWS application based on National Public Radio RSS material
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;
    SingleItem selectedNewsItem;
    TextView txtTitle;
    String title;
    String channel;
    Boolean toExit;
    int option; // number of lines from text file

    // hard-coding main NEWS categories (TODO: use a resource file)


    @Override
    public void onBackPressed() {
        if (toExit == false) {

            onCreate(null);
        } else
            super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);
        toExit = true;
        final Button btnVnexpress = findViewById(R.id.btnVnexpress);
        final Button btnThanhNien = findViewById(R.id.btnThanhNien);
        final Button btn24h = findViewById(R.id.btn24h);
        final Button btnNguoiDuaTin = findViewById(R.id.btnNguoiDuaTin);
        final Button btnVietNamNet = findViewById(R.id.btnVietNamNet);
        final Button btnVTCVN = findViewById(R.id.btnVTC);

        btnVnexpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                txtTitle=findViewById(R.id.txtTitle);
                channel = "VNEXPRESS";
                title="CHANNELS IN VNEXPRESS";
                txtTitle.setText(title);
                toExit = false;
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
                toExit = false;
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
                toExit = false;
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
                toExit = false;
                String[][] myUrlCaptionMenu = {

                        {"https://www.nguoiduatin.vn/rss/phap-luat.rss", "PHÁP LUẬT"},
                        {"https://www.nguoiduatin.vn/rss/nhip-song.rss", "NHỊP SỐNG"},
                        {"https://www.nguoiduatin.vn/rss/kinh-doanh.rss", "KINH DOANH"},
                        {"https://www.nguoiduatin.vn/rss/cong-nghe.rss", "CÔNG NGHỆ"}

                };

                //define convenient URL and CAPTIONs arrays

                channel(myUrlCaptionMenu,channel);
            }
        });

        btnVietNamNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                txtTitle = findViewById(R.id.txtTitle);
                channel = "VietNamNet";
                title = "CHANNELS IN VietNamNet";
                txtTitle.setText(title);
                toExit = false;
                String[][] myUrlCaptionMenu = {
                        {"https://vietnamnet.vn/rss/thoi-su-chinh-tri.rss", "Chính trị"},
                        {"https://vietnamnet.vn/rss/talkshow.rss", "Talkshow"},
                        {"https://vietnamnet.vn/rss/thoi-su.rss", "Thời sự"},
                        {"https://vietnamnet.vn/rss/kinh-doanh.rss", "Kinh doanh"},
                        {"https://vietnamnet.vn/rss/giai-tri.rss", "Giải trí"},
                        {"https://vietnamnet.vn/rss/the-gioi.rss", "Thế giới"},
                        {"https://vietnamnet.vn/rss/giao-duc.rss", "Giáo dục"},
                        {"https://vietnamnet.vn/rss/doi-song.rss", "Đời sống"},
                        {"https://vietnamnet.vn/rss/phap-luat.rss", "Pháp luật"},
                        {"https://vietnamnet.vn/rss/the-thao.rss", "Thể thao"},
                        {"https://vietnamnet.vn/rss/cong-nghe.rss", "Công nghệ"},
                        {"https://vietnamnet.vn/rss/suc-khoe.rss", "Sức khỏe"},
                        {"https://vietnamnet.vn/rss/bat-dong-san.rss", "Bất động sản"},
                        {"https://vietnamnet.vn/rss/ban-doc.rss", "Bạn đọc"},
                        {"https://vietnamnet.vn/rss/tuanvietnam.rss", "Tuần Việt Nam"},
                        {"https://vietnamnet.vn/rss/oto-xe-may.rss", "Xe"},
                };

                //define convenient URL and CAPTIONs arrays

                channel(myUrlCaptionMenu, channel);

            }
        });

        btnVTCVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                txtTitle = findViewById(R.id.txtTitle);
                channel = "VTC.VN";
                title = "CHANNELS IN VTC.VN";
                txtTitle.setText(title);
                toExit = false;
                String[][] myUrlCaptionMenu = {
                        {"https://vtc.vn/feed.rss", "Trang chủ"},
                        {"https://vtc.vn/xa-hoi.rss", "Xã hội"},
                        {"https://vtc.vn/kinh-te.rss", "Kinh tế"},
                        {"https://vtc.vn/truyen-hinh.rss", "Truyền hình"},
                        {"https://vtc.vn/phap-luat.rss", "Pháp luật"},
                        {"https://vtc.vn/the-gioi.rss", "Thế giới"},
                        {"https://vtc.vn/dia-oc.rss", "Địa ốc"},
                        {"https://vtc.vn/giai-tri.rss", "Giải trí"},
                        {"https://vtc.vn/the-thao.rss", "Thể thao"},
                        {"https://vtc.vn/gioi-tre.rss", "Giới trẻ"},
                        {"https://vtc.vn/doanh-nghiep-doanh-nhan.rss", "Doanh nghiệp - Doanh nhân"},
                        {"https://vtc.vn/phong-su-kham-pha.rss", "Phóng sự khám phá"},
                        {"https://vtc.vn/giao-duc.rss", "Giáo dục"},
                        {"https://vtc.vn/cong-nghe.rss", "Công nghệ"},
                        {"https://vtc.vn/oto-xe-may.rss", "Ô tô xe máy"},
                        {"https://vtc.vn/suc-khoe-doi-song.rss", "Sức khỏe đời sống"},
                        {"https://vtc.vn/khoe-va-dep.rss", "Khỏe và đẹp"}
                };

                //define convenient URL and CAPTIONs arrays

                channel(myUrlCaptionMenu, channel);

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
        this.setTitle("Vietnam Headline News\n" + niceDate());
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

