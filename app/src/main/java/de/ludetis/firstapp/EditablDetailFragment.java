package de.ludetis.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.ludetis.firstapp.data.Card;

public class EditablDetailFragment extends Fragment {

    private Card card;

    EditText editTextName;
    EditText editTextNumber;
    EditText editTextSum;
    EditText editTextDate;
    EditText editTextPin;
    Button saveButton;

    public EditablDetailFragment() {
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
        View v = inflater.inflate(R.layout.editable_detail_fragment_layout, container, false);
        editTextName = v.findViewById(R.id.editTextName);
        editTextNumber = v.findViewById(R.id.editTextNumber);
        editTextSum = v.findViewById(R.id.editTextSum);
        editTextDate = v.findViewById(R.id.editTextDate);
        editTextPin = v.findViewById(R.id.editTextPin);
        saveButton = v.findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setOwnerName(editTextName.getText().toString());
                card.setNum(editTextNumber.getText().toString());
                card.setAmount(Float.parseFloat(editTextSum.getText().toString()));
                card.setDate(editTextDate.getText().toString());
                card.setPin(Integer.parseInt(editTextPin.getText().toString()));

                BankCardsManager.getInstance().notifyDataWasChanged();
            }
        });

        editTextName.setText(card.getOwnerName());
        editTextNumber.setText(card.getNum());
        editTextSum.setText("" + card.getAmount());
        editTextDate.setText(card.getDate());
        editTextPin.setText("" + card.getPin());

        return v;
    }
}
