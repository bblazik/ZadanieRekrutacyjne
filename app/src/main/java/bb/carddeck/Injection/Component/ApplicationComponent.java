package bb.carddeck.Injection.Component;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import bb.carddeck.API.DataManager;
import bb.carddeck.Activity.DeckDashboard;
import bb.carddeck.Injection.Module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DeckDashboard deckActivity);

    Application application();
    DataManager dataManager();
}
