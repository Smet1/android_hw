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

import ru.mail.park.rk1.component.AppComponent;

public class RecyclerFragment extends Fragment {
    private final static String KEY = "kek";
    private final static Integer DEFAULT = 100;
    public int last = DEFAULT;
    private final static String NUMBERS = "numbers";


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
        super.onSaveInstanceState(outState);

        outState.putInt(NUMBERS, last);

        Log.d("kek", "onSaveInstanceState RecyclerFragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("kek", "onActivityCreated RecyclerFragment");

        View view = getView();

        assert view != null;
        final Button button = view.findViewById(R.id.add_number);
        button.setOnClickListener(v -> {
            RecyclerView numbers = view.findViewById(R.id.numbers_list);
            NumbersAdapter adapter = (NumbersAdapter) numbers.getAdapter();

            assert adapter != null;
            int newLen = adapter.getItemCount() + 1;
            adapter.add(newLen);
            last = newLen;
            AppComponent.getInstance().setLastNumber(last);
        });

        NumbersAdapter numbersAdapter = new NumbersAdapter(getContext(), this::onItemClick);

        RecyclerView numbers = view.findViewById(R.id.numbers_list);
        numbers.setLayoutManager(new GridLayoutManager(getContext(),
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3));
        numbers.setAdapter(numbersAdapter);


        for (Integer i = 1; i <= AppComponent.getInstance().getLastNumber(); i++) {
            numbersAdapter.add(i);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        MainActivity m = ((MainActivity) getActivity());
        if (m != null) {
            m.SetLastNumber(last);
        }
    }

    private void onItemClick(Integer i) {
        Objects.requireNonNull(getFragmentManager()).beginTransaction()
                .replace(R.id.container, NumberFragment.newInstance(i))
                .addToBackStack(null)
                .commit();
    }
}