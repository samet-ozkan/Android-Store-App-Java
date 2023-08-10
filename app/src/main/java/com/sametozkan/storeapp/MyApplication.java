package com.sametozkan.storeapp;

import android.app.Application;

import com.sametozkan.storeapp.di.component.AppComponent;
import com.sametozkan.storeapp.di.component.DaggerAppComponent;
import com.sametozkan.storeapp.di.module.AppModule;


public class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
