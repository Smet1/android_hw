package ru.mail.park.rk1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFragment extends Fragment {

    private final static String NUMBER_TO_SHOW = "number";
    public Integer number;

    public static NumberFragment newInstance(Integer num) {
        NumberFragment myFragment = new NumberFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(NUMBER_TO_SHOW, num);

        myFragment.setArguments(bundle);

        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("kek", "onCreate NumberFragment");
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null) {
            number = arguments.getInt(NUMBER_TO_SHOW);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("kek", "onCreateView NumberFragment");
        return inflater.inflate(R.layout.number_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("kek", "onActivityCreated NumberFragment");
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            number = savedInstanceState.getInt(NUMBER_TO_SHOW);
        }

        View view = getView();

        assert view != null;
        TextView textView = view.findViewById(R.id.text);

        if (number % 2 == 0) {
            textView.setTextColor(getResources().getColor(R.color.red));
        } else {
            textView.setTextColor(getResources().getColor(R.color.blue));
        }

        textView.setText(String.valueOf(number));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("kek", "onSaveInstanceState NumberFragment");
        super.onSaveInstanceState(outState);

        outState.putInt(NUMBER_TO_SHOW, number);
    }
}