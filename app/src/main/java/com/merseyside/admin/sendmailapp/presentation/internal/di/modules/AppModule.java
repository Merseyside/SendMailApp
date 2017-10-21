package com.merseyside.admin.sendmailapp.presentation.internal.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.merseyside.admin.sendmailapp.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivan_ on 09.10.2017.
 */

@Module
public class AppModule {
    private Context context;
    public AppModule(@NonNull Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator(context);
    }

}
