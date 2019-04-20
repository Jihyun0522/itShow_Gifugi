package kr.hs.mirim.japan_gifu;

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

    DrawerLayout drawerLayout;
    View drawerView;

    ImageButton buttonOpenDrawer;
    ImageButton buttonCloseDrawer;

    LinearLayout s_festival;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        context = this;

        final String intent_Season = intent.getStringExtra("season");

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
                intent.putExtra("season", intent_Season);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        s_festival = findViewById(R.id.s_festival);
        s_festival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, festival_basic.class);
                intent.putExtra("activity", "category");
                intent.putExtra("season", intent_Season);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

}