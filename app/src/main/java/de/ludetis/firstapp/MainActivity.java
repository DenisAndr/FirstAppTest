package de.ludetis.firstapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import de.ludetis.firstapp.broadcasts.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity implements DetailFragmentHelper {

    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        myRecyclerView = findViewById(R.id.myRecyclerVoew);

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(BankCardsManager.getInstance(), this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        BankCardsManager.getInstance().addOnCardWasChangedListener(new BankCardsManager.OnCardWasChanged() {
            @Override
            public void dataWasChanged() {
                myRecyclerViewAdapter.notifyDataSetChanged();
            }
        });


        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
//                startActivity(intent);

                Intent intent = new Intent();
                intent.setAction("MyBroadcastReceiverAction");
                intent.putExtra("data", "Nothing to see here, move along.");

                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);

            }
        });

        registerReceiver(new MyBroadcastReceiver(), new IntentFilter("MyBroadcastReceiverAction"));



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showCard(int position) {
        if (findViewById(R.id.detailActivityRoot) == null) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.KEY_POSITION, position);
            startActivity(intent);
        } else {
            ViewPager2 viewPager = findViewById(R.id.viewPager);
            viewPager.setAdapter(new ViewPagerFragmentAdapter(this, position));
            TabLayout tabLayout = findViewById(R.id.tabLayout);
            TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int tabPosition) {
                    switch (tabPosition) {
                        case 0 :
                            tab.setText("Инфо");
                            break;
                        case 1 :
                            tab.setText("Редактирывание");
                            break;

                    }
                }
            });
            tabLayoutMediator.attach();
        }
    }
}

interface DetailFragmentHelper {
    void showCard(int position);
}