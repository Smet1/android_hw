package ru.mail.park.rk1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        RecyclerFragment numbers = (RecyclerFragment) fragmentManager.findFragmentById(R.id.number_fragment);
        if (numbers == null)
            transaction.replace(R.id.container, RecyclerFragment.newInstance(LastNumber));

        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment f = fragmentManager.getFragments().get(0);

        if (f instanceof RecyclerFragment) {
            RecyclerFragment rf = (RecyclerFragment) f;

            outState.putInt(NUMBERS, rf.last);
        } else if (f instanceof NumberFragment) {
            NumberFragment rf = (NumberFragment) f;

            outState.putInt(NUM, rf.number);
        }

        Log.d("kek", "onSaveInstanceState MainActivity");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        LastNumber = savedInstanceState.getInt(NUMBERS);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        RecyclerFragment numbers = (RecyclerFragment) fragmentManager.findFragmentById(R.id.number_fragment);
//        if (numbers == null)
//            transaction.replace(R.id.container, RecyclerFragment.newInstance(LastNumber));
//
//        transaction.commit();
        Log.d("kek", "onRestore MainActivity");
    }

    public void SetLastNumber(int i) {
        LastNumber = i;
    }
}
