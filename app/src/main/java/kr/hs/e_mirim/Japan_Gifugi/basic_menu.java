package kr.hs.e_mirim.Japan_Gifugi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

public class basic_menu extends AppCompatActivity {
    Context context;
    Intent intent;

    ImageButton search;

    TextView menu_text;
    RelativeLayout back;

    String menu, name, email, pw, activity;

    Switch changeLayout;
    LinearLayout card_view;
    LinearLayout list_view;

    LinearLayout layout_act;
    LinearLayout layout_act1, layout_act2;

    LinearLayout layout_food;
    LinearLayout layout_food1, layout_food2, layout_food3, layout_food4, layout_food5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_menu);
        context = this;

        intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        activity = intent.getStringExtra("activity");
        menu = intent.getStringExtra("menu_type");

        menu_text = findViewById(R.id.top_view_text);

        layout_act = findViewById(R.id.layout_act_all);
        layout_act1 = findViewById(R.id.layout_act1);
        layout_act2 = findViewById(R.id.layout_act2);

        layout_food = findViewById(R.id.layout_food_all);
        layout_food1 = findViewById(R.id.layout_food1);
        layout_food2 = findViewById(R.id.layout_food2);
        layout_food3 = findViewById(R.id.layout_food3);
        layout_food4 = findViewById(R.id.layout_food4);
        layout_food5 = findViewById(R.id.layout_food5);

        switch (menu){
            case "sightseeing":
                menu_text.setText("EXPERIENCE");
                layout_food.setVisibility(View.GONE);
                break;

            case "food":
                menu_text.setText("FOOD");
                layout_act.setVisibility(View.GONE);
                break;
        }

        search = (ImageButton)findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, search.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        back = (RelativeLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (activity){
                    case "main" :
                        intent = new Intent(context, MainActivity.class);
                        break;

                    case "category" :
                        intent = new Intent(context, category.class);
                        break;
                }

                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                startActivity(intent);
                overridePendingTransition(0, 0);

                finish();
            }
        });

        list_view = findViewById(R.id.list_view);

        layout_act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "sightseeing");
                intent.putExtra("season", "--");
                intent.putExtra("content_name", "게로 온천");
                intent.putExtra("type", "activity");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        layout_act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "sightseeing");
                intent.putExtra("season", "--");
                intent.putExtra("content_name", "식품샘플 제작");
                intent.putExtra("type", "activity");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        layout_food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "food");
                intent.putExtra("season", "--");
                intent.putExtra("content_name", "가와라야");
                intent.putExtra("type", "food");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        layout_food2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "food");
                intent.putExtra("season", "--");
                intent.putExtra("content_name", "반쇼칸");
                intent.putExtra("type", "food");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        layout_food3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "food");
                intent.putExtra("season", "--");
                intent.putExtra("content_name", "일본 요리 히라이");
                intent.putExtra("type", "food");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        layout_food4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "food");
                intent.putExtra("season", "--");
                intent.putExtra("content_name", "코라쿠소");
                intent.putExtra("type", "food");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        layout_food5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "food");
                intent.putExtra("season", "--");
                intent.putExtra("content_name", "히다소");
                intent.putExtra("type", "food");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

    }
}