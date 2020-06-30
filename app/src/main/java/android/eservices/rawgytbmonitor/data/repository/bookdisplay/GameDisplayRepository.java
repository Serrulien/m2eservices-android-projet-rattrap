package android.eservices.rawgytbmonitor.data.repository.bookdisplay;

import android.eservices.rawgytbmonitor.data.api.model.Game;
import android.eservices.rawgytbmonitor.data.api.model.PaginatedResponse;
import android.eservices.rawgytbmonitor.data.entity.GameEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface GameDisplayRepository {

    Single<PaginatedResponse<Game>> getSearchResponse(String keywords);

    Flowable<List<GameEntity>> getFavorite();

    Completable addToFavorites(int id);

    Completable removeFromFavorites(int id);
}
