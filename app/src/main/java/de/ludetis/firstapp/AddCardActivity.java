package de.ludetis.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.ludetis.firstapp.data.BankCard;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        Button createCard = findViewById(R.id.buttonAddCard);
        createCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = ((EditText) findViewById(R.id.editTextName)).getText().toString();
                String num = ((EditText) findViewById(R.id.editTextNumber)).getText().toString();
                String sum = ((EditText) findViewById(R.id.editTextSum)).getText().toString();
                String date = ((EditText) findViewById(R.id.editTextDate)).getText().toString();
                String pin = ((EditText) findViewById(R.id.editTextPin)).getText().toString();

                if (name.isEmpty() || num.isEmpty() || sum.isEmpty() || date.isEmpty() || pin.isEmpty()) {
                    Toast.makeText(AddCardActivity.this, "Не все поля заполнены.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    BankCardsManager.getInstance().add(new BankCard(name, num, Float.parseFloat(sum), date, Integer.parseInt(pin)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(AddCardActivity.this, "Неправильно заполнены поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(AddCardActivity.this, "Карта успешно создана", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}