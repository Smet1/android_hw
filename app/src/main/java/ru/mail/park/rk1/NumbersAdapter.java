package ru.mail.park.rk1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersRecyclerViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<Integer> data;

    private final OnItemClickListener<Integer> onItemClickListener;


    NumbersAdapter(Context context, OnItemClickListener<Integer> onItemClickListener) {
        layoutInflater = LayoutInflater.from(context);

        this.data = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NumbersRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NumbersRecyclerViewHolder(layoutInflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(NumbersRecyclerViewHolder holder, int position) {
        int red = holder.itemView.getResources().getColor(R.color.red);
        int blue = holder.itemView.getResources().getColor(R.color.blue);
        holder.bind(data.get(position), this.onItemClickListener);
        holder.number.setTextColor(position % 2 == 1 ? red : blue);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void add(Integer newData) {
        data.add(newData);
        notifyItemInserted(data.size() - 1);
    }

    final static class NumbersRecyclerViewHolder extends RecyclerView.ViewHolder {
        private final TextView number;


        NumbersRecyclerViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.num);
        }

        void bind(final Integer i, OnItemClickListener<Integer> onItemClickListener) {
            number.setText(String.valueOf(i));

            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(i));
        }
    }
}
