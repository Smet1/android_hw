package ru.mail.park.rk1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

public class RecyclerFragment extends Fragment {
    private final static String KEY = "kek";
    private int last;
    private final static String NUMBERS = "numbers";
    private RecyclerView numbers;


    public static RecyclerFragment newInstance(int i) {
        RecyclerFragment myFragment = new RecyclerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY, i);
        myFragment.setArguments(bundle);

        myFragment.last = i;

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_fragment, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("kek", "onSaveInstanceState RecyclerFragment");
        super.onSaveInstanceState(outState);

        outState.putInt(NUMBERS, last);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("kek", "onActivityCreated RecyclerFragment");

        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            last = savedInstanceState.getInt(NUMBERS);
        }

        View view = getView();

        assert view != null;
        final Button button = view.findViewById(R.id.add_number);
        button.setOnClickListener(v -> {
            NumbersAdapter adapter = (NumbersAdapter) numbers.getAdapter();

            if (adapter != null) {
                int newLen = adapter.getItemCount() + 1;
                adapter.add(newLen);
                last = newLen;
            }
        });

        NumbersAdapter numbersAdapter = new NumbersAdapter(getContext(), this::onItemClick);

        numbers = view.findViewById(R.id.numbers_list);
        numbers.setLayoutManager(new GridLayoutManager(getContext(),
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3));
        numbers.setAdapter(numbersAdapter);


        for (Integer i = 1; i <= last; i++) {
            numbersAdapter.add(i);
        }
    }

    private void onItemClick(Integer i) {
        Objects.requireNonNull(getFragmentManager()).beginTransaction()
                .replace(R.id.container, NumberFragment.newInstance(i))
                .addToBackStack(null)
                .commit();
    }
}