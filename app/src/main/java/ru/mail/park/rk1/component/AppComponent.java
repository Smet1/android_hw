package ru.mail.park.rk1.component;

public class AppComponent {
    private int LastNumber = 0;

    private static AppComponent instance = null;

    public static AppComponent getInstance() {
        return instance;
    }

    private AppComponent() {
    }

    public static void init() {
        if (instance == null) {
            instance = new AppComponent();
        }
    }

    public int getLastNumber() {
        return LastNumber;
    }

    public void setLastNumber(int lastNumber) {
        LastNumber = lastNumber;
    }
}
