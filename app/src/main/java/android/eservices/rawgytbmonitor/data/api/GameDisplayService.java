package android.eservices.rawgytbmonitor.data.api;

import android.eservices.rawgytbmonitor.data.api.model.Game;
import android.eservices.rawgytbmonitor.data.api.model.PaginatedResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameDisplayService {
    @GET("games")
    Single<PaginatedResponse<Game>> search(@Query("search") String search, @Query("page_size") int pageSize);

    @GET("games/{gameId}")
    Single<Game> get(@Path("gameId") int gameId);
}
