package m2.eservices.android.characters;

import android.app.Application;

public class App extends Application {

    public AppComponent appComponent = DaggerAppComponent.create();

}
