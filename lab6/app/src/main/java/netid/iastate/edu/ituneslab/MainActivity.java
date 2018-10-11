package netid.iastate.edu.ituneslab;

import android.app.ListActivity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements DownloadURLTask.ResultHandler {
    /**
     * The list of song records from iTunes to display in the ListView.  This acts as the MVC model.
     */
    Button search;
    EditText et;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        et = findViewById(R.id.username);

        /**
         * Handles the onClick event of the search button.
         *
         * @param view the search button
         */
        search.setOnClickListener(new View.OnClickListener() {

            /* (non-Javadoc)
             * @see android.view.View.OnClickListener#onClick(android.view.View)
             */
            public void onClick(View v) {
                // TODO get the artist to search for from the activity_main.xml view
                String artist = et.getText().toString();
                String request = "https://itunes.apple.com/search?term="+ Uri.encode(artist)+"&entity=song&limit=20";
                // TODO execute a new DownloadURLTask
                DownloadURLTask t = new DownloadURLTask(MainActivity.this);
                t.execute(request);

            }
        });
    }


    /**
     * Callback from a DownloadURLTask when the URL has been retrieved.
     *
     * @param result a string containing the contents returned from the URL
     */
    @Override
    public void handleResult(String result) {
        // TODO handle the Result of the DownloadURLTask network call

        try{
            ArrayList<ItunesSongRecord> records = new ArrayList<>();
            JSONObject jresult = new JSONObject(result);

            int numRecords = jresult.getInt("resultCount");
            JSONArray jresultArr = jresult.getJSONArray("results");

            for(int i = 0; i< numRecords; i++){
                JSONObject r = (JSONObject) jresultArr.get(i);
                ItunesSongRecord ir = new ItunesSongRecord(r.getString("collectionName"), r.getString("trackName"));
                records.add(ir);
            }

            ItunesAdapter ia = new ItunesAdapter(this, R.layout.songrow, records);
            setListAdapter(ia);

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}