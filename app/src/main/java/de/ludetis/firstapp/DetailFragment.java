package de.ludetis.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import de.ludetis.firstapp.data.BankCard;

public class DetailFragment extends Fragment {

    @Inject
    BankCardsManager bankCardsManager;
    private int position;

    public DetailFragment() {
        MyApp.getBankCardManagerComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(DetailActivity.KEY_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_fragment_layout, container, false);

        updateCard(v);

        bankCardsManager.addOnCardWasChangedListener(new BankCardsManager.OnCardWasChanged() {
            @Override
            public void dataWasChanged() {
                updateCard(getView());
            }
        });

        return v;
    }

    private void updateCard(View v) {

        if (v == null) {
            return;
        }

        bankCardsManager.get(position, card -> {
            if (card != null) {
                ((EditText) v.findViewById(R.id.editTextName)).setText(card.getOwnerName());
                ((EditText) v.findViewById(R.id.editTextNumber)).setText(card.getNum());
                ((EditText) v.findViewById(R.id.editTextSum)).setText("" + card.getAmount());
                ((EditText) v.findViewById(R.id.editTextDate)).setText(card.getDate());
                ((EditText) v.findViewById(R.id.editTextPin)).setText("" + card.getPin());
            }
        });
    }
}
