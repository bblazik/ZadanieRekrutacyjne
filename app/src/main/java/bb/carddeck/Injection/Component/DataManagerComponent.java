package bb.carddeck.Injection.Component;


import bb.carddeck.API.DataManager;
import bb.carddeck.Injection.Module.DataManagerModule;
import bb.carddeck.Injection.Scope.PerDataManager;
import dagger.Component;

@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = DataManagerModule.class)
public interface DataManagerComponent{
    
    void inject (DataManager dataManager);
}

