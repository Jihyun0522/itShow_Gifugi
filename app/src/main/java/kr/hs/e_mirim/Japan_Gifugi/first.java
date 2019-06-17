package kr.hs.e_mirim.Japan_Gifugi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class first extends AppCompatActivity {
    Button login;
    Button signup;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        context = this;
        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);

        login.setOnClickListener(btnOnClick);
        signup.setOnClickListener(btnOnClick);

    }

    Button.OnClickListener btnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;

            switch (view.getId()){
                case R.id.login:
                    intent = new Intent(context, login.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                    finish();
                    break;

                case R.id.signup:
                    intent = new Intent(context, signup.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                    finish();
                    break;
            }
        }
    };

}
