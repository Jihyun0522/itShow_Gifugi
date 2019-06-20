
package kr.hs.e_mirim.Japan_Gifugi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class login extends AppCompatActivity {
    Button login;
    Context context;

    EditText E_email;
    EditText E_password;
    TextView textView;

    String email;
    String password;
    String name ="";

    //define firebase object
    FirebaseAuth firebaseAuth;

    //RealTime Database
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initializig firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        context = this;
        login = (Button) findViewById(R.id.login_home);

        E_email = (EditText) findViewById(R.id.login_email);
        E_password = (EditText) findViewById(R.id.login_password);
        textView = (TextView) findViewById(R.id.login_signUp);
        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, signup.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void userLogin() {
        email = E_email.getText().toString().trim();
        password = E_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("로그인중입니다. 잠시 기다려 주세요...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    email = email.replace(".", "_");
                    databaseReference = firebaseDatabase.getReference().child("user/" + email + "/name");
                    databaseReference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                name = childSnapshot.getValue(String.class);
                                Log.i("TAG: value is ",  name);
                            }

                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("email", email);
                            intent.putExtra("name", name);
                            intent.putExtra("pw", password);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.w("TAG: ", "Failed to read value", databaseError.toException());
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }//userLogin
}
