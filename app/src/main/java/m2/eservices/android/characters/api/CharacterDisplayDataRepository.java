package m2.eservices.android.characters.api;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import m2.eservices.android.characters.api.model.Character;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository {

    private CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource;

    @Inject
    public CharacterDisplayDataRepository(
        CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource
    ) {
        this.characterDisplayRemoteDataSource = characterDisplayRemoteDataSource;
    }

    @Override
    public Observable<List<Character>> getCharacters() {
        return  null;
    }

}
