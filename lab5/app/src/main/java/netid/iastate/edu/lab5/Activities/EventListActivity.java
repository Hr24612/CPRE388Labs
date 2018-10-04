package netid.iastate.edu.lab5.Activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import netid.iastate.edu.agenda.R;
import netid.iastate.edu.lab5.Models.AppDatabase;
import netid.iastate.edu.lab5.Models.Event;

/**
 * This is the main activity that is in control when the application is opened.
 * Its corresponding view is activity_event_list, and it displays a list of the titles
 * of the events stored in the database.
 */
public class EventListActivity extends ListActivity {

    private AppDatabase database;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        database = AppDatabase.getAppDatabase(this);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //TODO - get the selected event's id and pass as an extra to EventDetailsActivity.class
        long eventId = database.eventDao().getAll().get(position).getUid();
        Intent i = new Intent(this, EventDetailsActivity.class);
        i.putExtra("hello", eventId);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO - assign a list of all the events retrieved from database.eventDao().getAll() method to eventList
        eventList  = database.eventDao().getAll();
        //TODO - delete old events before they are displayed by the list adapter

        for(int i = 0; i< eventList.size(); i++){


            if(eventList.get(i).getEndTimeAsDate().before(Calendar.getInstance().getTime())){
                database.eventDao().delete(eventList.get(i));
                eventList.remove(eventList.get(i));
            }
        }


        // Set source of ListView to List of Events in database
        ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this,
                android.R.layout.simple_list_item_1, eventList);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_event_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_event:
                Intent addIntent = new Intent(this, AddEventActivity.class);
                startActivity(addIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
