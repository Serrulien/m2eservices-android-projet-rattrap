package android.eservices.rawgytbmonitor.presentation.search;

import android.eservices.rawgytbmonitor.presentation.search.adapter.GameItemViewModel;

import java.util.List;

public interface GameSearchContract {

    interface View {
        void display (List<GameItemViewModel> gameItemViewModelList);

        void onAddedToFavorites();

        void onRemovedFromFavorites();
    }

    interface Presenter {
        void search(String keywords);

        void attachView(View view);

        void cancelSubscription();

        void addToFavorite(int bookId);

        void removeFromFavorites(int bookId);

        void detachView();
    }
}
