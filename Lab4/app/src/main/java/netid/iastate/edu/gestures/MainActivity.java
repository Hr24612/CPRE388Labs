package netid.iastate.edu.gestures;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Main activity which represents the middle view.
 */
public class MainActivity extends CustomGestureListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLeftRight(ThirdActivity.class, SecondActivity.class);

        //TODO - check for passed intent and set background color according to the swipe direction.
        //Use KEY_INTENT_DIRECTION defined in CustomGestureListener as the key for the direction
        // data.
        ConstraintLayout layout1;
        layout1 = findViewById(R.id.mainLayout);
        Intent i = getIntent();
        String c = i.getStringExtra("id");

        if (c != null) {
            if (c.equals("left")) {

                layout1.setBackgroundResource(R.color.colorPrimaryDark);
            }
            if (c.equals("right")) {
                layout1.setBackgroundResource(R.color.colorPrimary);
            }
        }
    }
}
