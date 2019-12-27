package m2.eservices.android.characters.ui;

import android.view.View;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import m2.eservices.android.characters.api.CharacterDisplayDataRepository;
import m2.eservices.android.characters.api.model.Character;

public class CharacterListPresenter implements CharacterListContract.Presenter {

    private CharacterListContract.View view;
    private CharacterDisplayDataRepository repository;

    @Inject
    public CharacterListPresenter(CharacterDisplayDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getItemCount() {
        return 300;
        /*
        // Ne marche pas :(
        return this.repository.getCharacterCount()
                .subscribeOn(Schedulers.io())
                .blockingGet();
         */
    }

    @Override
    public void attachView(CharacterListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView(CharacterListContract.View view) {
        this.view = null;
    }

    @Override
    public Single<CharacterViewModel> getCharacter(int id) {
        return this.repository.getById(id).map(
                new Function<Character, CharacterViewModel>() {
                    @Override
                    public CharacterViewModel apply(Character character) throws Exception {
                        return CharacterMapper.map(character);
                    }
                }
        );
    }
}
