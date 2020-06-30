package android.eservices.rawgytbmonitor.presentation.favorite;

import android.eservices.rawgytbmonitor.data.entity.BookEntity;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.BookDisplayRepository;
import android.eservices.rawgytbmonitor.presentation.favorite.mapper.BookEntityToDetailViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class BookFavoritePresenter implements BookFavoriteContract.Presenter {

    private BookDisplayRepository bookDisplayRepository;
    private BookFavoriteContract.View view;
    private CompositeDisposable compositeDisposable;
    private BookEntityToDetailViewModelMapper bookEntityToDetailViewModelMapper;

    public BookFavoritePresenter(BookDisplayRepository bookDisplayRepository, BookEntityToDetailViewModelMapper bookEntityToDetailViewModelMapper) {
        this.bookDisplayRepository = bookDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.bookEntityToDetailViewModelMapper = bookEntityToDetailViewModelMapper;
    }

    @Override
    public void attachView(BookFavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getFavorites() {
        compositeDisposable.add(bookDisplayRepository.getFavoriteBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<BookEntity>>() {

                    @Override
                    public void onNext(List<BookEntity> bookEntityList) {
                        view.displayFavorites(bookEntityToDetailViewModelMapper.map(bookEntityList));
                        System.out.println("BIND FAVORITES");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
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
                        view.onBookRemoved();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
