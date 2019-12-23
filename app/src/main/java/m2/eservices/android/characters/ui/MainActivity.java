package m2.eservices.android.characters.ui;

import android.os.Bundle;

import com.example.android_m2_eservices_projet_etu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import m2.eservices.android.characters.App;
import m2.eservices.android.characters.api.CharacterDisplayDataRepository;
import m2.eservices.android.characters.api.CharacterDisplayRepository;
import m2.eservices.android.characters.api.model.Character;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements CharacterListContract.View {

    @Inject
    CharacterDisplayDataRepository test;

    private CharacterListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       ((App) getApplicationContext()).getAppComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.character_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        this.presenter = new CharacterListPresenter(this.test);
        presenter.attachView(this);

        CharacterAdapter adapter = new CharacterAdapter(presenter);
        recyclerView.setAdapter(adapter);

/*        Button button = (Button) findViewById(R.id.testButton);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        test.getById(2).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new SingleObserver<Character>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(Character character) {
                                        System.out.println(character.getCreated());
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                });
                    }
                }
        );*/
    }

}
