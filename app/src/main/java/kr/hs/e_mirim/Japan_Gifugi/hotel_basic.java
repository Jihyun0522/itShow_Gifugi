package kr.hs.e_mirim.Japan_Gifugi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class hotel_basic extends AppCompatActivity {
    Context context;
    Intent intent;

    ImageButton search;

    TextView menu_text;
    RelativeLayout back;

    ImageView hotel_list_img1, hotel_list_img2, hotel_list_img3, hotel_list_img4, hotel_list_img5, hotel_list_img6;
    TextView hotel_list_name1, hotel_list_name2, hotel_list_name3, hotel_list_name4, hotel_list_name5, hotel_list_name6;

    LinearLayout hot_1, hot_2, hot_3;

    LinearLayout hotel;

    String name, email, pw, activity;

    String[] hotel_name = {"기후 그랜드 호텔 GIFU GRAND HOTEL",
            "료칸 나가라 간코 호텔 이시킨 Ryokan Nagara Kanko Hotel Ishikin",
            "쥬하치로(Juhachiro)",
            "콤퍼트 호텔 기후(COMFORT HOTEL GIFU)",
            "호텔 파크 기후 Hotel Park Gifu",
            "호텔 하모니 테랏세 Hotel Harmonie Terrasse}"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_basic);
        context = this;

        intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        activity = intent.getStringExtra("activity");

        hot_1 = (LinearLayout)findViewById(R.id.hot_1);
        hot_2 = (LinearLayout)findViewById(R.id.hot_2);
        hot_3 = (LinearLayout)findViewById(R.id.hot_3);

        menu_text = (TextView) findViewById(R.id.top_view_text);
        menu_text.setText("STAY");

        search = (ImageButton)findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, search.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });//search

        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity.equals("category")) {
                    intent = new Intent(context, category.class);
                } else if(activity.equals("main")){
                    intent = new Intent(context, MainActivity.class);
                }
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);

                finish();
            }
        });//back


        hotel = findViewById(R.id.hotel_basic);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, hotel_basic.class);
                intent.putExtra("activity", "category");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        hotel_list_name1 = (TextView)findViewById(R.id.hotel_list_name1);
        hotel_list_name1.setText(hotel_name[0]);
        hotel_list_img1 = (ImageView)findViewById(R.id.hotel_list_img1);
        hotel_list_img1.setImageResource(R.drawable.gifu);

        hotel_list_name2 = (TextView)findViewById(R.id.hotel_list_name2);
        hotel_list_name2.setText(hotel_name[1]);
        hotel_list_img2 = (ImageView)findViewById(R.id.hotel_list_img2);
        hotel_list_img2.setImageResource(R.drawable.ishkin);

        hotel_list_name3 = (TextView)findViewById(R.id.hotel_list_name3);
        hotel_list_name3.setText(hotel_name[2]);
        hotel_list_img3 = (ImageView)findViewById(R.id.hotel_list_img3);
        hotel_list_img3.setImageResource(R.drawable.juhachiro);

        hotel_list_name4 = (TextView)findViewById(R.id.hotel_list_name4);
        hotel_list_name4.setText(hotel_name[3]);
        hotel_list_img4 = (ImageView)findViewById(R.id.hotel_list_img4);
        hotel_list_img4.setImageResource(R.drawable.comfort);

        hotel_list_name5 = (TextView)findViewById(R.id.hotel_list_name5);
        hotel_list_name5.setText(hotel_name[4]);
        hotel_list_img5 = (ImageView)findViewById(R.id.hotel_list_img5);
        hotel_list_img5.setImageResource(R.drawable.park);

        hotel_list_name6 = (TextView)findViewById(R.id.hotel_list_name6);
        hotel_list_name6.setText(hotel_name[5]);
        hotel_list_img6 = (ImageView)findViewById(R.id.hotel_list_img6);
        hotel_list_img6.setImageResource(R.drawable.harmonie);


    }//oncreate
}
