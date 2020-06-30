package android.eservices.rawgytbmonitor.presentation.favorite;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameItemViewModel;

import java.util.List;

public interface GameFavoriteContract {

    interface View {
        void display (List<GameItemViewModel> gameItemViewModelList);

        void onRemovedFromFavorites();
    }

    interface Presenter {
        void loadFavorites();

        void attachView(GameFavoriteContract.View view);

        void removeFromFavorites(int gameId);

        void detachView();
    }
}
