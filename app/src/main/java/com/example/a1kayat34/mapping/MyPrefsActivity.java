package com.example.a1kayat34.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPrefsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
