package de.ludetis.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    public static final String NAME_INTENT_KEY = "NAME_INTENT_KEY";

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("hello").toString();

        Log.d(TAG, "onCreate()");

        m(MainActivity2.class);
    }

    void m(Class<MainActivity2> stringClass){

        MainActivity2 o = new MainActivity3();

        if (o.getClass() == stringClass) {

        }

        if (o instanceof MainActivity2) {

        }

    }

    class MainActivity3 extends MainActivity2 {

    }


    public void onClose(View v) {

        Intent intent = new Intent();

        intent.putExtra(NAME_INTENT_KEY, "Denis");
        intent.putExtra("NUMBER", "789456123");

        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}