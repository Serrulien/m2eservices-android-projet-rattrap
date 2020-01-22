package m2.eservices.android.characters.ui.ui.character;

import io.reactivex.Single;

public interface CharacterContract {

    interface View {

    }

    interface Presenter {
        void attachView(View view);
        void detachView(View view);
        Single<CharacterViewModel> getCharacter(int id);
        Single<CharacterEpisodesViewModel> getEpisodes(int id);
    }

}
