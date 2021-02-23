package de.ludetis.firstapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.ludetis.firstapp.data.BankCard;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.BankCardViewHolder> {

    private final IBankCardsManager bankCardList;
    private List<BankCard> bankCards = new ArrayList<>();
    private final DetailFragmentHelper detailFragmentHelper;

    public MyRecyclerViewAdapter(IBankCardsManager bankCardList, DetailFragmentHelper detailFragmentHelper) {
        this.bankCardList = bankCardList;
        this.detailFragmentHelper = detailFragmentHelper;

        bankCardList.getAll(cards -> {
            bankCards.addAll(cards);
        });
    }

    @NonNull
    @Override
    public BankCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_card, parent, false);
        BankCardViewHolder bankCardViewHolder = new BankCardViewHolder(card, viewType);
        return bankCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BankCardViewHolder holder, int position) {
        holder.bind(bankCards.get(position), position);
    }

    @Override
    public int getItemCount() {
        return bankCards.size();
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

        public void bind(BankCard card, int position) {
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
