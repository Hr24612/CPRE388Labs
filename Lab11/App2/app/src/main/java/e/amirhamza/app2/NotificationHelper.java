package e.amirhamza.app2;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationHelper extends Application {
    public static final String CHANNEL_ID = "NotificationChannel";

    //Called by the system when a service is first created
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    //Creating a notification channel to display notification in Foreground Service.
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID, "NotificationChannel", NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


}
