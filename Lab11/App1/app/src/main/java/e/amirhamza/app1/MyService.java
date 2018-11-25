package e.amirhamza.app1;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;


public class MyService extends Service {
    public MyService() {
    }


    //Initializing...
    private static final String TAG=MyService.class.getSimpleName();
    public static final int GET_NUMBER_FLAG=0;
    static int x = 0;
    private class NumberRequestHandler extends Handler{

        //Message handler to handle the message being sent to the other app.
        @Override
        public void handleMessage(Message msg){

            switch(msg.what){
                case GET_NUMBER_FLAG:


                    Message messageSendNumber = Message.obtain(null, GET_NUMBER_FLAG);
                    messageSendNumber.arg1= x;
                    try{
                        msg.replyTo.send(messageSendNumber);
                    }catch (RemoteException e){
                        Log.i(TAG, ""+e.getMessage());
                    }

            }
            super.handleMessage(msg);

        }

    }

    private Messenger NumberMessenger = new Messenger(new NumberRequestHandler());

    //To provide binding for a service, onBind is called.
    @Override
    public IBinder onBind(Intent intent) {
       return NumberMessenger.getBinder();
    }

    //Called when the second app has connected to the service, after it had previously been notified that all had disconnected in its onUnbind(Intent).
    @Override
    public void onRebind(Intent intent){
        super.onRebind(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        stopNumberGenerator();
    }

    //Service executing code in this method.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();
        return START_STICKY;
    }


    //Method called when OnDestroy() is called after Unbind.
    private void stopNumberGenerator(){
        Toast.makeText(getApplicationContext(),"Service Stopped",Toast.LENGTH_SHORT).show();
    }

    //Calling unbind with destroy the service, unless the service was started by startService().
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    //Returning the value being sent to the other app.
    public static int setValue(int c){
        x = c;
        return x;
    }
}
