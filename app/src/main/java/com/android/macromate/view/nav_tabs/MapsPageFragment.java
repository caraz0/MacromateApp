package com.android.macromate.view.nav_tabs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.macromate.R;
import com.android.macromate.integration.service.ILocationService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class MapsPageFragment extends Fragment implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Inject
    ILocationService locationService;

    @Inject
    ExecutorService executor;

    public MapsPageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // Field injection is done here

        var mapFragment = new SupportMapFragment();
        getChildFragmentManager().beginTransaction()
                .add(R.id.my_map, mapFragment)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        var rootView = inflater.inflate(R.layout.fragment_maps_page, container, false);

        getChildFragmentManager().executePendingTransactions();
        var mapFragmentaux = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.my_map);

        mapFragmentaux.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        centerMap(googleMap);
        configureMapSettings(googleMap);

        executor.submit(() -> addShopsLocationMarkersToMap(googleMap));
    }

    private void centerMap(@NonNull GoogleMap googleMap) {
        LatLng sydney = new LatLng(40.43, -3.69);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
    }

    private void configureMapSettings(@NonNull GoogleMap googleMap) {
        UiSettings settings = googleMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        settings.setCompassEnabled(true);

        tryPhoneLocationButton(googleMap);
    }

    private void addShopsLocationMarkersToMap(@NonNull GoogleMap googleMap) {
        var locations = locationService.getAllLocations();
        getActivity().runOnUiThread(() -> {
            for (var location : locations)
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title(location.getName()));

        });
    }

    private void tryPhoneLocationButton(GoogleMap googleMap) {
        if (!checkLocationPermission()) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        googleMap.setMyLocationEnabled(true);
    }

    private boolean checkLocationPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }

}