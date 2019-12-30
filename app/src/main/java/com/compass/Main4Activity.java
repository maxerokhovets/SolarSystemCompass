package com.compass;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.nucldev.engine.SolarSystemCompassLauncher;
import com.compass.solarsystemcompassapp.R;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        TextView tw = findViewById(R.id.out_text2);
        tw.setBackgroundColor(Color.TRANSPARENT);
        tw.setText(SolarSystemCompassLauncher.planetInfo.toString());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
