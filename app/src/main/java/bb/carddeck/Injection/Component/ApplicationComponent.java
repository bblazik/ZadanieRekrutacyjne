package bb.carddeck.Injection.Component;

import android.app.Application;

import javax.inject.Singleton;

import bb.carddeck.data.DataManager;
import bb.carddeck.view.Activity.DeckDashboard;
import bb.carddeck.Injection.Module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DeckDashboard deckActivity);

    Application application();
    DataManager dataManager();
}
