package de.ludetis.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.ludetis.firstapp.data.Card;

public class MainActivity extends AppCompatActivity implements DetailFragmentHelper {

    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        myRecyclerView = findViewById(R.id.myRecyclerVoew);

        boolean openInNewActivity = (findViewById(R.id.detailActivityRoot) == null);

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(BankCardsManager.getInstance(), this, openInNewActivity);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        myRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showCard(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt(DetailActivity.KEY_POSITION, position);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detailActivityRoot, detailFragment).commit();
    }
}

interface DetailFragmentHelper {
    void showCard(int position);
}