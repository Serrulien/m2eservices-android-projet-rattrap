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
    CharacterDisplayDataRepository repoCharacter;

    private CharacterListPresenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CharacterAdapter characterAdapter;

    private static final String BUNDLE_LAYOUT_KEY = "layoutManager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       ((App) getApplicationContext()).getAppComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.createRecyclerView();

        if ( savedInstanceState != null ) {
            this.layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(BUNDLE_LAYOUT_KEY));
        }

        this.presenter = new CharacterListPresenter(this.repoCharacter);
        presenter.attachView(this);

        this.characterAdapter = new CharacterAdapter(presenter);
        recyclerView.setAdapter(this.characterAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position", this.getCurrentItemPosition());
        outState.putParcelable(BUNDLE_LAYOUT_KEY, layoutManager.onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    private int getCurrentItemPosition() {
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else {
            throw new RuntimeException("layout not supported");
        }
    }

    private void restoreItem(int position) {
        if (layoutManager instanceof GridLayoutManager) {
            layoutManager.scrollToPosition(position);
        } else if (layoutManager instanceof LinearLayoutManager) {
            layoutManager.scrollToPosition(position);
        } else {
            throw new RuntimeException("layout not supported");
        }
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
            this.swapRecyclerViewLayout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createRecyclerView() {
        this.recyclerView = findViewById(R.id.character_recycler_view);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);
    }

    private void swapRecyclerViewLayout() {
        if (layoutManager instanceof GridLayoutManager) {
            this.layoutManager = new LinearLayoutManager(this);
        } else if (layoutManager instanceof LinearLayoutManager) {
            this.layoutManager = new GridLayoutManager(this, 2);
        } else {
            throw new RuntimeException("layout not supported");
        }
        this.recyclerView.setLayoutManager(this.layoutManager);
    }

}
