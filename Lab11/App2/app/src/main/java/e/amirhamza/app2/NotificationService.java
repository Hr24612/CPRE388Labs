package e.amirhamza.app2;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import static e.amirhamza.app2.NotificationHelper.CHANNEL_ID;

public class NotificationService extends Service {
    public NotificationService() {
    }

    //Called by the system when the service is first created.
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //Called by the system to notify a service that it is no longer used and is being used.
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Service executing code in this method.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("input");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Now Playing")
                .setContentText(input).setSmallIcon(R.drawable.ic_1)
                .setContentIntent(pendingIntent).build();
        startForeground(1,notification);
        return START_NOT_STICKY;
    }

    // This is the object that receives interactions from clients.
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
