package kr.hs.mirim.japan_gifu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {
    Button signin;
    Context context;

    EditText s_name;
    EditText s_email;
    EditText s_password;

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
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);

            finish();
        }
    };
}
