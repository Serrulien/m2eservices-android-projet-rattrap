package android.eservices.rawgytbmonitor.presentation.search.mapper;

import android.eservices.rawgytbmonitor.data.api.model.Game;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameItemViewModel;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class GameToViewModelMapper {

    private GameItemViewModel map(Game game) {
        GameItemViewModel gameItemViewModel = new GameItemViewModel();
        gameItemViewModel.setFavorite(game.isFavorite());
        gameItemViewModel.setGameId(game.getId());
        gameItemViewModel.setImageUrl(game.getImage());
        gameItemViewModel.setName(game.getName());
        gameItemViewModel.setRating(game.getRating());
        return gameItemViewModel;
    }

    public List<GameItemViewModel> map(List<Game> gameList) {
        List<GameItemViewModel> gameItemViewModelList = new ArrayList<>();
        for (Game game : gameList) {
            gameItemViewModelList.add(map(game));
        }
        return gameItemViewModelList;
    }
}
