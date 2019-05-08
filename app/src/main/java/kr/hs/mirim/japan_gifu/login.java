
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

    Button.OnClickListener gotoHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

            finish();
        }
    };

}
