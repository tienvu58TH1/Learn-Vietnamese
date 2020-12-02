package com.example.projectda.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.example.projectda.R;
import com.example.projectda.fragments.DetailQuestionManagementFragment;

import com.example.projectda.fragments.ManagerFragment;
import com.example.projectda.fragments.MenuFragment;
import com.example.projectda.utils.LocaleManager;
import com.google.android.material.navigation.NavigationView;

public class AdminManagerAcitivity extends AppCompatActivity {

    public DrawerLayout drawerLayoutAdminMain;
    public static Toolbar tbManager;
    private NavigationView nvMain;
    public static ImageView ivUser;
    public static TextView tvUser;
    public static Bitmap bitmapUser;
    private static boolean isLoadLocale=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager_acitivity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (!isLoadLocale){
            isLoadLocale=true;
            LocaleManager.loadLocale(this);
            recreate();
        }
        actionBar();
        selectMenu();
        setupView();
    }


    private void setupView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main,new ManagerFragment()).commit();
    }

    private void actionBar() {
        tbManager=findViewById(R.id.tbManager);
        drawerLayoutAdminMain=findViewById(R.id.drawerLayoutAdminMain);
        setSupportActionBar(tbManager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbManager.setNavigationIcon(R.drawable.ic_menu_drawer);
        tbManager.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutAdminMain.openDrawer(GravityCompat.START);
            }
        });
    }

    private void selectMenu() {
        nvMain=findViewById(R.id.nvMainAdmin);
        View view=nvMain.getHeaderView(0);
        ivUser=view.findViewById(R.id.ivUser);
        String urlImage=LoginActivity.prefConfig.readPath();
        Glide.with(getApplicationContext()).asBitmap().load(urlImage).signature(new ObjectKey(String.valueOf(System.currentTimeMillis()))).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                bitmapUser=resource;
                ivUser.setImageBitmap(bitmapUser);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
        tvUser=view.findViewById(R.id.tvUser);
        tvUser.setText(LoginActivity.prefConfig.readName());
        nvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_logout:
                        LoginActivity.prefConfig.writeLoginStatus(false);
                        LoginActivity.prefConfig.writeName("user");
                        LoginActivity.prefConfig.writePath("path");
                        LoginActivity.prefConfig.writeLevelWrite(1);
                        LoginActivity.prefConfig.writeIdUser(0);
                        LoginActivity.prefConfig.writeLevelSpeech(1);
                        LoginActivity.prefConfig.writeCheckAdmin(0);
                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

}