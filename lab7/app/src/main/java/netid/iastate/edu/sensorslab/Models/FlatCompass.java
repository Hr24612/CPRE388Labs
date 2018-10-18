package netid.iastate.edu.sensorslab.Models;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import netid.iastate.edu.sensorslab.Interfaces.SensorUpdateCallback;

import static android.content.Context.SENSOR_SERVICE;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;

/**
 * This class is used for managing a FlatCompass object, we use this to do business logic before updating the UI
 */
public class FlatCompass implements SensorEventListener {
    private SensorManager mSensorManager;//used to store the SensorManager for use throughout the model class
    private Sensor mMagField;//used to get and start/register the Sensor
    private SensorUpdateCallback mCallback;//used to keep track of the activity to callback to

    public FlatCompass(Context context, SensorUpdateCallback callback) {
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE); // TODO Get the Sensor Service using the application context
        mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); // TODO Get a magnetic field sensor
        mCallback = callback;
    }

    /**
     * This method is called from the activity when the sensor listener should be registered
     */
    public void start() {
        // TODO Register magnetic field sensor listener
        mSensorManager.registerListener(this, mMagField, mSensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * This method is called from the activity when the sensor listener should be unregistered
     */
    public void stop() {
        // TODO Unregister magnetic field sensor listener
        mSensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float orientation = 0.0f;

        // TODO Calculate the orientation
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){

           float x = event.values[0];
           float y = event.values[1];

           if(y == 0) {
               orientation = x;
           }
           else
               orientation = (float) (Math.atan2(x, y) * 180 / Math.PI);
        }

        mCallback.update(orientation);//use callback to call update() method in the activity
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //do nothing in our scenario
    }
}