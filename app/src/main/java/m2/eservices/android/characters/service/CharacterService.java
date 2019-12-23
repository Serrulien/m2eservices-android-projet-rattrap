package m2.eservices.android.characters.service;

import io.reactivex.Single;
import m2.eservices.android.characters.api.model.Character;
import m2.eservices.android.characters.api.model.ResponseApi;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterService {

    @GET("character")
    Single<ResponseApi> getCharacters(
      @Query("page") int pageIndex
    );

    @GET("character/{charId}")
    Single<Character> getCharacter(@Path("charId") int charId);

}
