package de.ludetis.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.ludetis.firstapp.data.Card;

public class DetailFragment extends Fragment {

    private Card card;

    public DetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt(DetailActivity.KEY_POSITION);
        card = BankCardsManager.getInstance().get(position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_fragment_layout, container, false);

        updateCard(v);

        BankCardsManager.getInstance().addOnCardWasChangedListener(new BankCardsManager.OnCardWasChanged() {
            @Override
            public void dataWasChanged() {
                updateCard(getView());
            }
        });

        return v;
    }

    private void updateCard(View v) {
        ((EditText) v.findViewById(R.id.editTextName)).setText(card.getOwnerName());
        ((EditText) v.findViewById(R.id.editTextNumber)).setText(card.getNum());
        ((EditText) v.findViewById(R.id.editTextSum)).setText("" + card.getAmount());
        ((EditText) v.findViewById(R.id.editTextDate)).setText(card.getDate());
        ((EditText) v.findViewById(R.id.editTextPin)).setText("" + card.getPin());
    }
}
