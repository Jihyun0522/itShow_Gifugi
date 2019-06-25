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
    LinearLayout layout_food;

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
        layout_food = findViewById(R.id.layout_food_all);

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

    }
}