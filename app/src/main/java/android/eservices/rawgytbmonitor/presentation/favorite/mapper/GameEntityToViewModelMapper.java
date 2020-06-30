package android.eservices.rawgytbmonitor.presentation.favorite.mapper;
import android.eservices.rawgytbmonitor.data.entity.GameEntity;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameEntityToViewModelMapper {

    private GameItemViewModel map(GameEntity game) {
        GameItemViewModel gameItemViewModel = new GameItemViewModel();
        gameItemViewModel.setFavorite(true);
        gameItemViewModel.setGameId(game.id);
        gameItemViewModel.setImageUrl(game.imageUrl);
        gameItemViewModel.setName(game.name);
        gameItemViewModel.setRating(game.rating);
        return gameItemViewModel;
    }

    public List<GameItemViewModel> map(List<GameEntity> gameList) {
        List<GameItemViewModel> gameItemViewModelList = new ArrayList<>();
        for (GameEntity game : gameList) {
            gameItemViewModelList.add(map(game));
        }
        return gameItemViewModelList;
    }

}
