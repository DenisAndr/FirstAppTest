package de.ludetis.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import de.ludetis.firstapp.data.Contact;

public class MainActivity extends AppCompatActivity {

    Map<String, Contact> contactBook = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);




        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("hello", "hello value");
                startActivityForResult(intent, 123);
            }
        });

        Button button = findViewById(R.id.button2);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextName = findViewById(R.id.editTextName);
                EditText editTextNumber = findViewById(R.id.editTextNumber);

                String name = editTextName.getText().toString();
                String number = editTextNumber.getText().toString();

                if (name.isEmpty() || number.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Имя или номер телефона пустые.", Toast.LENGTH_LONG).show();

                } else {
                    Contact contact = new Contact();
                    contact.setName(name);
                    contact.setNumber(number);
                    contactBook.put(name, contact);
                    Toast.makeText(MainActivity.this, "Добавлен контакт с именем " + name, Toast.LENGTH_LONG).show();
                }

            }
        };

        button.setOnClickListener(onClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 123) {
                String name = data.getExtras().getString(MainActivity2.NAME_INTENT_KEY);
                Toast.makeText(MainActivity.this, "Получено имя " + name, Toast.LENGTH_LONG).show();
            } else if (requestCode == 124) {

            }
        }

    }



}