package android.eservices.rawgytbmonitor.presentation.search;

import android.eservices.rawgytbmonitor.presentation.search.adapter.BookItemViewModel;

import java.util.List;

public interface BookSearchContract {

    interface View {
        void displayBooks(List<BookItemViewModel> bookItemViewModelList);

        void onBookAddedToFavorites();

        void onBookRemovedFromFavorites();
    }

    interface Presenter {
        void searchBooks(String keywords);

        void attachView(View view);

        void cancelSubscription();

        void addBookToFavorite(String bookId);

        void removeBookFromFavorites(String bookId);

        void detachView();
    }
}
