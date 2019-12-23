package m2.eservices.android.characters.api;

import io.reactivex.Single;
import m2.eservices.android.characters.api.model.Character;

public interface CharacterDisplayRepository {

    Single<Character> getById(int charId);
    Single<Integer> getCharacterCount();

}
