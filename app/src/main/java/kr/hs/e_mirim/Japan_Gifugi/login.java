
package kr.hs.e_mirim.Japan_Gifugi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    Button login;
    Context context;

    EditText E_email;
    EditText E_password;
    TextView textView;
    ProgressBar progressBar;

    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        login = (Button)findViewById(R.id.login_home);

        E_email = (EditText)findViewById(R.id.login_email);
        E_password = (EditText)findViewById(R.id.login_password);
        textView = (TextView) findViewById(R.id.login_signUp);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        login.setOnClickListener(new View.OnClickListener() {

            class check{
                int id_check = 0;
                int password_check=0;
            }
            check c = new check();

            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
                final DatabaseReference mdatabaseRef = mdatabase.getReference("email");

                mdatabaseRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot messeage : dataSnapshot.getChildren()){
                            if(messeage.getValue().toString().equals(E_email.getText().toString())) {
                                c.id_check=1;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                FirebaseDatabase mdatabasepwd = FirebaseDatabase.getInstance();
                DatabaseReference mdatabaseRefUser = mdatabasepwd.getReference("user");
                name = mdatabaseRefUser.child(E_email.getText().toString()).child("name").toString();

                mdatabaseRefUser.child(E_email.getText().toString()).child("pw").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messeage : dataSnapshot.getChildren()) {
                            if (messeage.getValue().toString().equals(E_email.getText().toString())) {
                                if (c.id_check == 1) {
                                    Toast.makeText(login.this, "로그인 완료!", Toast.LENGTH_SHORT).show();
                                    c.password_check = 1;


                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("email", E_email.getText().toString());
                                    intent.putExtra("name", name);
                                    intent.putExtra("pw", E_password.getText().toString());
                                    startActivity(intent);
                                }
                            }
                        }
                        if(!(c.id_check == 1 && c.password_check == 1)){
                            Toast.makeText(login.this, "아이디나 비밀번호가 잘못되었습니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                            E_email.setText("");
                            E_password.setText("");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, signup.class);
                startActivity(intent);
            }
        });
    }

}
