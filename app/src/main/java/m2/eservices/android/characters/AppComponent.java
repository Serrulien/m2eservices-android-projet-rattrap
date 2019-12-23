package m2.eservices.android.characters;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import m2.eservices.android.characters.ui.MainActivity;

@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class
})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
