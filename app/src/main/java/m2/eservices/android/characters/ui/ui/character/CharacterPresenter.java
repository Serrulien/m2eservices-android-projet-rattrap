package m2.eservices.android.characters.ui.ui.character;


import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import m2.eservices.android.characters.api.CharacterDisplayDataRepository;
import m2.eservices.android.characters.api.model.Character;
import m2.eservices.android.characters.ui.main.CharacterToCharacterListItemMapper;
import m2.eservices.android.characters.ui.main.CharacterListItemViewModel;

public class CharacterPresenter implements CharacterContract.Presenter {

    private CharacterContract.View view;
    private CharacterDisplayDataRepository repository;

    @Inject
    public CharacterPresenter(CharacterDisplayDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void attachView(CharacterContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView(CharacterContract.View view) {
        this.view = null;
    }

    @Override
    public Single<CharacterViewModel> getCharacter(int id) {
        return this.repository.getById(id).map(
                new Function<Character, CharacterViewModel>() {
                    @Override
                    public CharacterViewModel apply(Character character) throws Exception {
                        return CharacterToCharacterViewModelMapper.map(character);
                    }
                }
        );
    }

    @Override
    public Single<CharacterEpisodesViewModel> getEpisodes(int id) {
        return this.repository.getById(id).map(
                new Function<Character, CharacterEpisodesViewModel>() {
                    @Override
                    public CharacterEpisodesViewModel apply(Character character) throws Exception {
                        return CharacterToCharacterEpisodesViewModelMapper.map(character);
                    }
                }
        );
    }
}
