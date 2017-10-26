package com.statsandtracksexample.statsandtracks_10;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class IntegratingMapActivity extends AppCompatActivity {

    ScaleBarOverlay mScaleBarOverlay;
    MyLocationNewOverlay mLocationOverlay;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context thisContext = getApplicationContext();
        Configuration.getInstance().load(thisContext, PreferenceManager.getDefaultSharedPreferences(thisContext));
        setContentView(R.layout.activity_integrating_map);

        //setting the map to fill the tile
        MapView thisMap = (MapView) findViewById(R.id.map);
        thisMap.setBuiltInZoomControls(true);
        thisMap.setMultiTouchControls(true);
        IMapController mapController = thisMap.getController();
        mapController.setZoom(9);
        GeoPoint startPoint = new GeoPoint(53.305344, -6.220654);
        mapController.setCenter(startPoint);


        //adding in 'my location'
        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(thisContext), thisMap);
        this.mLocationOverlay.enableMyLocation();
        thisMap.getOverlays().add(this.mLocationOverlay);

//play around with these values to get the location on screen in the right place for your applicatio
        mScaleBarOverlay = new ScaleBarOverlay(thisMap);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(300, 10);
        thisMap.getOverlays().add(this.mScaleBarOverlay);


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
