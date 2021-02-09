package de.ludetis.firstapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyServece extends Service {

    String TAG = MyServece.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate()");
    }

    public void doSomeWork(String zadanie, SomeWorkCallback someWorkCallback) {
        new Thread() {
            @Override
            public void run() {

                Log.d(TAG, "Working with... " + zadanie);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                someWorkCallback.onWorkComplited("Тяжелая работа в сервисе выполнена успешно!");

            }
        }.run();
    }

    public class MyServiceBinder extends Binder {
        public MyServece getService() {
            return MyServece.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand()");

        new Thread() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stopSelf();
            }
        }.run();

        return START_STICKY;
    }

    public interface SomeWorkCallback {
        void onWorkComplited(String status);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy()");
    }
}
