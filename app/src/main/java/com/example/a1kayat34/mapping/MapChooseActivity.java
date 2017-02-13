package com.example.a1kayat34.mapping;

/**
 * Created by 1kayat34 on 06/02/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class MapChooseActivity extends Activity implements View.OnClickListener{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mca);

        Button regular = (Button) findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button cyclemap = (Button) findViewById(R.id.btnCyclemap);
        cyclemap.setOnClickListener(this);


    }
    public void onClick(View view){

        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        //set cyclemap false by default
        boolean cyclemap = false;

        if(view.getId()==R.id.btnCyclemap){


            cyclemap = true;

        }
        bundle.putBoolean("com.example.cyclemap",cyclemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();

    }
}
