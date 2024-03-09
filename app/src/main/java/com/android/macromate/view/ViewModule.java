package com.android.macromate.view;

import com.android.macromate.view.nav_tabs.HistoryPageFragment;
import com.android.macromate.view.nav_tabs.ListsPageFragment;
import com.android.macromate.view.nav_tabs.MapsPageFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ViewModule {

    @Provides
    @Singleton
    HistoryPageFragment historyPageFragment() {
        return new HistoryPageFragment();
    }

    @Provides
    @Singleton
    ListsPageFragment listsPageFragment() {
        return new ListsPageFragment();
    }

    @Provides
    @Singleton
    MapsPageFragment mapsPageFragment() {
        return new MapsPageFragment();
    }

}
