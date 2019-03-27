package kr.hs.mirim.japan_gifu;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat;
    TextView Today;
    TextView season;

    Button category;
    Context context;

    ImageButton search;

    private String TAG = "HOME";

    private Boolean isMenuShow = false;
    private Boolean isExitFlag = false;

    private String getTime() {
        mFormat = new SimpleDateFormat("yyyy.MM.dd");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    private int getMonth() {
        int m = 0;
        mFormat = new SimpleDateFormat("MM");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        m = Integer.parseInt(mFormat.format(mDate));

        return m;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 전체화면인 DrawerLayout 객체 참조
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Drawer 화면(뷰) 객체 참조
        final View drawerView = (View) findViewById(R.id.drawer);

        // 드로어 화면을 열고 닫을 버튼 객체 참조
        ImageButton btnOpenDrawer = (ImageButton) findViewById(R.id.btn_OpenDrawer);
        ImageButton btnCloseDrawer = (ImageButton) findViewById(R.id.btn_CloseDrawer);

        // 드로어 여는 버튼 리스너
        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        // 드로어 닫는 버튼 리스너
        btnCloseDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
            }
        });


        Today = (TextView) findViewById(R.id.today);
        Today.setText(getTime());

        season = (TextView) findViewById(R.id.season);
        category = (Button) findViewById(R.id.btn_category);
        context = this;

        switch (getMonth()) {
            case 3:
            case 4:
            case 5:
                season.setText("春");
                break;

            case 6:
            case 7:
            case 8:
                season.setText("夏");
                break;

            case 9:
            case 10:
            case 11:
                season.setText("秋");
                break;

            case 12:
            case 1:
            case 2:
                season.setText("冬");
                break;
        }

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, category.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

                finish();
            }
        });

        search = (ImageButton)findViewById(R.id.btn_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, search.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

}