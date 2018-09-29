package netid.iastate.edu.gestures;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static netid.iastate.edu.gestures.R.id.view;

/**
 * This Activity shows touch information on a TextView.
 */
public class CoordinateActivity extends Activity {

    LinearLayout a;
    TextView b;
    float x;
    float y;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);

        //TODO - set a touch listener for the (linear) layout to a new instance of the class below.
        a = findViewById(R.id.view);
        b = findViewById(R.id.textView);
        a.setOnTouchListener(new OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        x = event.getX();
                        y = event.getY();
                        b.setText(String.valueOf("X: "+x+"Y: "+y));
                        //b.setText(String.valueOf(y));
                        break;

                    case MotionEvent.ACTION_UP:
                        x = event.getX();
                        y = event.getY();
                        b.setText(String.valueOf("X: "+x+"Y: "+y));
                        break;

                }
                return true;
            }
        });

    }

    //TODO - Create a private class that implements View.OnTouchListener.
    //TODO - Override the onTouch method and implement logic that checks for a moving action and if
    // true, updates (set) the TextView in the layout to display the ( x , y ) coordinates of the
    // moving finger


}