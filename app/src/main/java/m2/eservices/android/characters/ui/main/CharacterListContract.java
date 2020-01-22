package m2.eservices.android.characters.ui.main;

import io.reactivex.Single;

public interface CharacterListContract {

    interface View {

    }

    interface Presenter {
        void attachView(View view);
        void detachView(View view);
        Single<CharacterListItemViewModel> getCharacter(int id);
        int getItemCount();
    }

}
