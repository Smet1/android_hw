package ru.mail.park.rk1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null) {
            number = arguments.getInt(NUMBER_TO_SHOW);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.number_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.text);

        if (number % 2 == 0) {
            textView.setTextColor(getResources().getColor(R.color.red));
        } else {
            textView.setTextColor(getResources().getColor(R.color.blue));
        }

        textView.setText(String.valueOf(number));
    }
}