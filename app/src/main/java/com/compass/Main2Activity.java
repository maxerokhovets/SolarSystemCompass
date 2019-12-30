package com.compass;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.nucldev.engine.SolarSystemCompassLauncher;
import com.compass.solarsystemcompassapp.R;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onMercuryButtonClick(View view){
        String[] key = {"Mercury"};
        SolarSystemCompassLauncher.main(key);
        if(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth()!=null){
            if(SolarSystemCompassLauncher.planetInfo.getCulminationHeigh()>0){
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
             }else {
            Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
            startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
            startActivity(intent);
        }
    }

    public void onVenusButtonClick(View view){
        String[] key = {"Venus"};
        SolarSystemCompassLauncher.main(key);
        if(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth()!=null){
            if(SolarSystemCompassLauncher.planetInfo.getCulminationHeigh()>0){
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
            startActivity(intent);
        }
    }

    public void onMarsButtonClick(View view){
        String[] key = {"Mars"};
        SolarSystemCompassLauncher.main(key);
        if(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth()!=null){
            if(SolarSystemCompassLauncher.planetInfo.getCulminationHeigh()>0){
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
            startActivity(intent);
        }
    }

    public void onJupiterButtonClick(View view){
        String[] key = {"Jupiter"};
        SolarSystemCompassLauncher.main(key);
        if(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth()!=null){
            if(SolarSystemCompassLauncher.planetInfo.getCulminationHeigh()>0){
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
            startActivity(intent);
        }
    }

    public void onSaturnButtonClick(View view){
        String[] key = {"Saturn"};
        SolarSystemCompassLauncher.main(key);
        if(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth()!=null){
            if(SolarSystemCompassLauncher.planetInfo.getCulminationHeigh()>0){
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
            startActivity(intent);
        }
    }

    public void onUranusButtonClick(View view){
        String[] key = {"Uranus"};
        SolarSystemCompassLauncher.main(key);
        if(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth()!=null){
            if(SolarSystemCompassLauncher.planetInfo.getCulminationHeigh()>0){
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
            startActivity(intent);
        }
    }

    public void onNeptuneButtonClick(View view){
        String[] key = {"Neptune"};
        SolarSystemCompassLauncher.main(key);
        if(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth()!=null){
            if(SolarSystemCompassLauncher.planetInfo.getCulminationHeigh()>0){
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(Main2Activity.this,Main5Activity.class);
        startActivity(intent);
    }
}
