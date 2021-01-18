package de.ludetis.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ludetis.firstapp.data.BankCard;
import de.ludetis.firstapp.data.Card;
import de.ludetis.firstapp.data.NeBankCard;

public class MainActivity extends AppCompatActivity {

    private List<Card> bankCardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        bankCardList.add(new BankCard("Danya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Vanya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Olya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Kira", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Misha", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Masyanya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Mikhalich", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Lesha", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Vitalik", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Danya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Vanya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Olya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Kira", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Misha", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Masyanya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Mikhalich", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Lesha", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Vitalik", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Danya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Vanya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Olya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Kira", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Misha", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Masyanya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Mikhalich", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Lesha", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Vitalik", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Danya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Vanya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Olya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Kira", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Misha", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Masyanya", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Mikhalich", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new BankCard("Lesha", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));
        bankCardList.add(new NeBankCard("Vitalik", "1234 5678 9012 4578", 1000f, "21.02.2017", 1234));

        RecyclerView myRecyclerView = findViewById(R.id.myRecyclerVoew);

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(bankCardList);

        myRecyclerView.setAdapter(myRecyclerViewAdapter);

        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }




}