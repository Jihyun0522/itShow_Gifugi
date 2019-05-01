package kr.hs.mirim.japan_gifu;

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

    ImageButton search;

    TextView menu_text;
    RelativeLayout back;

    String menu;

    Switch changeLayout;
    LinearLayout card_view;
    LinearLayout list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_menu);

        Intent intent = getIntent();
        menu = intent.getStringExtra("menu_type");

        menu_text = findViewById(R.id.top_view_text);

        switch (menu){
            case "sightseeing":
                menu_text.setText("SIGHTSEEING");
                break;

            case "stay":
                menu_text.setText("STAY");
                break;

            case "food":
                menu_text.setText("FOOD");
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
                Intent intent = getIntent();
                String activity = intent.getExtras().getString("activity");
                if (activity.equals("category")) {
                    intent = new Intent(context, category.class);
                } else if(activity.equals("main")){
                    intent = new Intent(context, MainActivity.class);
                }
                startActivity(intent);
                overridePendingTransition(0, 0);

                finish();
            }
        });

        changeLayout = (Switch)findViewById(R.id.festival_switch);
        card_view = findViewById(R.id.card_view);
        list_view = findViewById(R.id.list_view);

        //화면 전환 성공
        changeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckState();
            }
        });
    }

    private void CheckState(){
        if (changeLayout.isChecked()){
            card_view.setVisibility(card_view.VISIBLE);
        } else {
            card_view.setVisibility(card_view.GONE);
        }
    }

}
