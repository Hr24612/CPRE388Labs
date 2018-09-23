package e.amirhamza.lab3;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private long lastPause;
    private Button startButton;
    private Button pauseButton;
    private Button resetButton;
    private Chronometer chronoMeter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start);
        pauseButton = findViewById(R.id.pause);
        resetButton = findViewById(R.id.reset);
        chronoMeter = findViewById(R.id.chronometer);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(lastPause !=0){
                    chronoMeter.setBase(chronoMeter.getBase() + SystemClock.elapsedRealtime() - lastPause);

                }
                else {
                    chronoMeter.setBase(SystemClock.elapsedRealtime());

                }
                chronoMeter.start();
                startButton.setEnabled(false);
                pauseButton.setEnabled(true);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastPause = SystemClock.elapsedRealtime();
                chronoMeter.stop();
                pauseButton.setEnabled(false);
                startButton.setEnabled(true);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronoMeter.stop();
                chronoMeter.setBase(SystemClock.elapsedRealtime());
                lastPause = 0;
                startButton.setEnabled(true);
                pauseButton.setEnabled(true);
            }
        });

    }
}
