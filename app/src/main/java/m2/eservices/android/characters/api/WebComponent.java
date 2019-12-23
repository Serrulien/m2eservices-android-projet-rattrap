package m2.eservices.android.characters.api;

import javax.inject.Singleton;

import dagger.Component;
import m2.eservices.android.characters.AppModule;
import m2.eservices.android.characters.ui.MainActivity;

@Singleton
@Component(modules = {AppModule.class, WebModule.class})
public interface WebComponent {
    void inject(MainActivity activity);
}
