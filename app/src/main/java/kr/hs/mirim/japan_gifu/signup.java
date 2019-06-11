package kr.hs.mirim.japan_gifu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    Button signin;
    Context context;

    EditText s_name;
    EditText s_email;
    EditText s_password;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        context = this;
        signin = (Button) findViewById(R.id.signin);

        s_name = (EditText)findViewById(R.id.signup_name);
        s_email = (EditText)findViewById(R.id.signup_email);
        s_password = (EditText)findViewById(R.id.signup_password);

        signin.setOnClickListener(gotoHome);
    }

    Button.OnClickListener gotoHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String room = s_email.getText().toString();
            databaseReference.child("id").push().setValue(room);
            databaseReference.child("user").child(room).child("name").push().setValue(s_name.getText().toString());
            databaseReference.child("user").child(room).child("passwd").push().setValue(s_password.getText().toString());

            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.putExtra("id",room);
            intent.putExtra("pwd", s_password.getText().toString());
            startActivity(intent);
            overridePendingTransition(0, 0);

            finish();
        }
    };
}
