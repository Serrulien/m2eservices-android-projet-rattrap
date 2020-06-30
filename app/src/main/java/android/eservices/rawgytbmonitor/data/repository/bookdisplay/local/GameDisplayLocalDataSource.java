package android.eservices.rawgytbmonitor.data.repository.bookdisplay.local;

import android.eservices.rawgytbmonitor.data.db.GameDatabase;
import android.eservices.rawgytbmonitor.data.entity.GameEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class GameDisplayLocalDataSource {

    private GameDatabase gameDatabase;

    public GameDisplayLocalDataSource(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    public Flowable<List<GameEntity>> loadFavorites() {
        return gameDatabase.bookDao().loadFavorites();
    }

    public Completable addToFavorites(GameEntity gameEntity) {
        return gameDatabase.bookDao().addToFavorites(gameEntity);
    }

    public Completable deleteFromFavorites(int id) {
        return gameDatabase.bookDao().deleteFromFavorites(id);
    }

    public Single<List<Integer>> getFavoriteIdList() {
        return gameDatabase.bookDao().getFavoriteIdList();
    }
}
