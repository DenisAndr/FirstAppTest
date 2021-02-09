package de.ludetis.firstapp.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import de.ludetis.firstapp.MainActivity;
import de.ludetis.firstapp.R;

public class MyServece extends Service {

    String TAG = MyServece.class.getSimpleName();
    private String CHANNEL_ID = "MyServece CHANNEL_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        initForegroundService();

        Log.d(TAG, "onCreate()");
    }

    private void initForegroundService() {

        createNotificationChannel();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 0, notificationIntent, 0);

            Notification notification =
                    new Notification.Builder(this, CHANNEL_ID)
                            .setContentTitle("Title1")
                            .setContentText("Text1")
                            .setSmallIcon(R.drawable.ic_icon_24)
                            .setContentIntent(pendingIntent)
                            .build();

            // Notification ID cannot be 0.
            startForeground(1, notification);
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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
