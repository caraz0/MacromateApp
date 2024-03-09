package com.android.macromate.core.services;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.macromate.MainActivity;
import com.android.macromate.R;
import com.android.macromate.dtos.integration.LocationDTO;
import com.android.macromate.integration.service.ILocationService;
import com.android.macromate.view.nav_tabs.MapsPageFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationNotificationService extends Service implements LocationListener {

    private LocationManager locationManager;
    private final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    private final long MIN_TIME = 10000; // 10 second
    private final float MIN_DISTANCE = 500; // 500 meters

    @Inject
    ILocationService locationService;

    private List<LocationDTO> locations;

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            this.locations = locationService.getAllLocations();
        }).start();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (meetsPermissions())
            locationManager.requestLocationUpdates(LOCATION_PROVIDER,
                    MIN_TIME, MIN_DISTANCE, this);

        return START_STICKY;
    }

    @Override
    public void onLocationChanged(Location location) {
        for (LocationDTO locationDTO : locations) {

            float[] results = new float[1];
            Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                    locationDTO.getLatitude(), locationDTO.getLongitude(), results);

            float distanceInMeters = results[0];
            if (distanceInMeters < MIN_DISTANCE) {
                sendNotification();
            }
        }
    }

    private void sendNotification() {
        NotificationManager notificationManager;

        // crea canal de notificaciones
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.getApplicationContext(), "com.uc3m.it.helloserviceappmov.notify_001");

        //pendingIntent para abrir la actividad cuando se pulse la notificación
        Intent ii = new Intent(this.getApplicationContext(), MapsPageFragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, ii, PendingIntent.FLAG_IMMUTABLE);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.favicon);
        mBuilder.setContentTitle("Tienes una tienda cerca!");
        mBuilder.setContentText("Pincha para ver cual es!");

        notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "YOUR_CHANNEL_ID";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Canal de HelloServiceAppMov",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        notificationManager.notify(0, mBuilder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private boolean meetsPermissions() {
        boolean locationPerms = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        boolean notificationsPerms = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;

        return locationPerms && notificationsPerms;
    }

}
