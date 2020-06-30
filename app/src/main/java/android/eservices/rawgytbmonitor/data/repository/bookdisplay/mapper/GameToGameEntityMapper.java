package android.eservices.rawgytbmonitor.data.repository.bookdisplay.mapper;

import android.eservices.rawgytbmonitor.data.api.model.Game;
import android.eservices.rawgytbmonitor.data.entity.GameEntity;
import android.text.TextUtils;

public class GameToGameEntityMapper {

    public GameEntity map(Game game) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.id = game.getId();
        gameEntity.imageUrl = game.getImage();
        gameEntity.rating = game.getRating();
        gameEntity.name = game.getName();
        return gameEntity;
    }
}
