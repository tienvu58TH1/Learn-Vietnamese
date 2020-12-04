package com.example.projectda.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.example.projectda.R;
import com.example.projectda.fragments.MenuFragment;
import com.example.projectda.models.Track;
import com.example.projectda.callback.CallBackPlayComplete;
import com.example.projectda.utils.CheckConnection;
import com.example.projectda.utils.LocaleManager;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayoutMain;
    public static Toolbar tbMain;
    public NavigationView nvMain;
    public static ImageView ivUser;
    public static TextView tvUser;
    private static MediaPlayer mediaPlayer;
    public static boolean isPlaying=false;
    public static boolean checkShowTrack=false;
    public static int positionTrack=0;
    public static ArrayList<Track> mTracks;
    private static int current=0;
    public static Bitmap bitmapUser;
    private static boolean isLoadLocale=false;
    public static int key=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mTracks =new ArrayList<>();

        if (!isLoadLocale){
            isLoadLocale=true;
            LocaleManager.loadLocale(this);
            recreate();
        }

        actionBar();
        selectMenu();
        setupView();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            getDataWithRetrofit();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }

    }

    private void setupView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main,new MenuFragment()).commit();
    }

    private void selectMenu() {
        nvMain=findViewById(R.id.nvMain);
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
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    private void actionBar() {
        tbMain=findViewById(R.id.tbMain);
        drawerLayoutMain=findViewById(R.id.drawerLayoutMain);
        setSupportActionBar(tbMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        key=getIntent().getIntExtra("keymain",0);
        if (key==1){
            drawerLayoutMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            tbMain.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            tbMain.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }else {
            tbMain.setNavigationIcon(R.drawable.ic_menu_drawer);
            tbMain.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayoutMain.openDrawer(GravityCompat.START);
                }
            });
        }
    }
    public static void onCreateMp3(String linkmp3, final CallBackPlayComplete callBackPlayComplete){
        current=0;
        mediaPlayer= new MediaPlayer();
        try {
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            mediaPlayer.setDataSource(linkmp3);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isPlaying=true;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isPlaying=false;
                current=0;
                callBackPlayComplete.onCompleteMp3();
            }
        });
    }

    public static void onStartMp3(){
        if (isPlaying){
            onPauseMp3();
        }else {
            onPlay();
        }
    }
    public static void onStopMp3(){
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
        isPlaying=false;
    }

    public static void onPreAndNextMp3(CallBackPlayComplete callBackPlayComplete){
        if (isPlaying){
            onStopMp3();
            onCreateMp3(mTracks.get(positionTrack).getLink(),callBackPlayComplete);
        }else {
            onCreateMp3(mTracks.get(positionTrack).getLink(),callBackPlayComplete);
        }
    }

    public static void onPauseMp3(){
        current=mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
        isPlaying=false;
    }

    public static void onPlay(){
        mediaPlayer.seekTo(current);
        mediaPlayer.start();
        isPlaying=true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlaying){
            onStopMp3();
            isPlaying=false;
        }
        bitmapUser=null;
        checkShowTrack=false;
        key=0;
    }
    private void getDataWithRetrofit() {
        Call<List<Track>> call= LoginActivity.apiInterface.getTracksMp3();
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if (response.body()==null){
                    return;
                }
                mTracks = (ArrayList<Track>) response.body();
            }
            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                LoginActivity.prefConfig.displayToast(t.getMessage());
            }
        });
    }

}