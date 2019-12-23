package m2.eservices.android.characters.api;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import m2.eservices.android.characters.api.model.Character;
import m2.eservices.android.characters.api.model.ResponseApi;
import m2.eservices.android.characters.service.CharacterService;

class CharacterDisplayRemoteDataSource {

    @Inject CharacterService servCharacter;

    @Inject
    public CharacterDisplayRemoteDataSource(
            CharacterService servCharacter
    ) {
        this.servCharacter = servCharacter;
    }

    public Single<ResponseApi> getOnePageRow(int pageIndex) {
        return this.servCharacter.getCharacters(pageIndex);
    }

    public Single<Character> getById(int charId) {
        return this.servCharacter.getCharacter(charId);
    }

}
