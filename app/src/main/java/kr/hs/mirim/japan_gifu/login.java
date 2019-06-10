
package kr.hs.mirim.japan_gifu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    Button login;
    Context context;

    EditText E_email;
    EditText E_password;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        login = (Button)findViewById(R.id.login_home);

        E_email = (EditText)findViewById(R.id.login_email);
        E_password = (EditText)findViewById(R.id.login_password);

        login.setOnClickListener(gotoHome);
    }

    /*
    class check{
            int idcheck=0,passwordcheck=0;
        }
            check c = new check();

            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
                DatabaseReference mdatabaseRef = mdatabase.getReference("id");

                mdatabaseRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot messeage : dataSnapshot.getChildren()){
                            if(messeage.getValue().toString().equals(loginid.getText().toString())) {
                                c.idcheck=1;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                FirebaseDatabase mdatabasepwd = FirebaseDatabase.getInstance();
                DatabaseReference mdatabaseRefpwd = mdatabasepwd.getReference("user");

                mdatabaseRefpwd.child(loginid.getText().toString()).child("passwd").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messeage : dataSnapshot.getChildren()) {
                            if (messeage.getValue().toString().equals(loginpwd.getText().toString())) {
                                if (c.idcheck == 1) {
                                    Toast.makeText(login.this, "로그인 완료!", Toast.LENGTH_SHORT).show();
                                    c.passwordcheck=1;
                                    Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                                    intent.putExtra("id",loginid.getText().toString());
                                    intent.putExtra("pwd",loginpwd.getText().toString());
                                    startActivity(intent);
                                }
                            }
                        }
                        if(!(c.idcheck==1&&c.passwordcheck==1)){
                            Toast.makeText(login.this, "아이디나 비밀번호가 잘못되었습니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                            loginid.setText("");
                            loginpwd.setText("");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
     */

    Button.OnClickListener gotoHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

            finish();
        }
    };

}
