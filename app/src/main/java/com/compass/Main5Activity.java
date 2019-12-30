package com.compass;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.compass.solarsystemcompassapp.R;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onEnterCoordClick(View view){
        Intent intent = new Intent(Main5Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onUseDeviceLocationClick(View view){
        Intent intent = new Intent(Main5Activity.this, Main7Activity.class);
        startActivity(intent);
    }

    public void onAboutClick(View view){
        AlertDialog.Builder adB = new AlertDialog.Builder(Main5Activity.this);
        adB.setMessage("This application is intended for amateur astronomers. It allows with sufficient accuracy to determine the position of the solar planets on the celestial sphere at the current time anywhere in the world. Based on the algorithms proposed by Peter Duffett-Smith in the book “Practical astronomy with your calculator” (1982).\n" +
                "The application compute the horizontal coordinates of the planet at the current time. In compass mode, the arrow indicates the current position of the planet (azimuth). If the planet is below horizon, compass mode is not available.\n" +
                "You can enter your coordinates manually, or use the location of your device. ")
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = adB.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {

    }



}
