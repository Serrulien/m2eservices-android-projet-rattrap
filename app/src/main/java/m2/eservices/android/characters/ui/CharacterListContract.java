package m2.eservices.android.characters.ui;

import io.reactivex.Single;
import m2.eservices.android.characters.api.model.Character;

public interface CharacterListContract {

    interface View {

    }

    interface Presenter {
        void attachView(View view);
        void detachView(View view);
        Single<CharacterViewModel> getCharacter(int id);
        int getItemCount();
    }

}
