package m2.eservices.android.characters.ui.ui.character;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.android_m2_eservices_projet_etu.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private CharacterPresenter characterPresenter;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1,
            R.string.tab_text_2
    };
    private final Context mContext;
    private final int charId;

    public SectionsPagerAdapter(
            Context context,
            FragmentManager fm,
            CharacterPresenter presenter,
            int charId
    ) {
        super(fm);
        mContext = context;
        this.charId = charId;
        this.characterPresenter = presenter;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return CharacterInfoFragment.newInstance(
                this.characterPresenter.getCharacter(charId).blockingGet()
            );
        } else {
            return CharacterEpisondesFragment.newInstance(
                this.characterPresenter.getEpisodes(charId).blockingGet()
            );
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}