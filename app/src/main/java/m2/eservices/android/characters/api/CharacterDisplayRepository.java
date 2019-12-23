package m2.eservices.android.characters.api;

import java.util.List;

import io.reactivex.Observable;
import m2.eservices.android.characters.api.model.Character;

public interface CharacterDisplayRepository {

    Observable<List<Character>> getCharacters();

}
