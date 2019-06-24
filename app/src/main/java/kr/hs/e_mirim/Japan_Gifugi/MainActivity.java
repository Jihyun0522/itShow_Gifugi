package kr.hs.e_mirim.Japan_Gifugi;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MainActivity extends AppCompatActivity {
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat;
    TextView Today;
    TextView season;

    Button category;
    Context context;
    Button home;
    ImageButton search;

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

    //card view
    ImageView imageCard;
    TextView sub_text;
    TextView main_text;

    String sub, main;

    //RealTime Database
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://japangifugi-a8d93.appspot.com");

    //사진 이름. activity / festival / food / hotel 순
    String image_name[][] = {
            {"게로온천", "식품샘플"},
            {"가을 다카야마", "나가라강", "노부나가", "데지카라", "도산", "봄 다카야마", "이케노우에"},
            {"가와라야", "반쇼칸", "일본요리 히라이", "코라쿠소", "히다소2"},
            {"기후 그랜드 호텔1", "이시킨", "주하치로", "컴포트", "파크", "하모니"}
    };

    String name, email, pw, season_text;

    //날짜 표시
    private String getTime() {
        mFormat = new SimpleDateFormat("yyyy.MM.dd");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    //오늘의 달
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
        drawerLayout = (DrawerLayout) findViewById(R.id.m_drawerLayout);
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

        Today = (TextView) findViewById(R.id.today);
        Today.setText(getTime());

        home = (Button) findViewById(R.id.btn_home);
        season = (TextView) findViewById(R.id.season);
        category = (Button) findViewById(R.id.btn_category);
        search = (ImageButton) findViewById(R.id.btn_search);
        context = this;

        home.setTextColor(ContextCompat.getColor(this, R.color.main));

        //봄, 여름, 가을, 겨울에 따라 한자 출력
        switch (getMonth()) {
            case 3:
            case 4:
            case 5:
                season.setText("春");
                season_text = "spring";
                break;

            case 6:
            case 7:
            case 8:
                season.setText("夏");
                season_text = "summer";
                break;

            case 9:
            case 10:
            case 11:
                season.setText("秋");
                season_text = "fall";
                break;

            case 12:
            case 1:
            case 2:
                season.setText("冬");
                season_text = "winter";
                break;
        }

        //main image 띄우기


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, category.class);
                intent.putExtra("month", getMonth());
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);

                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, search.class);
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
                intent.putExtra("activity", "main");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("season", season_text);
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
                intent.putExtra("activity", "main");
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
                intent.putExtra("activity", "main");
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
                intent.putExtra("activity", "main");
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
                intent.putExtra("activity", "main");
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
                intent.putExtra("activity", "main");
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
                intent.putExtra("activity", "main");
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
                intent.putExtra("activity", "main");
                intent.putExtra("menu_type", "food");
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        //card view
        imageCard = findViewById(R.id.main_card);
        sub_text = findViewById(R.id.sub_text);
        main_text = findViewById(R.id.main_text);

        reference.child("festival").child("나가라강.jpg");

        sub_text.setText("아름다운 불꽃을 바라보며");
        main_text.setText("나가라강\n불꽃놀이 대회");

        int radius = 20; // corner radius, higher value = more rounded
        int margin = 10; // crop margin, set to 0 for corners with no crop
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/japangifugi-a8d93.appspot.com/o/festival%2F%EB%82%98%EA%B0%80%EB%9D%BC%EA%B0%95.jpg?alt=media&token=010f1dec-609f-485f-8b7e-28139b07a0ca")
                .override(340,440).centerCrop()
                /*.transform(new RoundedCornersTransformation(radius, margin))*/
                .into(imageCard);

        imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", "main");
                intent.putExtra("layout", "main");
                intent.putExtra("season", "summer");
                intent.putExtra("content_name", "나가라강 불꽃놀이 대회");
                intent.putExtra("type", "festival");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }
}