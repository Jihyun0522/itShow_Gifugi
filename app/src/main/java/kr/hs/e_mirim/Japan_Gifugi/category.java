package kr.hs.e_mirim.Japan_Gifugi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class category extends AppCompatActivity {
    Context context;
    Button home;
    Button category;

    ImageButton search;
    LinearLayout festival;
    LinearLayout sightseeing;
    LinearLayout stay;
    LinearLayout food;

    DrawerLayout drawerLayout;
    View drawerView;

    ImageButton buttonOpenDrawer;
    ImageButton buttonCloseDrawer;

    LinearLayout s_festival;
    TextView s_festival_spring;
    TextView s_festival_summer;
    TextView s_festival_fall;
    TextView s_festival_winter;
    LinearLayout s_sightseeing;
    LinearLayout s_stay;
    LinearLayout s_food;
    TextView s_name_user;
    TextView s_id_user;

    Intent intent;

    String name, email, pw;

    //define firebase object
    FirebaseAuth firebaseAuth;

    //RealTime Database
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat;

    //오늘의 달
    private int getMonth() {
        int m = 0;
        mFormat = new SimpleDateFormat("MM");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        m = Integer.parseInt(mFormat.format(mDate));

        return m;
    }
    String season;

    LinearLayout card_view1, card_view2, card_view3, card_view4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        context = this;

        intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");

        email = email.replace("_", ".");

        s_name_user = findViewById(R.id.s_name_user);
        s_name_user.setText(name);
        s_id_user = findViewById(R.id.s_id_user);
        s_id_user.setText(email);

        //메인 화면
        drawerLayout = (DrawerLayout) findViewById(R.id.c_drawerLayout);
        //drawer 참조
        drawerView = (View) findViewById(R.id.drawer_menu);

        switch (getMonth()){
            case 3:
            case 4:
            case 5:
                season = "spring";
                break;

            case 6:
            case 7:
            case 8:
                season = "summer";
                break;

            case 9:
            case 10:
            case 11:
                season = "fall";
                break;

            case 12:
            case 1:
            case 2:
                season = "winter";
                break;
        }

        // 드로어 여는 버튼 리스너
        buttonOpenDrawer = (ImageButton) findViewById(R.id.opendrawer);
        buttonOpenDrawer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        // 드로어 닫는 버튼 리스너
        buttonCloseDrawer = (ImageButton) findViewById(R.id.closedrawer);
        buttonCloseDrawer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                drawerLayout.closeDrawers();
            }
        });

        category = findViewById(R.id.btn_category);
        category.setTextColor(ContextCompat.getColor(this, R.color.main));

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, MainActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);

                finish();
            }
        });

        search = (ImageButton)findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, search.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        festival = findViewById(R.id.c_festival);
        festival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("season", season);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        sightseeing = findViewById(R.id.c_sightseeing);
        sightseeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "sightseeing");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        stay = findViewById(R.id.c_stay);
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "stay");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        food = findViewById(R.id.c_food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "food");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //drawer menu
        s_festival = findViewById(R.id.s_festival);
        s_festival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("season", season);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        s_festival_spring = findViewById(R.id.s_festival_spring);
        s_festival_spring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("season", "spring");
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        s_festival_summer = findViewById(R.id.s_festival_summer);
        s_festival_summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("season", "summer");
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        s_festival_fall = findViewById(R.id.s_festival_fall);
        s_festival_fall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("season", "fall");
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        s_festival_winter = findViewById(R.id.s_festival_winter);
        s_festival_winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("season", "winter");
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        s_sightseeing = findViewById(R.id.s_sightseeing);
        s_sightseeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "sightseeing");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        s_stay = findViewById(R.id.s_stay);
        s_stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "stay");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        s_food = findViewById(R.id.s_food);
        s_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "food");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        card_view1 = findViewById(R.id.category_card_1);
        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", "category");
                intent.putExtra("layout", "category");
                intent.putExtra("season", "summer");
                intent.putExtra("content_name", "나가라강 불꽃놀이 대회");
                intent.putExtra("type", "festival");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        card_view2 = findViewById(R.id.category_card_2);
        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", "category");
                intent.putExtra("layout", "category");
                intent.putExtra("season", "summer");
                intent.putExtra("content_name", "게로 온천");
                intent.putExtra("type", "experience");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        card_view3 = findViewById(R.id.category_card_3);
        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", "category");
                intent.putExtra("layout", "category");
                intent.putExtra("season", "summer");
                intent.putExtra("content_name", "가와라야");
                intent.putExtra("type", "food");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        card_view4 = findViewById(R.id.category_card_4);
        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", "category");
                intent.putExtra("layout", "category");
                intent.putExtra("season", "summer");
                intent.putExtra("content_name", "식품샘플 제작");
                intent.putExtra("type", "experience");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }


}