package m2.eservices.android.characters.ui.main;

import android.os.Bundle;

import com.example.android_m2_eservices_projet_etu.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import m2.eservices.android.characters.App;
import m2.eservices.android.characters.api.CharacterDisplayDataRepository;

public class MainActivity extends AppCompatActivity implements CharacterListContract.View {

    @Inject
    CharacterDisplayDataRepository repoCharacter;

    private CharacterListPresenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CharacterAdapter characterAdapter;

    private static final String BUNDLE_LAYOUT_KEY = "layoutManager";
    private static final String BUNDLE_LAYOUT_TYPE_KEY = "layoutManagerType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       ((App) getApplicationContext()).getAppComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if ( savedInstanceState != null ) {
            this.createRecyclerView(savedInstanceState.getInt(BUNDLE_LAYOUT_TYPE_KEY));
            this.layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(BUNDLE_LAYOUT_KEY));
        } else {
            this.createRecyclerView(0);
        }

        this.presenter = new CharacterListPresenter(this.repoCharacter);
        presenter.attachView(this);

        this.characterAdapter = new CharacterAdapter(this, presenter);
        recyclerView.setAdapter(this.characterAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BUNDLE_LAYOUT_KEY, layoutManager.onSaveInstanceState());
        outState.putInt(BUNDLE_LAYOUT_TYPE_KEY, this.layoutToId(this.layoutManager));
        super.onSaveInstanceState(outState);
    }

    private int layoutToId(RecyclerView.LayoutManager layout) {
        if (layoutManager instanceof GridLayoutManager) {
            return 1;
        } else {
            return 0;
        }
    }

    private RecyclerView.LayoutManager idToLayout(int id) {
        if (id == 0) {
            return new LinearLayoutManager(this);
        } else {
            return new GridLayoutManager(this, 2);
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

    private void createRecyclerView(int id) {
        this.recyclerView = findViewById(R.id.character_recycler_view);
        this.layoutManager = this.idToLayout(id);
        this.recyclerView.setLayoutManager(layoutManager);
    }

    private void swapRecyclerViewLayout() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("save", this.layoutManager.onSaveInstanceState());
        if (layoutManager instanceof GridLayoutManager) {
            this.layoutManager = new LinearLayoutManager(this);
        } else if (layoutManager instanceof LinearLayoutManager) {
            this.layoutManager = new GridLayoutManager(this, 2);
        } else {
            throw new RuntimeException("layout not supported");
        }
        this.layoutManager.onRestoreInstanceState(bundle.getParcelable("save"));
        this.recyclerView.setLayoutManager(this.layoutManager);
    }

}
