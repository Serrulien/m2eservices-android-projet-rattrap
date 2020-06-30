package android.eservices.rawgytbmonitor.data.repository.bookdisplay.remote;

import android.eservices.rawgytbmonitor.RawgytbmonitorApplication;
import android.eservices.rawgytbmonitor.data.api.BookDisplayService;
import android.eservices.rawgytbmonitor.data.api.model.Book;
import android.eservices.rawgytbmonitor.data.api.model.BookSearchResponse;

import io.reactivex.Single;

public class BookDisplayRemoteDataSource {

    private BookDisplayService bookDisplayService;

    public BookDisplayRemoteDataSource(BookDisplayService bookDisplayService) {
        this.bookDisplayService = bookDisplayService;
    }

    public Single<BookSearchResponse> getBookSearchResponse(String keywords) {
        return bookDisplayService.searchBooks(keywords, RawgytbmonitorApplication.API_KEY);
    }

    public Single<Book> getBookDetails(String bookId) {
        return bookDisplayService.getBook(bookId, RawgytbmonitorApplication.API_KEY);
    }
}
