package de.ludetis.firstapp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import de.ludetis.firstapp.services.MyServece;

public class MainActivity extends AppCompatActivity implements DetailFragmentHelper {

    private RecyclerView myRecyclerView;
    private MyServece service;

    ServiceConnection myServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = ((MyServece.MyServiceBinder) binder).getService();
            workWithService(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private String TAG = MainActivity.class.getSimpleName();

    private void workWithService(MyServece service) {
        Log.d(TAG, "workWithService()");

        service.doSomeWork("https://docs.google.com/presentation/d/1wU_uxZdDFbVl6pB5YUTxh2dwnke39HSs/edit#slide=id.p3", new MyServece.SomeWorkCallback(){

            @Override
            public void onWorkComplited(String status) {
                Log.d(TAG, "onWorkComplited() status = " + status);
            }
        });
    }

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

                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                startActivity(intent);

            }
        });


        Intent serviceStartIntent = new Intent(this, MyServece.class);

        bindService(serviceStartIntent, myServiceConnection, Service.BIND_AUTO_CREATE);

//        startService(serviceStartIntent);
//
//        stopService(serviceStartIntent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myServiceConnection);
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