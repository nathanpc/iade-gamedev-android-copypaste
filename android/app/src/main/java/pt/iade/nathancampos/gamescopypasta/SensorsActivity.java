package pt.iade.nathancampos.gamescopypasta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SensorsActivity extends AppCompatActivity {
    private TextView labelGyroX;
    private TextView labelGyroY;
    private TextView labelGyroZ;

    private SensorManager sensorManager;
    private Sensor gyroSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        setupComponents();

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
               labelGyroX.setText(String.valueOf(event.values[0]));
               labelGyroY.setText(String.valueOf(event.values[1]));
               labelGyroZ.setText(String.valueOf(event.values[2]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
               Log.d("ACCU_CHANGE", sensor.toString() + " - " + accuracy);
            }
        }, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void setupComponents() {
        labelGyroX = (TextView)findViewById(R.id.gyrox_label);
        labelGyroY = (TextView)findViewById(R.id.gyroy_label);
        labelGyroZ = (TextView)findViewById(R.id.gyroz_label);
    }
}