package android.eservices.rawgytbmonitor.presentation.search;

import android.eservices.rawgytbmonitor.data.api.model.BookSearchResponse;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.BookDisplayRepository;
import android.eservices.rawgytbmonitor.presentation.search.mapper.BookToViewModelMapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BookSearchPresenter implements BookSearchContract.Presenter {

    private BookDisplayRepository bookDisplayRepository;
    private BookSearchContract.View view;
    private CompositeDisposable compositeDisposable;
    private BookToViewModelMapper bookToViewModelMapper;

    public BookSearchPresenter(BookDisplayRepository bookDisplayRepository, BookToViewModelMapper bookToViewModelMapper) {
        this.bookDisplayRepository = bookDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.bookToViewModelMapper = bookToViewModelMapper;
    }

    @Override
    public void searchBooks(String keywords) {
        compositeDisposable.clear();
        compositeDisposable.add(bookDisplayRepository.getBookSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BookSearchResponse>() {

                    @Override
                    public void onSuccess(BookSearchResponse bookSearchResponse) {
                        // work with the resulting todos
                        view.displayBooks(bookToViewModelMapper.map(bookSearchResponse.getBookList()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));

    }

    @Override
    public void addBookToFavorite(String bookId) {
        compositeDisposable.add(bookDisplayRepository.addBookToFavorites(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onBookAddedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void removeBookFromFavorites(String bookId) {
        compositeDisposable.add(bookDisplayRepository.removeBookFromFavorites(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onBookRemovedFromFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void attachView(BookSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelSubscription() {
        compositeDisposable.clear();
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
