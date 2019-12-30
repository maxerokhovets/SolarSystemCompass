package com.compass;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.compass.exceptions.OutOfRangeException;
import com.nucldev.engine.SolarSystemCompassLauncher;
import com.nucldev.engine.utils.DegreesConvertion;
import com.compass.solarsystemcompassapp.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText fet = findViewById(R.id.lat_deg);
        fet.requestFocus();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onButtonEnterClick(View view){
        try{
            EditText editTextLatitudeDeg = findViewById(R.id.lat_deg);
            EditText editTextLatitudeMin = findViewById(R.id.lat_min);
            EditText editTextLatitudeSec = findViewById(R.id.lat_sec);
            EditText editTextLongitudeDeg = findViewById(R.id.long_deg);
            EditText editTextLongitudeMin = findViewById(R.id.long_min);
            EditText editTextLongitudeSec = findViewById(R.id.long_sec);
            int[] latitude = {Integer.parseInt(editTextLatitudeDeg.getText().toString()),
            Integer.parseInt(editTextLatitudeMin.getText().toString()),
            Integer.parseInt(editTextLatitudeSec.getText().toString())};
            int[] longitude = {Integer.parseInt(editTextLongitudeDeg.getText().toString()),
            Integer.parseInt(editTextLongitudeMin.getText().toString()),
            Integer.parseInt(editTextLongitudeSec.getText().toString())};
            double latitudeDecimal = DegreesConvertion.toDecimal(latitude);
            if(latitudeDecimal>90|latitudeDecimal<(-90))
                throw new OutOfRangeException();
            double longitudeDecimal = DegreesConvertion.toDecimal(longitude);
            if (longitudeDecimal>180|longitudeDecimal<(-180))
                throw  new OutOfRangeException();
            SolarSystemCompassLauncher.latitude = latitude;
            SolarSystemCompassLauncher.longitude = longitude;
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }catch (NumberFormatException e){
            AlertDialog.Builder adB = new AlertDialog.Builder(MainActivity.this);
            adB.setTitle("Enter your coordinates!")
                    .setMessage("This app cannot work " +
                            "without your coordinates. Enter your location, or use device location")
                    .setCancelable(false)
                    .setPositiveButton("Enter coordinates",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   dialog.cancel();
                                }
                            }).setNegativeButton("Use device\n location",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this,Main7Activity.class);
                                startActivity(intent);
                        }
                    });
            AlertDialog alertDialog = adB.create();
            alertDialog.show();
        } catch (OutOfRangeException e){
            AlertDialog.Builder adB = new AlertDialog.Builder(MainActivity.this);
            adB.setTitle("Out of range!")
                    .setMessage("Latitude should be between -90 and 90 degrees." +
                            " Longitude should be between -180 and 180 degrees.")
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
    }
}
