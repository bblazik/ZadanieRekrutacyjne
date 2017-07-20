package bb.carddeck.Injection.Module;

import bb.carddeck.API.API;
import bb.carddeck.API.DeckOfCardsService;
import bb.carddeck.Injection.Scope.PerDataManager;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Module
public class DataManagerModule {

    public DataManagerModule(){

    }

    @Provides
    @PerDataManager
    DeckOfCardsService provideDeckOfCardsService(){
        return new API().getClient();
    }

    @Provides
    @PerDataManager
    Scheduler provideSubscribeScheduler() {
        return Schedulers.io();
    }
}
