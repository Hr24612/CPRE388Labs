package e.amirhamza.lab10;

import android.content.res.Resources;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double endLong, endLat;
    double latitude = 42.031924;
    double longitude = -93.634611;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        try{
            boolean isSuccess = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_mine)
            );
            if(!isSuccess){
                Log.e("Error", "Map style load failure!");
            }
        }
        catch (Resources.NotFoundException ex){
            ex.printStackTrace();
        }
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        float[] distance = new float[10];
        Location.distanceBetween(42.031924, 93.634611, 41.585016, 93.625024, distance);

        LatLng Ames = new LatLng(42.031924, -93.634611);
        mMap.addMarker(new MarkerOptions().position(Ames).title("Marker in Ames"));
        LatLng DesMoines = new LatLng(41.585016, -93.625024);
        mMap.addMarker(new MarkerOptions().position(DesMoines).title("Marker in DesMoines").snippet("Distance is: "+distance[0] + "m"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(DesMoines));
    }
}
