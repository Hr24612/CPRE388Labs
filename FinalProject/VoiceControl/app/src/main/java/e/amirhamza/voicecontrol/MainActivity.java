package e.amirhamza.voicecontrol;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Author: Amir Hamza
 */
public class MainActivity extends AppCompatActivity {

    private TextToSpeech myTTS;
    private SpeechRecognizer mySpeechRecognizer;
    ImageView imageView;
    int index = 0;
    AdView adView;


    Button pause, forward, backward, home;
    MediaPlayer mediaPlayer;
    int position;
    TextView textView;
    String sname;


    //WORKOUT & MUSIC PLAYER ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();

        //Adding AdMob to the activity
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Initializing the buttons in the activity
        pause = findViewById(R.id.pause);
        forward = findViewById(R.id.forward);
        backward = findViewById(R.id.previous);
        textView = findViewById(R.id.textView2);
        textView.setSelected(true);
        home = findViewById(R.id.home);
        FloatingActionButton fab = findViewById(R.id.fab);
        imageView = findViewById(R.id.imageView);

        //Enabling runTimePermission() method to initialize the music player
        runTimePermission();

        //onlick listener for the mic icon
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                mySpeechRecognizer.startListening(i);
            }
        });
        initializeTextToSpeech();
        initializeSpeechRecognizer();

        //onclick listener for home button.
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainApp.class));
            }
        });
    }

    //Method used to loop around the provided workout image files based on the given voice input.
    private void processResult(String command){
        final int[] images = new int[] {R.drawable.zero, R.drawable.abs1, R.drawable.abs2, R.drawable.bi1, R.drawable.bi2, R.drawable.tri1, R.drawable.tri2, R.drawable.delt2, R.drawable.delt3,  R.drawable.leg1, R.drawable.leg2, R.drawable.back1, R.drawable.back2, R.drawable.back3, R.drawable.chest1, R.drawable.chest2, R.drawable.chest3};
        command = command.toLowerCase();


            if(command.indexOf("next") != -1){

                    if(index<images.length-1){
                        index++;
                        imageView.setImageResource(images[index]);
                    }
                    else {

                    }
                }

            else if(command.indexOf("previous") !=-1){

                if(index<images.length && index >0){
                    index--;
                    imageView.setImageResource(images[index]);
                }
                else index = 0;
            }
            }
    private void initializeSpeechRecognizer() {
        if(SpeechRecognizer.isRecognitionAvailable(this)){
            mySpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            mySpeechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    List<String> results = bundle.getStringArrayList(
                            SpeechRecognizer.RESULTS_RECOGNITION
                    );
                    processResult(results.get(0));
                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });
        }
    }

    //Method used when the intent is fired for the workout app.
    private void initializeTextToSpeech() {
        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(myTTS.getEngines().size() ==0){
                    Toast.makeText(MainActivity.this, "There is no Text to Speech engine on your device", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    myTTS.setLanguage(Locale.US);
                    speak("Hello! Welcome to the workout app");

                }
            }
        });
    }


    private void speak(String message){
        if(Build.VERSION.SDK_INT >= 21){
            myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else{
            myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
    }

    //Using Dexter API to enable permission for accessing device storage for music library used in the app.
    public void runTimePermission(){
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        play();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    //Finding songs from the device storage.
    ArrayList<File> findSong(File file) {
        ArrayList<File> arrayList = new ArrayList<>();

        File[] files = file.listFiles();
        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.addAll(findSong(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wva")) {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    //Method for initializing music player and enabling click listeners for the music keys.
    public void play() {

        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());


        sname = mySongs.get(position).getName().toString();
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setText(sname);

        pause.setBackgroundResource(R.drawable.pause);
        Uri uri = Uri.parse(mySongs.get(position).toString());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        pause.setBackgroundResource(R.drawable.play);


        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position+1)%mySongs.size());

                Uri uri = Uri.parse(mySongs.get(position).toString());

                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

                sname = mySongs.get(position).getName().toString();
                textView.setText(sname);
                pause.setBackgroundResource(R.drawable.pause);
                mediaPlayer.start();
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mediaPlayer.stop();
                mediaPlayer.release();

                position = ((position - 1) < 0) ? (mySongs.size() - 1) : (position - 1);
                Uri uri = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);


                sname = mySongs.get(position).getName().toString();
                textView.setText(sname);
                pause.setBackgroundResource(R.drawable.pause);
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mediaPlayer.isPlaying()){
                    pause.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                }
                else{
                    pause.setBackgroundResource(R.drawable.pause);
                    mediaPlayer.start();
                }
            }
        });
    }
}
