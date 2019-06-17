package kr.hs.e_mirim.Japan_Gifugi;

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

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        context = this;

        //메인 화면
        drawerLayout = (DrawerLayout) findViewById(R.id.c_drawerLayout);
        //drawer 참조
        drawerView = (View) findViewById(R.id.drawer_menu);

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
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        s_festival_spring = findViewById(R.id.s_festival_spring);
        s_festival_spring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        s_festival_summer = findViewById(R.id.s_festival_summer);
        s_festival_summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        s_festival_fall = findViewById(R.id.s_festival_fall);
        s_festival_fall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        s_festival_winter = findViewById(R.id.s_festival_winter);
        s_festival_winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        s_sightseeing = findViewById(R.id.s_sightseeing);
        s_sightseeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "sightseeing");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        s_stay = findViewById(R.id.s_stay);
        s_stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "stay");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        s_food = findViewById(R.id.s_food);
        s_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, basic_menu.class);
                intent.putExtra("activity", "category");
                intent.putExtra("menu_type", "food");
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

}