package android.eservices.rawgytbmonitor.data.repository.bookdisplay;

import android.eservices.rawgytbmonitor.data.api.model.BookSearchResponse;
import android.eservices.rawgytbmonitor.data.entity.BookEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface BookDisplayRepository {

    Single<BookSearchResponse> getBookSearchResponse(String keywords);

    Flowable<List<BookEntity>> getFavoriteBooks();

    Completable addBookToFavorites(String bookId);

    Completable removeBookFromFavorites(String bookId);
}
