package com.example.a1kayat34.mapping;

/**
 * Created by 1kayat34 on 13/02/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class SetLocationActivity extends Activity implements View.OnClickListener{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sla);

        Button go = (Button) findViewById(R.id.btnGo);
        go.setOnClickListener(this);
    }

    public void onClick(View view){

        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);

        double lat = Double.parseDouble(latitude.getText().toString());
        double lon = Double.parseDouble(longitude.getText().toString());

        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        bundle.putDouble("com.example.latitude",lat);
        bundle.putDouble("com.example.longitude",lon);
        //
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();

    }
}
