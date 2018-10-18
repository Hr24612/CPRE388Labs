package netid.iastate.edu.sensorslab.Models;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import netid.iastate.edu.sensorslab.Interfaces.SensorUpdateCallback;

/**
 * This class is used for managing a TiltCalculator object, we use this to do business logic before updating the UI
 */
public class TiltCalculator implements SensorEventListener {
    private SensorManager mSensorManager;//used to store the SensorManager for use throughout the model class
    private Sensor mAcc;//used to get and start/register the Sensor
    private SensorUpdateCallback mCallback;//used to keep track of the activity to callback to

    public TiltCalculator(Context context, SensorUpdateCallback callback) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE); // TODO Get the Sensor Service using the application context
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // TODO Get an accelerometer
        mCallback = callback;
    }

    /**
     * This method is called from the activity when the sensor listener should be registered
     */
    public void start() {
        // TODO Register accelerometer sensor listener
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * This method is called from the activity when the sensor listener should be unregistered
     */
    public void stop() {
        // TODO Unregister accelerometer sensor listener
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float orientation = 0.0f;
        float x, y, z;
        double pitch, roll = 0.0;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            double arcTan = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));

            if(arcTan != 0.0) {
                pitch = Math.atan(y / Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2))); // TODO Determine pitch from accelerometer
                roll = Math.atan(-x / z); // TODO Determine roll from accelerometer
                orientation = (float) (Math.atan2(pitch, roll) * 180 / Math.PI) + 90.0f; // TODO Determine orientation from pitch and roll
            }
            else {
               orientation = 0;
            }

            mCallback.update(orientation);//use callback to call update() in activity
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
