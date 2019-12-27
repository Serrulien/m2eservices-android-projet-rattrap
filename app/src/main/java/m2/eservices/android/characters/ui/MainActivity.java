package m2.eservices.android.characters.ui;

import android.os.Bundle;

import com.example.android_m2_eservices_projet_etu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

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

    private boolean gridDisplayed = false;
    private CharacterListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       ((App) getApplicationContext()).getAppComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.character_recycler_view);
        this.setLinear(recyclerView);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_change_layout) {
            swapLayout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void swapLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.character_recycler_view);
        if (!gridDisplayed) {
            setGrid(recyclerView);
        } else {
            setLinear(recyclerView);
        }
        gridDisplayed = !gridDisplayed;
    }

    private void setLinear(RecyclerView recyclerView) {
        LinearLayout ll = findViewById(R.id.inside_item_layout);
        //ll.setOrientation(LinearLayout.HORIZONTAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setGrid(RecyclerView recyclerView) {
        LinearLayout ll = findViewById(R.id.inside_item_layout);
        ll.setOrientation(LinearLayout.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }

}
