package de.ludetis.firstapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.ludetis.firstapp.data.BankCard;
import de.ludetis.firstapp.data.Card;
import de.ludetis.firstapp.data.NeBankCard;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.BankCardViewHolder> {

    private final IBankCardsManager bankCardList;
    private final DetailFragmentHelper detailFragmentHelper;

    public MyRecyclerViewAdapter(IBankCardsManager bankCardList, DetailFragmentHelper detailFragmentHelper) {
        this.bankCardList = bankCardList;
        this.detailFragmentHelper = detailFragmentHelper;
    }

    @NonNull
    @Override
    public BankCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_card, parent, false);
        BankCardViewHolder bankCardViewHolder = new BankCardViewHolder(card, viewType);
        return bankCardViewHolder;
    }

    @Override
    public int getItemViewType(int position) {

        Card card = bankCardList.get(position);

        if (card instanceof BankCard) {
            return 0;
        }

        if (card instanceof NeBankCard) {
            return 1;
        }

        return  -1;
    }

    @Override
    public void onBindViewHolder(@NonNull BankCardViewHolder holder, int position) {
        holder.bind(bankCardList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return bankCardList.size();
    }

    public class BankCardViewHolder extends RecyclerView.ViewHolder {

        private TextView ownerName;
        private TextView num;
        private TextView amount;
        private TextView date;
        private TextView pin;
        private View itemViewMain;

        public BankCardViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            if (viewType == 0) {
                itemView.setBackgroundColor(Color.GREEN);
            } else if (viewType == 1) {
                itemView.setBackgroundColor(Color.BLUE);
            }
            itemViewMain = itemView;
            ownerName = itemView.findViewById(R.id.bank_card_name_tv);
            num = itemView.findViewById(R.id.bank_card_number_tv);
            amount = itemView.findViewById(R.id.bank_card_sum_tv);
            date = itemView.findViewById(R.id.bank_card_date_tv);
            pin = itemView.findViewById(R.id.bank_card_pin_tv);
        }

        public void bind(Card card, int position) {
            ownerName.setText(card.getOwnerName());
            num.setText(card.getNum());
            amount.setText(String.valueOf(card.getAmount()));
            date.setText(card.getDate());
            pin.setText(String.valueOf(card.getPin()));

            View.OnClickListener o = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailFragmentHelper.showCard(position);
                }
            };

            itemViewMain.setOnClickListener(o);
        }
    }
}
