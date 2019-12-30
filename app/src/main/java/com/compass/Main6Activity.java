package com.compass;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.nucldev.engine.SolarSystemCompassLauncher;
import com.nucldev.engine.utils.DegreesConvertion;
import com.compass.solarsystemcompassapp.R;

public class Main6Activity extends AppCompatActivity implements SensorEventListener {

    private ImageView arrowImage;
    private float rotateDegree = 0f;
    private SensorManager mSensorManager;
    TextView horCoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        arrowImage = findViewById(R.id.arrow);
        horCoordinates = findViewById(R.id.hor_coord_view);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(Main6Activity.this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(Main6Activity.this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        int[] cAzArr = DegreesConvertion.fromDecimal(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth());
        int[] cAlArr = DegreesConvertion.fromDecimal(SolarSystemCompassLauncher.planetInfo.getCurrentAltitude());
        horCoordinates.setText(SolarSystemCompassLauncher.planetInfo.getPlanetName()+"\n"
                                +"Current azimuth: "+"\n"+cAzArr[0] + "°" + cAzArr[1] + "'" + cAzArr[2] + "\"" +"\n"
                                + "Current altitude: "+"\n"+cAlArr[0] + "°" + cAlArr[1] + "'" + cAlArr[2] + "\""+"\n"
                                + "* enter magnetic declination for your area");
        RotateAnimation rotateAnimation = new RotateAnimation(
                rotateDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setDuration(200);
        rotateAnimation.setFillAfter(true);
        arrowImage.startAnimation(rotateAnimation);
        float currentAzimuth = Float.parseFloat(SolarSystemCompassLauncher.planetInfo.getCurrentAzimuth().toString());
        EditText et = findViewById(R.id.edit_decl);
        float declination = 0f;
        try{
            declination = Float.parseFloat(et.getText().toString());
        }catch (NumberFormatException e){}
        rotateDegree = -degree + currentAzimuth + declination;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onRefreshClick(View view){
        String[] key = {SolarSystemCompassLauncher.planetInfo.getPlanetName()};
        SolarSystemCompassLauncher.main(key);
    }

}