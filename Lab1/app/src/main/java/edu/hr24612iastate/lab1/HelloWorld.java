package edu.hr24612iastate.lab1;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

/**
 * Author: Amir Hamza
 * Date: 09/16/2018
 */
public class HelloWorld extends AppCompatActivity {

    private String [] names;
    private Random random;
    private ConstraintLayout lab1;
    private Button pressMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        names = getResources().getStringArray(R.array.names);
        random = new Random();

        lab1 = findViewById(R.id.lab1);
        pressMe = findViewById(R.id.pressMe);
        pressMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256),random.nextInt(256));
                lab1.setBackgroundColor(color);
            }
        });
    }

    public void chooseButtonOnClick(View v)
    {
        TextView nameTextView = findViewById(R.id.pressMe);
        int nameIndex = random.nextInt(names.length);
        nameTextView.setText(names[nameIndex]);
    }
}
