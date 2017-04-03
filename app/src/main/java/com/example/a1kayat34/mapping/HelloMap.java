package com.example.a1kayat34.mapping;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;
import android.widget.Toast;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class HelloMap extends Activity implements View.OnClickListener {

    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers.
        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(40.1,22.5));

        //set up markers gestures... so that when clicked it makes happen something
        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                Toast.makeText(HelloMap.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                Toast.makeText(HelloMap.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };



        //Set overlay items
        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
        OverlayItem luanda = new OverlayItem("Luanda", "capical of Angola", new GeoPoint(-8.8271,13.2464));
        OverlayItem jardimDoEden = new OverlayItem("Jardim do Eden", "Condominio", new GeoPoint(-8.9376,13.2455));

        //adding the item to overlay
        items.addItem(luanda);
        items.addItem(jardimDoEden);

        // Load in the CSV file - notes at: http://www.free-map.org.uk/course/mad/part9.php
        // loop through each result, adding a marker to the map based on the current line

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath()+"/poi.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                if (components.length == 5) {
                    OverlayItem newLocation = new OverlayItem(components[0], components[1], components[2], new GeoPoint(Double.parseDouble(components[4]),Double.parseDouble(components[3])));
                    items.addItem(newLocation);
                }

            }
        }
        catch (IOException e){
            new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(e.toString()).show();
        }

        //set overlay to map
        mv.getOverlays().add(items);


        Button go = (Button) findViewById(R.id.go);
        go.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        double latitude = Double.parseDouble(prefs.getString("lat", "50.9"));
        double longitude = Double.parseDouble(prefs.getString("lon", "-1.4"));
        Integer zoom = Integer.parseInt(prefs.getString("zoom", "14"));

        mv.getController().setZoom(zoom);
        mv.getController().setCenter(new GeoPoint(latitude,longitude));

    }

    @Override
    public void onClick(View view){
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);

        double lat = Double.parseDouble(latitude.getText().toString());
        double lon = Double.parseDouble(longitude.getText().toString());

        mv.getController().setCenter(new GeoPoint(lat,lon));
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)
        {
            // react to the menu item being selected...
            //System.exit(0);
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent, 3);
            return true;
        }
        else if(item.getItemId() == R.id.setlocation){
            Intent intent = new Intent(this,SetLocationActivity.class);
            startActivityForResult(intent, 1);
            return true;

        }
        else if(item.getItemId() == R.id.poiList){
            Intent intent = new Intent(this,PoiListActivity.class);
            startActivityForResult(intent, 2);
            return true;

        }
        else if(item.getItemId() == R.id.choosemapstyle){
            Intent intent = new Intent(this,MapListActivity.class);
            startActivityForResult(intent, 0);
            return true;

        }
        else if(item.getItemId() == R.id.preferences){
            Intent intent = new Intent(this,MyPrefsActivity.class);
            startActivityForResult(intent, 4);
            return true;

        }

        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        if(requestCode==3)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Bundle extras = intent.getExtras();
                Double latitude = extras.getDouble("com.example.latitude");
                Double longitude = extras.getDouble("com.example.longitude");

                mv.getController().setCenter(new GeoPoint(latitude,longitude));
            }

        }
        /* list exercise*/
        if(requestCode==0){

        }
    }


}
