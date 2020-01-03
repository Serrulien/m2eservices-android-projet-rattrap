package m2.eservices.android.characters.api;

import javax.inject.Inject;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import m2.eservices.android.characters.api.model.Character;
import m2.eservices.android.characters.api.model.ResponseApi;

public class CharacterDisplayDataRepository implements CharacterDisplayRepository {

    private CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource;
    private int characterCount = -1;

    @Inject
    public CharacterDisplayDataRepository(
        CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource
    ) {
        this.characterDisplayRemoteDataSource = characterDisplayRemoteDataSource;
    }

    @Override
    public Single<Character> getById(int charId) {
        return this.characterDisplayRemoteDataSource.getById(charId);
    }

    @Override
    public Single<Integer> getCharacterCount() {
        if ( this.characterCount == -1 ) {
            return this.characterDisplayRemoteDataSource.getOnePageRow(0)
                .map(
                    new Function<ResponseApi, Integer>() {
                        @Override
                        public Integer apply(ResponseApi responseApi) throws Exception {
                            characterCount = responseApi.getInfo().getCount();
                            return characterCount;
                        }
                    }
                );
        } else {
            return Single.create(
                new SingleOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                        emitter.onSuccess(characterCount);
                    }
                }
            );
        }
    }
}
