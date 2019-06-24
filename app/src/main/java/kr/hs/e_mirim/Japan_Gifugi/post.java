package kr.hs.e_mirim.Japan_Gifugi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class post extends AppCompatActivity {
    RelativeLayout h_name_layout, period_layout, time_layout, holiday_layout, price_layout, tel_layout, site_layout;
    TextView explane_content;
    TextView address_content, h_name_content, period_content, time_content, holiday_content, price_content, tel_content, site_content;
    ImageView post_image;

    ImageButton close;
    Context context;

    Intent intent;

    String name, email, pw;
    String type, content_name, season;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        type = intent.getStringExtra("type");
        content_name = intent.getStringExtra("content_name");
        season = intent.getStringExtra("season");

        email = email.replace("_", ".");

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

            case "experience":
                h_name_layout.setVisibility(View.GONE);
                period_layout.setVisibility(View.GONE);
                holiday_layout.setVisibility(View.GONE);
                site_layout.setVisibility(View.GONE);
                break;

            case "food":
                h_name_layout.setVisibility(View.GONE);
                period_layout.setVisibility(View.GONE);
                break;

            case "stay":
                h_name_layout.setVisibility(View.GONE);
                period_layout.setVisibility(View.GONE);
                holiday_layout.setVisibility(View.GONE);
                break;
        }

        databaseReference = firebaseDatabase.getReference().child(type + "/" + content_name);
        if(type.equals("festival")) databaseReference.child(season);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                switch ("type"){
                    case "festival":
                        festival festival = dataSnapshot.getValue(festival.class);
                        explane_content.setText(festival.getExplane());
                        address_content.setText(festival.getAddress());
                        explane_content.setText(festival.getExplane());
                        period_content.setText(festival.getSeason());
                        tel_content.setText(festival.getTel());
                        time_content.setText(festival.getTime());
                        break;
                }
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
    }
}
