package ru.mail.park.rk1;

import android.app.Application;

import ru.mail.park.rk1.component.AppComponent;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent.init();
    }
}
