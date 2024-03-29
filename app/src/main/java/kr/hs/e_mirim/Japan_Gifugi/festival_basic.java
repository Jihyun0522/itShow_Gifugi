package kr.hs.e_mirim.Japan_Gifugi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class festival_basic extends AppCompatActivity {
    Context context;
    Intent intent;

    ImageButton search;

    TextView menu_text;
    RelativeLayout back;

    ImageButton spring;
    ImageButton summer;
    ImageButton fall;
    ImageButton winter;

    ImageView festival_list_img_1, festival_list_img_2, festival_list_img_3;
    TextView festival_list_name_1, festival_list_name_2, festival_list_name_3;
    TextView festival_list_season_1, festival_list_season_2, festival_list_season_3;

    RelativeLayout fes_1, fes_2, fes_3;

    LinearLayout festival;

/*
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<>();
*/
    String name, email, pw, activity, season;

    String[] festival_item_spring = {"데지카라 불축제", "도산 축제", "봄의 다카야마축제"};
    String[] festival_item_summer = {"나가라강 불꽃놀이 대회"};
    String[] festival_item_fall = {"가을의 다카야마축제", "기후 노부나가 축제"};
    String[] festival_item_winter = {"이케노우에 미소기 축제"};
    //listAdapter adapter;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_festival_basic);
            context = this;

            intent = getIntent();
            name = intent.getStringExtra("name");
            email = intent.getStringExtra("email");
            pw = intent.getStringExtra("pw");
            activity = intent.getStringExtra("activity");
            season = intent.getStringExtra("season");

        fes_1 = (RelativeLayout)findViewById(R.id.fes_1);
        fes_2 = (RelativeLayout)findViewById(R.id.fes_2);
        fes_3 = (RelativeLayout)findViewById(R.id.fes_3);

        menu_text = (TextView) findViewById(R.id.top_view_text);
        menu_text.setText("FESTIVAL");

        search = (ImageButton)findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, search.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });//search

        festival = findViewById(R.id.festival_basic);
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

        spring = (ImageButton)findViewById(R.id.f_spring);
        summer = (ImageButton)findViewById(R.id.f_summer);
        fall = (ImageButton)findViewById(R.id.f_fall);
        winter = (ImageButton)findViewById(R.id.f_winter);

        spring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        fall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        switch (season) {
            case "spring":
                spring.setImageResource(R.drawable.on_spring);
                summer.setImageResource(R.drawable.un_summer);
                fall.setImageResource(R.drawable.un_fall);
                winter.setImageResource(R.drawable.un_winter);
                break;

            case "summer":
                spring.setImageResource(R.drawable.un_spring);
                summer.setImageResource(R.drawable.on_summer);
                fall.setImageResource(R.drawable.un_fall);
                winter.setImageResource(R.drawable.un_winter);
               break;

            case "fall":
                spring.setImageResource(R.drawable.un_spring);
                summer.setImageResource(R.drawable.un_summer);
                fall.setImageResource(R.drawable.on_fall);
                winter.setImageResource(R.drawable.un_winter);
                break;

            case "winter":
                spring.setImageResource(R.drawable.un_spring);
                summer.setImageResource(R.drawable.un_summer);
                fall.setImageResource(R.drawable.un_fall);
                winter.setImageResource(R.drawable.on_winter);
                break;
        }//btn_switch

        switch (season) {
            case "spring":
                festival_list_name_1 = (TextView)findViewById(R.id.festival_list_name_1);
                festival_list_name_1.setText(festival_item_spring[0]);
                festival_list_name_2 = (TextView)findViewById(R.id.festival_list_name_2);
                festival_list_name_2.setText(festival_item_spring[1]);
                festival_list_name_3 = (TextView)findViewById(R.id.festival_list_name_3);
                festival_list_name_3.setText(festival_item_spring[2]);

                festival_list_img_1 = (ImageView) findViewById(R.id.festival_list_img_1);
                festival_list_img_1.setImageResource(R.drawable.dejikara);
                festival_list_img_2 = (ImageView)findViewById(R.id.festival_list_img_2);
                festival_list_img_2.setImageResource(R.drawable.dosan);
                festival_list_img_3 = (ImageView)findViewById(R.id.festival_list_img_3);
                festival_list_img_3.setImageResource(R.drawable.dakayama_spring);

                festival_list_season_1 = (TextView)findViewById(R.id.festival_list_season_1);
                festival_list_season_1.setText("春");
                festival_list_season_2 = (TextView)findViewById(R.id.festival_list_season_2);
                festival_list_season_2.setText("春");
                festival_list_season_3 = (TextView)findViewById(R.id.festival_list_season_3);
                festival_list_season_3.setText("春");
                break;

            case "summer":
                festival_list_name_1 = (TextView)findViewById(R.id.festival_list_name_1);
                festival_list_name_1.setText(festival_item_summer[0]);

                festival_list_img_1 = (ImageView) findViewById(R.id.festival_list_img_1);
                festival_list_img_1.setImageResource(R.drawable.hanabi);

                festival_list_season_1 = (TextView)findViewById(R.id.festival_list_season_1);
                festival_list_season_1.setText("夏");

                fes_2.setVisibility(View.GONE);
                fes_3.setVisibility(View.GONE);
                break;

            case "fall":
                festival_list_name_1 = (TextView)findViewById(R.id.festival_list_name_1);
                festival_list_name_1.setText(festival_item_fall[0]);
                festival_list_name_2 = (TextView)findViewById(R.id.festival_list_name_2);
                festival_list_name_2.setText(festival_item_fall[1]);

                festival_list_img_1 = (ImageView) findViewById(R.id.festival_list_img_1);
                festival_list_img_1.setImageResource(R.drawable.dakayama_fall);
                festival_list_img_2 = (ImageView)findViewById(R.id.festival_list_img_2);
                festival_list_img_2.setImageResource(R.drawable.nobunaga);

                festival_list_season_1 = (TextView)findViewById(R.id.festival_list_season_1);
                festival_list_season_1.setText("秋");
                festival_list_season_2 = (TextView)findViewById(R.id.festival_list_season_2);
                festival_list_season_2.setText("秋");

                fes_3.setVisibility(View.GONE);
                break;

            case "winter":
                festival_list_name_1 = (TextView)findViewById(R.id.festival_list_name_1);
                festival_list_name_1.setText(festival_item_winter[0]);

                festival_list_img_1 = (ImageView) findViewById(R.id.festival_list_img_1);
                festival_list_img_1.setImageResource(R.drawable.ikenoue);

                festival_list_season_1 = (TextView)findViewById(R.id.festival_list_season_1);
                festival_list_season_1.setText("冬");

                fes_2.setVisibility(View.GONE);
                fes_3.setVisibility(View.GONE);
                break;
        }//switch

        fes_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "festival_" + season);
                intent.putExtra("season", season);
                intent.putExtra("content_name", festival_list_name_1.getText().toString());
                intent.putExtra("type", "festival");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        fes_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "festival_" + season);
                intent.putExtra("season", season);
                intent.putExtra("content_name", festival_list_name_2.getText().toString());
                intent.putExtra("type", "festival");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        fes_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, post.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pw", pw);
                intent.putExtra("activity", activity);
                intent.putExtra("layout", "festival_" + season);
                intent.putExtra("season", season);
                intent.putExtra("content_name", festival_list_name_3.getText().toString());
                intent.putExtra("type", "festival");

                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        /*
        initDatabase();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter);

        mReference = mDatabase.getReference("content/festival"); // 변경값을 확인할 child 이름
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String list2 = messageData.getValue().toString();
                    Array.add(list2);
                    adapter.add(list2);
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount()-1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
    }//oncreate
/*
    class listAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return festival_item.length;
        }

        @Override
        public Object getItem(int position){
            return festival_item[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(getApplicationContext());
            view.setText(festival_item[position]);
            return view;
        }
    }//listadapter
    */
/*
    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }//initdatabase

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }
    */
}
