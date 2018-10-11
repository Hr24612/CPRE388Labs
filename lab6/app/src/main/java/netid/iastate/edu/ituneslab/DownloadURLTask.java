package netid.iastate.edu.ituneslab;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Async task that downloads a specified URL.
 */
public class DownloadURLTask extends AsyncTask<String, Integer, String> {
    /**
     * The instance of the class that will receive a callback when the URL is retrieved
     * successfully.
     */
    private ResultHandler resultHandler;

    DownloadURLTask(ResultHandler resultHandler) {
        this.resultHandler = resultHandler;
    }

    @Override
    protected String doInBackground(String... params) {
        // Verify that only one URL has been provided.
        if (params.length != 1) {
            throw new IllegalArgumentException("This class only handles one URL as an argument");
        }
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            return readStream(httpURLConnection.getInputStream());
        } catch (java.io.IOException e) {
            // Print the stack trace to Logcat and continue. For a reliable app, an appropriate
            // action would be adding an error callback to the ResultHandler interface.
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // This is not implemented here, but know that you can use this to notify users of the
        // task's progress.
    }

    @Override
    protected void onPostExecute(String result) {
        // Send a callback to the class registered for callbacks in the constructor.
        resultHandler.handleResult(result);
    }

    /**
     * A helper function that reads an InputStream until its end and returns the data as a string.
     *
     * @param inputStream the stream to read to the end
     * @return a string of all the data read from inputStream
     */
    private String readStream(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * An interface that must be implemented by classes that desire to receive callbacks on
     * completion of a URL retrieval.
     */
    public interface ResultHandler {
        /**
         * Callback for when the URL has been retrieved.
         *
         * @param result a string containing the contents returned from the URL
         */
        void handleResult(String result);
    }
}

