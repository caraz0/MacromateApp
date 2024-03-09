package com.android.macromate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.macromate.core.services.LocationNotificationService;
import com.android.macromate.data.dao.ListDAO;
import com.android.macromate.view.nav_tabs.HistoryPageFragment;
import com.android.macromate.view.nav_tabs.ListsPageFragment;
import com.android.macromate.view.nav_tabs.MapsPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    HistoryPageFragment historyPageFragment;

    @Inject
    ListsPageFragment listsPageFragment;

    @Inject
    MapsPageFragment mapsPageFragment;

    @Inject
    ListDAO list;

    private Map<Integer, Fragment> navBarFragmentsMap;

    @Inject
    void init() {
        navBarFragmentsMap = Map.of(
                R.id.historyPageFragment, historyPageFragment,
                R.id.listsPageFragment, listsPageFragment,
                R.id.mapsPageFragment, mapsPageFragment
        );
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bot_nav_bar);
        nav.setOnItemSelectedListener(this::onNavBarClickListener);

        loadFragment(navBarFragmentsMap.get(R.id.historyPageFragment));

        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.POST_NOTIFICATIONS}, 1);

        Intent serviceIntent = new Intent(this, LocationNotificationService.class);
        startService(serviceIntent);
    }

    private boolean onNavBarClickListener(MenuItem item) {
        var fragment = navBarFragmentsMap.get(item.getItemId());
        loadFragment(fragment);
        return true;
    }

    public void loadFragment(Fragment fragment) {
        var transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

}
