package android.eservices.rawgytbmonitor.data.repository.bookdisplay;

import android.eservices.rawgytbmonitor.data.api.model.Game;
import android.eservices.rawgytbmonitor.data.api.model.PaginatedResponse;
import android.eservices.rawgytbmonitor.data.entity.GameEntity;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.local.GameDisplayLocalDataSource;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.mapper.GameToGameEntityMapper;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.remote.gameDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class GameDisplayDataRepository implements GameDisplayRepository {

    private GameDisplayLocalDataSource gameDisplayLocalDataSource;
    private gameDisplayRemoteDataSource gameDisplayRemoteDataSource;
    private GameToGameEntityMapper EntityMapper;

    public GameDisplayDataRepository(GameDisplayLocalDataSource gameDisplayLocalDataSource,
                                     gameDisplayRemoteDataSource gameDisplayRemoteDataSource,
                                     GameToGameEntityMapper EntityMapper) {
        this.gameDisplayLocalDataSource = gameDisplayLocalDataSource;
        this.gameDisplayRemoteDataSource = gameDisplayRemoteDataSource;
        this.EntityMapper = EntityMapper;
    }

    @Override
    public Single<PaginatedResponse<Game>> getSearchResponse(String keywords) {
        return gameDisplayRemoteDataSource.getBookSearchResponse(keywords)
                .zipWith(gameDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<PaginatedResponse<Game>, List<Integer>, PaginatedResponse<Game>>() {
                    @Override
                    public PaginatedResponse<Game> apply(PaginatedResponse<Game> searchResponse, List<Integer> idList) throws Exception {
                        for (Game game : searchResponse.getResults()) {
                            if (idList.contains(game.getId())) {
                                game.setFavorite();
                            }
                        }
                        return searchResponse;
                    }
                });
    }

    @Override
    public Flowable<List<GameEntity>> getFavorite() {
        return gameDisplayLocalDataSource.loadFavorites();
    }

    @Override
    public Completable addToFavorites(int id) {
        return gameDisplayRemoteDataSource.get(id)
                .map(new Function<Game, GameEntity>() {
                    @Override
                    public GameEntity apply(Game game) throws Exception {
                        return EntityMapper.map(game);
                    }
                })
                .flatMapCompletable(new Function<GameEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(GameEntity gameEntity) throws Exception {
                        return gameDisplayLocalDataSource.addToFavorites(gameEntity);
                    }
                });
    }

    @Override
    public Completable removeFromFavorites(int id) {
        return gameDisplayLocalDataSource.deleteFromFavorites(id);
    }
}
