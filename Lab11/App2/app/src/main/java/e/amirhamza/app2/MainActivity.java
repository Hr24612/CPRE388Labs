package e.amirhamza.app2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Initializing...
    private Context mContext;
    MediaPlayer mediaPlayer = null;
    int pauseCurrentPosition = 0;
    String s;
    int y = 0;
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final int GET_NUMBER_FLAG = 0;
    private boolean mIsBound;
    private int NumberValue;

    Messenger NumberRequestMessenger, NumberReceiveMessenger;

    private Intent serviceIntent;
    private TextView textViewNumber;
    private Button buttonBindService, buttonUnBindService, buttonGetNumber;

    class NumberHandler extends Handler {
        //Message handler to handle the message being received from the other app.
        @Override
        public void handleMessage(Message msg) {
            NumberValue = 0;

            switch (msg.what) {
                case GET_NUMBER_FLAG:
                    NumberValue = msg.arg1;
                    getValue(NumberValue);
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    }

    ServiceConnection NumberServiceConnection = new ServiceConnection() {
        // This is called when the connection with the service has been unexpectedly disconnected
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            NumberRequestMessenger = null;
            NumberReceiveMessenger = null;
            mIsBound = false;
        }

        //his is called when the connection with the service has been established
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder binder) {
           NumberRequestMessenger = new Messenger(binder);
           NumberReceiveMessenger = new Messenger(new NumberHandler());
            mIsBound = true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        textViewNumber = findViewById(R.id.textViewNumber);

        buttonBindService = findViewById(R.id.buttonBindService);
        buttonUnBindService = findViewById(R.id.buttonUnBindService);
        buttonGetNumber = findViewById(R.id.buttonGetNumber);


       buttonGetNumber.setOnClickListener(this);
        buttonBindService.setOnClickListener(this);
        buttonUnBindService.setOnClickListener(this);

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("e.amirhamza.app1", "e.amirhamza.app1.MyService"));
    }

    //OnCreateListener for different button is the activity layout.
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBindService:
                bindToRemoteService();
                break;
            case R.id.buttonUnBindService:
                unbindFromRemoteService();
                break;
            case R.id.buttonGetNumber:
                fetchNumber();

            default:
                break;
        }
    }

    // Attempts to establish a connection with the service.
    private void bindToRemoteService() {
        bindService(serviceIntent, NumberServiceConnection, BIND_AUTO_CREATE);
        Toast.makeText(mContext, "Service bound", Toast.LENGTH_SHORT).show();
    }

    //Unregister the service.
    private void unbindFromRemoteService() {
        if (mIsBound) {
            unbindService(NumberServiceConnection);
            mIsBound = false;
            Toast.makeText(mContext, "Service Unbound", Toast.LENGTH_SHORT).show();
        }
    }

    //Getting received number from the other app.
    private void fetchNumber() {
        if (mIsBound == true) {
            Message requestMessage = Message.obtain(null, GET_NUMBER_FLAG);
            requestMessage.replyTo = NumberReceiveMessenger;

            try {
                NumberRequestMessenger.send(requestMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "Service Unbound, can't get number", Toast.LENGTH_SHORT).show();
        }
    }

    //value c is the received value from the other app. Conditional statements are used to identity which operation to perform.
    public void getValue(int c) {
        y = c;

        if (y == 2) {
            textViewNumber.setText("Play Button Pressed");
            if (mediaPlayer == null) {
                s = "Maroon 5, Don't wanna know";
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a);
                mediaPlayer.start();
                y = 0;
            } else if (!mediaPlayer.isPlaying()) {
                mediaPlayer.seekTo(pauseCurrentPosition);
                mediaPlayer.start();
               y = 0;
            }
        }
        if (y == 1) {
            textViewNumber.setText("Pause Button Pressed");
            if (mediaPlayer != null) {
                mediaPlayer.pause();
                pauseCurrentPosition = mediaPlayer.getCurrentPosition();
                y = 0;
            }
        } if(y == 3){
            textViewNumber.setText("Stop Button Pressed");
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer = null;
                y = 0;
            }
        }
    }

    //Begin notification service.
    public void startService(View view) {

        Intent serviceIntent = new Intent(this, NotificationService.class);
        serviceIntent.putExtra("input", s);
        startService(serviceIntent);
    }

    //Stop notification service.
    public void stopService(View view){
        Intent serviceIntent = new Intent(this, NotificationService.class);
        stopService(serviceIntent);
    }
}