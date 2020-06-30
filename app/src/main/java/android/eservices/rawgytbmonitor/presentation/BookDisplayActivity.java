package android.eservices.rawgytbmonitor.presentation;

import android.eservices.rawgytbmonitor.R;
import android.eservices.rawgytbmonitor.presentation.favorite.fragment.FavoriteFragment;
import android.eservices.rawgytbmonitor.presentation.search.fragment.SearchFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class BookDisplayActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.ic_subscriptions_24dp,
            R.drawable.ic_apps_24dp,
            R.drawable.ic_search_24dp,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);
        tabLayout = findViewById(R.id.tablayout);

        final FavoriteFragment youtubeFragment = FavoriteFragment.newInstance();
        final SearchFragment searchFragment = SearchFragment.newInstance();
        final FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return youtubeFragment;
                } else if (position == 1) {
                    return favoriteFragment;
                }
                return searchFragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        for(int i = 0; i < tabIcons.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(tabIcons[i]);
        }
    }


}
