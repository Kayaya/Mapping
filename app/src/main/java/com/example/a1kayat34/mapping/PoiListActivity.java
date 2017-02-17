package com.example.a1kayat34.mapping;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PoiListActivity extends ListActivity {

    //declare arrayData
    private String[] names, details;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialise array
        names = new String[]{"IE","Firefox","Chrome"};
        details = new String[] { "made by Microsoft", "Made by Mozila",
                "Made by Google"};

        //Declare an Adaptar
        ArrayAdapter<String> browserAdaptar;

        //set the list view and setup as text
        browserAdaptar = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);

        //Create my customised adapter
        BrowserAdapter browserAdapter = new BrowserAdapter();
        setListAdapter(browserAdapter);


    }
    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        // handle list item selection

    }

    class BrowserAdapter extends ArrayAdapter<String>{
        public BrowserAdapter(){
            super(PoiListActivity.this, android.R.layout.simple_list_item_1,names);

        }
        @Override
        public View getView(int index, View convertView, ViewGroup parent){
            View view = convertView;
            if(view == null){
                //Inflating or expanding XML layout into objects
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //set the view
                view = inflater.inflate(R.layout.poientry, parent, false);
            }

            TextView nameTV = (TextView)view.findViewById(R.id.browser_name);
            TextView descriptionTV = (TextView)view.findViewById(R.id.browser_desc);

            nameTV.setText(names[index]);
            descriptionTV.setText(details[index]);


            return view;

        }
    }
}
