package com.anselmo.chemoparse;

import android.app.Application;

import com.anselmo.chemoparse.utils.Constants;
import com.parse.Parse;

/**
 * Created by Anselmo Hernandez on 11/3/15.
 */
public class ChemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, Constants.APPLICATION_ID_PARSE, Constants.CLIENT_KEY_PARSE);
    }
}
