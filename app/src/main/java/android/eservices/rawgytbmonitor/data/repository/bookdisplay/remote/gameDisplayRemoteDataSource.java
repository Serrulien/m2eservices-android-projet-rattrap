package android.eservices.rawgytbmonitor.data.repository.bookdisplay.remote;

import android.eservices.rawgytbmonitor.RawgytbmonitorApplication;
import android.eservices.rawgytbmonitor.data.api.GameDisplayService;
import android.eservices.rawgytbmonitor.data.api.model.Game;
import android.eservices.rawgytbmonitor.data.api.model.PaginatedResponse;

import io.reactivex.Single;

public class gameDisplayRemoteDataSource {

    private GameDisplayService gameDisplayService;

    public gameDisplayRemoteDataSource(GameDisplayService gameDisplayService) {
        this.gameDisplayService = gameDisplayService;
    }

    public Single<PaginatedResponse<Game>> getBookSearchResponse(String keywords) {
        return gameDisplayService.search(keywords, 20);
    }

    public Single<Game> get(int id) {
        return gameDisplayService.get(id);
    }
}
