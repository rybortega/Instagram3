package com.example.parsagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("parsagram-2020") // should correspond to APP_ID env variable
                .clientKey("7Zq7x5kq")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://parsagram-2020.herokuapp.com/parse/").build());
    }
}
