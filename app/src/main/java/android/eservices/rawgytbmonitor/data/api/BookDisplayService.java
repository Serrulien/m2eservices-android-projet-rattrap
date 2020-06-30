package android.eservices.rawgytbmonitor.data.api;

import android.eservices.rawgytbmonitor.data.api.model.Book;
import android.eservices.rawgytbmonitor.data.api.model.BookSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookDisplayService {
    @GET("volumes")
    Single<BookSearchResponse> searchBooks(@Query("q") String keywords, @Query("key") String apiKey);

    @GET("volumes/{bookId}")
    Single<Book> getBook(@Path("bookId") String bookId, @Query("key") String apiKey);
}
