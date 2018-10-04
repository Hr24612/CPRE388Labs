package netid.iastate.edu.lab5.Activities;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

import netid.iastate.edu.agenda.R;
import netid.iastate.edu.lab5.Models.AppDatabase;
import netid.iastate.edu.lab5.Models.Event;

/**
 * This activity is called when an event title on the main page is pressed. Its
 * corresponding view is activity_event_details, which presents all of the information
 * about the selected event that is stored in the database.
 */
public class EventDetailsActivity extends Activity {

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        database = AppDatabase.getAppDatabase(this);

        //TODO - get the ID passed from the EventListActivity
        Bundle b = getIntent().getExtras();
        long eventId = b.getLong("hello");

        //TODO - query the database to get the user selected event using the id passed from above Intent
        int i = (int) eventId;
        Event selectedEvent = database.eventDao().findByID(i);



        //Set views to reflect event data
        //TODO - uncomment the bellow code block once you write the code above
        TextView temp = findViewById(R.id.titleOutput);
        temp.setText(selectedEvent.getTitle());

        temp = findViewById(R.id.locationOutput);
        temp.setText(selectedEvent.getLocation());

        temp = findViewById(R.id.startOutput);
        temp.setText(selectedEvent.getReadableStartTime());

        temp = findViewById(R.id.endOutput);
        temp.setText(selectedEvent.getEndTime());

        temp = findViewById(R.id.deetsOutput);
        temp.setText(selectedEvent.getDetails());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_event_details, menu);
        return true;
    }

}
