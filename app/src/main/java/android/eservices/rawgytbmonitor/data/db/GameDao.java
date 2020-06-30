package android.eservices.rawgytbmonitor.data.db;

import android.eservices.rawgytbmonitor.data.entity.GameEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface GameDao {

    @Query("SELECT * from GameEntity")
    Flowable<List<GameEntity>> loadFavorites();

    @Insert
    public Completable addToFavorites(GameEntity gameEntity);

    @Query("DELETE FROM GameEntity WHERE id = :id")
    public Completable deleteFromFavorites(int id);

    @Query("SELECT id from GameEntity")
    Single<List<Integer>> getFavoriteIdList();
}
