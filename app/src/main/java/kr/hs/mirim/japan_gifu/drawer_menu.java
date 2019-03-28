package kr.hs.mirim.japan_gifu;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class drawer_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_menu);

        // 전체화면인 DrawerLayout 객체 참조
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Drawer 화면(뷰) 객체 참조
        final View drawerView = (View) findViewById(R.id.drawer_menu);

        // 드로어 화면을 열고 닫을 버튼 객체 참조
        ImageButton btnOpenDrawer = (ImageButton) findViewById(R.id.btn_OpenDrawer);
        ImageButton btnCloseDrawer = (ImageButton) findViewById(R.id.btn_CloseDrawer);

        // 드로어 여는 버튼 리스너
        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        // 드로어 닫는 버튼 리스너
        btnCloseDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
            }
        });
    }

}
