package com.example.a1kayat34.mapping;

import android.app.Activity;
import android.os.Bundle;
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

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;


public class HelloMap extends Activity implements View.OnClickListener {

    MapView mv;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers.
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(40.1,22.5));

        Button go = (Button) findViewById(R.id.go);
        go.setOnClickListener(this);
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
            startActivityForResult(intent, 0);
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
        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        if(requestCode==0)
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
    }


}
