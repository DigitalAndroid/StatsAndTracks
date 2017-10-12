package com.statsandtracksexample.statsandtracks_10;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;

public class IntegratingMapActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context thisContext = getApplicationContext();
        Configuration.getInstance().load(thisContext, PreferenceManager.getDefaultSharedPreferences(thisContext));
        setContentView(R.layout.activity_integrating_map);

        //setting the map to fill the tile
        MapView thisMap = (MapView) findViewById(R.id.map);
//        thisMap.setBuiltInZoomControls(true);
//        thisMap.setMultiTouchControls(true);
//        IMapController mapController = thisMap.getController();
//        mapController.setZoom(9);
//        GeoPoint startPoint = new GeoPoint(50.0895227, -122.8818379);
//        mapController.setCenter(startPoint);

        thisMap.setTileSource(TileSourceFactory.MAPNIK);
    }

    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }
}
