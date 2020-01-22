package m2.eservices.android.characters.ui.ui.character;

import android.os.Bundle;
import com.example.android_m2_eservices_projet_etu.R;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import javax.inject.Inject;
import m2.eservices.android.characters.App;
import m2.eservices.android.characters.api.CharacterDisplayDataRepository;

public class CharacterActivity extends AppCompatActivity {

    @Inject
    CharacterDisplayDataRepository repoCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((App) getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        setTitle("");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(
                this,
                getSupportFragmentManager(),
                new CharacterPresenter(this.repoCharacter),
                getIntent().getIntExtra("charId", 0)
        );
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}