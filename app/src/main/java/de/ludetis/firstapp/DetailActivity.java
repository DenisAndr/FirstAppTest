package de.ludetis.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import de.ludetis.firstapp.data.Card;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_POSITION = "KEY_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int position = getIntent().getExtras().getInt(KEY_POSITION);

        Card card = BankCardsManager.getInstance().get(position);

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.detailActivityRoot, detailFragment).commit();


    }

}