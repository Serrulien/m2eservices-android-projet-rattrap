package m2.eservices.android.characters;

import android.app.Application;

import m2.eservices.android.characters.api.WebModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .webModule(new WebModule("https://rickandmortyapi.com/api/"))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
