package e.amirhamza.voicecontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


/**
 * Author: Amir Hamza
 */
public class MainApp extends AppCompatActivity {

    Button timer, workout;
    AdView adView;

    //MAIN ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        //Add AdMob to the activity
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        getSupportActionBar().hide();


        //Main Activity where timer and workout activities can be accessed by clicking the timer or workout buttons.
        timer = findViewById(R.id.btn_timer);
        workout = findViewById(R.id.btn_workout);

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainApp.this, Main2Activity.class));
            }
        });
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainApp.this, MainActivity.class));

            }
        });
    }
}
