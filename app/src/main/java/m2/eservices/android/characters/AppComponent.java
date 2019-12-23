package m2.eservices.android.characters;

import javax.inject.Singleton;

import dagger.Component;
import m2.eservices.android.characters.api.WebModule;
import m2.eservices.android.characters.ui.MainActivity;

@Singleton
@Component(modules = {
        AppModule.class,
        WebModule.class
})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
