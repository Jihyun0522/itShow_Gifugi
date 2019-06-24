package kr.hs.e_mirim.Japan_Gifugi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class post extends AppCompatActivity {
    RelativeLayout h_name_layout, period_layout, time_layout, holiday_layout, price_layout, tel_layout, site_layout;
    TextView explane_content;
    TextView address_content, h_name_content, period_content, time_content, holiday_content, price_content, tel_content, site_content;
    ImageView post_image;

    ImageButton close;
    Context context;

    Intent intent;

    String image;

    String name, email, pw;
    String type, content_name, season, activity, layout;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    ListView reply_list;
    EditText reply_edit;
    Button reply_btn;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        context = this;

        intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        type = intent.getStringExtra("type");
        content_name = intent.getStringExtra("content_name");
        season = intent.getStringExtra("season");
        activity = intent.getStringExtra("activity");
        layout = intent.getStringExtra("layout");

        email = email.replace("_", ".");

        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (layout){
                    case "main":
                        intent = new Intent(context, MainActivity.class);
                        break;

                    case "category":
                        intent = new Intent(context, category.class);
                        break;

                    case "festival_spring" :
                        intent = new Intent(context, festival_basic.class);
                        intent.putExtra("activity", activity);
                        intent.putExtra("season", "spring");
                        break;

                    case "festival_summer" :
                        intent = new Intent(context, festival_basic.class);
                        intent.putExtra("activity", activity);
                        intent.putExtra("season", "summer");
                        break;

                    case "festival_fall" :
                        intent = new Intent(context, festival_basic.class);
                        intent.putExtra("activity", activity);
                        intent.putExtra("season", "fall");
                        break;

                    case "festival_winter" :
                        intent = new Intent(context, festival_basic.class);
                        intent.putExtra("activity", activity);
                        intent.putExtra("season", "winter");
                        break;

                    case "sightseeing":
                        intent = new Intent(context, basic_menu.class);
                        intent.putExtra("activity", activity);
                        intent.putExtra("menu_type", "sightseeing");
                        break;

                    case "stay":
                        intent = new Intent(context, basic_menu.class);
                        intent.putExtra("activity", activity);
                        intent.putExtra("menu_type", "stay");
                        break;

                    case "food":
                        intent = new Intent(context, basic_menu.class);
                        intent.putExtra("activity", activity);
                        intent.putExtra("menu_type", "food");
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

        explane_content = findViewById(R.id.explane);
        post_image = findViewById(R.id.post_image);

        h_name_layout = findViewById(R.id.hotel_name_layout);
        period_layout = findViewById(R.id.period_layout);
        time_layout = findViewById(R.id.time_layout);
        holiday_layout = findViewById(R.id.holiday_layout);
        price_layout = findViewById(R.id.price_layout);
        tel_layout = findViewById(R.id.tel_layout);
        site_layout = findViewById(R.id.site_layout);

        h_name_content = findViewById(R.id.hotel_name_content);
        period_content = findViewById(R.id.period_content);
        time_content = findViewById(R.id.time_content);
        holiday_content = findViewById(R.id.holiday_content);
        price_content = findViewById(R.id.price_content);
        tel_content = findViewById(R.id.tel_content);
        site_content = findViewById(R.id.site_content);
        address_content = findViewById(R.id.address_content);

        switch (type){
            case "festival":
                h_name_layout.setVisibility(View.GONE);
                holiday_layout.setVisibility(View.GONE);
                price_layout.setVisibility(View.GONE);
                site_layout.setVisibility(View.GONE);
                break;

            case "activity":
                h_name_layout.setVisibility(View.GONE);
                period_layout.setVisibility(View.GONE);
                holiday_layout.setVisibility(View.GONE);
                site_layout.setVisibility(View.GONE);
                break;

            case "food":
                h_name_layout.setVisibility(View.GONE);
                period_layout.setVisibility(View.GONE);
                price_layout.setVisibility(View.GONE);
                break;

            case "stay":
                period_layout.setVisibility(View.GONE);
                holiday_layout.setVisibility(View.GONE);
                break;
        }

        if(type.equals("festival")) databaseReference = firebaseDatabase.getReference().child("content").child(type).child(season);
        else databaseReference = firebaseDatabase.getReference().child("content").child(type);

        databaseReference.orderByKey().equalTo(content_name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                switch (type){
                    case "festival":
                        festival festival = dataSnapshot.getValue(festival.class);
                        explane_content.setText(festival.getExplane());
                        address_content.setText(festival.getAddress());
                        explane_content.setText(festival.getExplane());
                        period_content.setText(festival.getSeason());
                        tel_content.setText(festival.getTel());
                        time_content.setText(festival.getTime());
                        image = festival.getImage();

                        Log.i("TAG: value is ",  image);
                        break;

                    case "activity" :
                        experience experience = dataSnapshot.getValue(experience.class);
                        explane_content.setText(experience.getExplane());
                        address_content.setText(experience.getAddress());
                        time_content.setText(experience.getTime());
                        price_content.setText(experience.getPrice());
                        tel_content.setText(experience.getTel());
                        image = experience.getImage();
                        break;

                    case "food":
                        food food = dataSnapshot.getValue(food.class);
                        explane_content.setText(food.getExplane());
                        address_content.setText(food.getAddress());
                        holiday_content.setText(food.getClosed());
                        site_content.setText(food.getSite());
                        tel_content.setText(food.getTel());
                        time_content.setText(food.getTime());
                        image = food.getImage();
                        break;

                    case "stay":
                        hotel hotel = dataSnapshot.getValue(hotel.class);
                        explane_content.setText(hotel.getExplain());
                        address_content.setText(hotel.getAddress());
                        site_content.setText(hotel.getLink());
                        h_name_content.setText(hotel.getName());
                        price_content.setText(hotel.getPrice());
                        tel_content.setText(hotel.getTel());
                        image = hotel.getImage();
                        break;
                }

                Glide.with(context)
                        .load(image)
                        .override(340,300).centerCrop()
                        .into(post_image);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reply_list = findViewById(R.id.reply_list);
        reply_edit = findViewById(R.id.reply_edit);
        reply_btn = findViewById(R.id.reply_btn);

        initDatabase();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        reply_list.setAdapter(adapter);

        if(type.equals("festival")) mReference = mDatabase.getReference().child("content").child(type).child(season);
        else mReference = mDatabase.getReference().child("content").child(type);

        mReference.child(content_name).child("reply").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    String msg2 = snapshot.getValue().toString();
                    Array.add(msg2);
                    adapter.add(msg2);
                }
                adapter.notifyDataSetChanged();
                reply_list.setSelection(adapter.getCount() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(content_name).child("reply").push().setValue(name + " - " + reply_edit.getText().toString());
                reply_edit.setText("");
            }
        });
    }

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
    }
}
