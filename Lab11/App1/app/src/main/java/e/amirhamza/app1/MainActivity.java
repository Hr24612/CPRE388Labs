package e.amirhamza.app1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Initializing...
    private static final String TAG=MainActivity.class.getSimpleName();

    private Button buttonStartService, buttonStopService;
    private Context mContext;
    private Intent serviceIntent;
    TextView textView;
    Button play, pause, stop;
    MyService ms = new MyService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=getApplicationContext();
        buttonStartService= findViewById(R.id.buttonStartService);
        buttonStopService= findViewById(R.id.buttonStopService);
        play = findViewById(R.id.btn_play);
        pause = findViewById(R.id.btn_pause);
        stop = findViewById(R.id.btn_stop);
        textView = findViewById(R.id.textView);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        buttonStopService.setOnClickListener(this);
        buttonStartService.setOnClickListener(this);
        serviceIntent=new Intent(getApplicationContext(),MyService.class);
    }

    //OnClick method is used by play, pause and stop buttons to pass value.
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonStartService: startService(serviceIntent);
                Toast.makeText(mContext,"Service Started", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonStopService: stopService(serviceIntent);
                break;
            case R.id.btn_pause: ms.setValue(1);
                break;
            case R.id.btn_play: ms.setValue(2);
                break;
            case R.id.btn_stop: ms.setValue(3);
                break;
            default:break;
        }

    }
}