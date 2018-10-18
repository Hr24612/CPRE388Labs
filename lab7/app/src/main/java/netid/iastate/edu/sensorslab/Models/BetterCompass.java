package netid.iastate.edu.sensorslab.Models;

import android.app.Service;
import android.content.Context;
import android.content.IntentSender;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import netid.iastate.edu.sensorslab.Interfaces.SensorUpdateCallback;

/**
 * This class is used for managing a BetterCompass object, we use this to do business logic before updating the UI
 */
public class BetterCompass implements SensorEventListener {
    private SensorManager mSensorManager;//used to store the SensorManager for use throughout the model class
    private Sensor mMagField;//used to get and start/register the Sensor
    private Sensor mAcc;//used to get and start/register the Sensor
    private SensorUpdateCallback mCallback;//used to keep track of the activity to callback to
    boolean firstReading;
    private float[] mAccelerometerReading = new float[3];
    private float[] mMagnetometerReading = new float[3];

    public BetterCompass(Context context, SensorUpdateCallback callback) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE); // TODO Get the Sensor Service using the application context
        mMagField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); // TODO Get a magnetic field sensor
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // TODO Get an accelerometer
        mCallback = callback;
    }

    /**
     * This method is called from the activity when the sensor listeners should be registered
     */
    public void start() {
        // TODO Register the magnetic field and accelerometer listeners
        mSensorManager.registerListener(this, mMagField, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * This method is called from the activity when the sensor listeners should be unregistered
     */
    public void stop() {
        // TODO Unregister the magnetic field and accelerometer listeners
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // TODO Store magnetic field data in mMagnetometerReading

            mMagnetometerReading[0] = event.values[0];
            mMagnetometerReading[1] = event.values[1];
            mMagnetometerReading[2] = event.values[2];
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // TODO Store accelerometer data in mAccelerometerReading
            mAccelerometerReading[0] = event.values[0];
            mAccelerometerReading[1] = event.values[1];
            mAccelerometerReading[2] = event.values[2];
        }
            float orientation = 0.0f;
            // TODO Get orientation from magnetometer and accelerometer
            float[] mRotationMatrix = new float[9];
            float[] mOrientationAngles = new float[3];
            SensorManager.getRotationMatrix(mRotationMatrix, null, mAccelerometerReading, mMagnetometerReading);
            SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);
            orientation = (float) (-mOrientationAngles[0] * 180 / Math.PI);


            mCallback.update(orientation);//use callback to call update() method in the activity
        }

        @Override
        public void onAccuracyChanged (Sensor sensor,int accuracy){
            // Do nothing in our scenario
        }
    }