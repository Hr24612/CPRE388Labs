package netid.iastate.edu.ituneslab;

import java.util.List;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * This class is used to "adapt" the song information (data in the constructor) to View objects in
 * a ListView.
 */
public class ItunesAdapter extends ArrayAdapter<ItunesSongRecord> {
    /**
     * The ID of the layout that will be inflated and populated as a row for a song.
     */
    @LayoutRes
    private int layoutRowId;
    /**
     * The layout inflater associated with the parent context (usually an Activity)
     */
    private LayoutInflater inflater;
    /**
     * The list of song records that will be displayed in the ListView.
     */
    private List<ItunesSongRecord> data;

    /**
     * Constructor for a new ItunesAdapter
     *
     * @param context          the context (Activity) that this ArrayAdapter is being used in
     * @param layoutResourceId The resource ID for a layout file containing a layout to use when
     *                         instantiating views. Must contain TextViews R.id.actualSong and
     *                         R.id.actualAlbum.
     * @param data             the ItunesSongRecord objects to represent in the ListView
     */
    public ItunesAdapter(Context context, @LayoutRes int layoutResourceId,
                         List<ItunesSongRecord> data) {
        // Provide the context and data to the superclass's constructor. There is no "resource" of
        // a TextView for the superclass, because we override getView() to use the layoutResourceId.
        super(context, 0, data);
        this.layoutRowId = layoutResourceId;
        this.data = data;
        // Get a layout inflater for the context to inflate the row layout later.
        inflater = LayoutInflater.from(context);
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position    The position of the item within the adapter's data set of the item whose
     *                    view we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible
     *                    to convert this view to display the correct data, this method can create a
     *                    new view.
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // This is for the row.xml layout
        View songRowView = convertView;
        // This holds references to the TextViews in row.xml that are updated in this function.
        ItunesViewHolder viewHolder;

        if (songRowView == null) {
            // convertView was null, meaning a new View must be inflated from the specified layout,
            // which should be row.xml.
            songRowView = inflater.inflate(layoutRowId, parent, false);

            // Create a new ItunesViewHolder and set it to the fields the row in the ListView
            viewHolder = new ItunesViewHolder();

            // TODO find the TextViews in songRowView and store references to them in

            viewHolder.albumNameTextView =  songRowView.findViewById(R.id.album);
            viewHolder.songTitleTextView =  songRowView.findViewById(R.id.song);

            // Store the viewHolder (references to TextView instances) with the View object
            songRowView.setTag(viewHolder);
        } else {
            // Retrieve the viewHolder to avoid calling songRowView.findViewById() again
            viewHolder = (ItunesViewHolder) songRowView.getTag();
        }

        // Get the song information record to display in this part of the ListView
        ItunesSongRecord itunesRecord = data.get(position);

        // TODO using the viewHolder, set the song information in this row
        viewHolder.albumNameTextView.setText(itunesRecord.getAlbumTitle());
        viewHolder.songTitleTextView.setText(itunesRecord.getSongTitle());

        return songRowView;
    }

    /**
     * A class to track the instances of the TextViews in the layout row. This is to avoid the need
     * to call findViewById() each time the row comes into view.
     */
    static class ItunesViewHolder {
        /**
         * The instance of the album name TextView that this view holder will be stored with.
         */
        TextView albumNameTextView;
        /**
         * The instance of the song title TextView that this view holder will be stored with.
         */
        TextView songTitleTextView;
    }
}