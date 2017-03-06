package com.example.a1kayat34.mapping;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MapListActivity extends ListActivity {

    String[] names;
    String[] descriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_map_list);*/
        names = new String[]{"Regular Map", "Cycle Map"};
        descriptions = new String[]{"Display a regular map","Display a cycle map"};

        MyAdaptar adapter;
        adapter = new MyAdaptar();
        setListAdapter(adapter);
    }
    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        /*Boolean cyclemap = false;
        if(index == 1){
            cyclemap = true;
        }
        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        bundle.putDouble("com.example.cyclemap",cyclemap);
        //
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();*/
        // handle list item selection
        //still work to do here...


    }
    //cutomised adaptar
    public class MyAdaptar extends ArrayAdapter<String>{
            public MyAdaptar(){
            super(MapListActivity.this, android.R.layout.simple_list_item_1, names);
        }
        //decide how to display the layout
        public View getView(int index, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view ;
            view = inflater.inflate(R.layout.activity_map_list, parent, false);
            TextView nameTV = (TextView)view.findViewById(R.id.map_name);
            nameTV.setText(names[index]);
            TextView decriptionTV = (TextView)view.findViewById(R.id.map_desc);
            decriptionTV.setText(descriptions[index]);
            return view;
        }

    }
}
