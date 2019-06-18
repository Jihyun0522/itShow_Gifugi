package kr.hs.e_mirim.Japan_Gifugi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    Button signin;
    Context context;

    EditText s_name;
    EditText s_email;
    EditText s_password;

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    //RealTime Database
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    ProgressDialog progressDialog;

    String email, name, password;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();

        context = this;
        signin = (Button) findViewById(R.id.signin);

        s_name = (EditText) findViewById(R.id.signup_name);
        s_email = (EditText) findViewById(R.id.signup_email);
        s_password = (EditText) findViewById(R.id.signup_password);

        progressDialog = new ProgressDialog(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    //Firebse creating a new user
    private void registerUser() {
        //사용자가 입력하는 email, password를 가져온다.
        name = s_name.getText().toString();
        email = s_email.getText().toString();
        password = s_password.getText().toString();

        //email과 password가 비었는지 아닌지를 체크 한다.
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name을 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }

        //email과 password가 제대로 입력되어 있다면 계속 진행된다.
        progressDialog.setMessage("등록중입니다. 기다려 주세요...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    email = email.replace(".", "_");

                    databaseReference.child("user").child(email).child("name").push().setValue(name);
                    databaseReference.child("user").child(email).child("pw").push().setValue(password);

                    Toast.makeText(signup.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, login.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                } else {
                    //에러발생시
                    Toast.makeText(signup.this, "에러유형\n - 이미 등록된 이메일  \n -암호 최소 6자리 이상 \n - 서버에러", Toast.LENGTH_SHORT).show();
                    Toast.makeText(signup.this, "등록 에러!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

    }

   /*Button.OnClickListener gotoHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            email = s_email.getText().toString();
            name = s_name.getText().toString();
            password = s_password.getText().toString();

            if (isValidEmail() && isValidPasswd() && isValidName()) {
                createUser(email, password);
            }

            if (check == 1) {
                Toast.makeText(signup.this, "회원가입 성공!!", Toast.LENGTH_SHORT).show();

                //databaseReference.child("email").push().setValue(email);
                databaseReference.child("user").child(email).child("name").push().setValue(name);
                databaseReference.child("user").child(email).child("pw").push().setValue(password);

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("name", name);
                intent.putExtra("pw", name);
                startActivity(intent);
                overridePendingTransition(0, 0);

                finish();
            } else {
                Toast.makeText(signup.this, "회원가입 실패..", Toast.LENGTH_SHORT).show();
            }
        }
    };*/
}
