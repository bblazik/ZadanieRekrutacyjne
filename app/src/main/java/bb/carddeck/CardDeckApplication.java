package bb.carddeck;


import android.app.Application;
import android.content.Context;

import bb.carddeck.Injection.Component.ApplicationComponent;
import bb.carddeck.Injection.Component.DaggerApplicationComponent;
import bb.carddeck.Injection.Module.ApplicationModule;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class CardDeckApplication extends Application{

    ApplicationComponent mApplicationComponent;
    private Scheduler scheduler;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        context = getApplicationContext();
    }

    public static CardDeckApplication get() {
        return (CardDeckApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }
}
