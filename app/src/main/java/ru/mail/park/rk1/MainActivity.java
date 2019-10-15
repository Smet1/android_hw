package ru.mail.park.rk1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private int LastNumber = 0;
    private final static String NUMBERS = "numbers";
    private final static String NUM = "num";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("kek", "onCreate MainActivity");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            RecyclerFragment numbers = (RecyclerFragment) fragmentManager.findFragmentById(R.id.number_fragment);
            if (numbers == null)
                transaction.replace(R.id.container, RecyclerFragment.newInstance(LastNumber));

            transaction.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("kek", "onSaveInstanceState MainActivity");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("kek", "onRestore MainActivity");

        super.onRestoreInstanceState(savedInstanceState);
    }

    public void SetLastNumber(int i) {
        LastNumber = i;
    }
}
