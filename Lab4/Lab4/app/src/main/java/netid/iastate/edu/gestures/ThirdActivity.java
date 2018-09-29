package netid.iastate.edu.gestures;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.widget.RelativeLayout;

/**
 * Activity which represents the left view.
 */
public class ThirdActivity extends CustomGestureListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setLeftRight(SecondActivity.class, MainActivity.class);

        //TODO - check for passed intent and set background color according to the swipe direction.
        ConstraintLayout layout3;
        layout3 = findViewById(R.id.thirdActivity);
        Intent i = getIntent();
        String c = i.getStringExtra("id");

        if(c.equals("left")) {

            layout3.setBackgroundResource(R.color.colorPrimaryDark);
        }
        if(c.equals("right")){
            layout3.setBackgroundResource(R.color.colorPrimary);
        }
    }
}
