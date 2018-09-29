package netid.iastate.edu.gestures;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.widget.RelativeLayout;

/**
 * Activity which represents the right view.
 */
public class SecondActivity extends CustomGestureListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setLeftRight(MainActivity.class, ThirdActivity.class);

        //TODO - check for passed intent and set background color according to the swipe direction.
        ConstraintLayout layout2;
        layout2 = findViewById(R.id.secondActivity);
        Intent i = getIntent();
        String c = i.getStringExtra("id");

        if(c.equals("left")) {
            layout2.setBackgroundResource(R.color.colorPrimary);
        }
        if(c.equals("right")){
            layout2.setBackgroundResource(R.color.colorAccent);
        }
    }
}