package android.eservices.rawgytbmonitor.presentation.search;

import android.eservices.rawgytbmonitor.data.api.model.Game;
import android.eservices.rawgytbmonitor.data.api.model.PaginatedResponse;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.GameDisplayRepository;
import android.eservices.rawgytbmonitor.presentation.search.mapper.GameToViewModelMapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GameSearchPresenter implements GameSearchContract.Presenter {

    private GameDisplayRepository gameDisplayRepository;
    private GameSearchContract.View view;
    private CompositeDisposable compositeDisposable;
    private GameToViewModelMapper gameToViewModelMapper;

    public GameSearchPresenter(GameDisplayRepository gameDisplayRepository, GameToViewModelMapper gameToViewModelMapper) {
        this.gameDisplayRepository = gameDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.gameToViewModelMapper = gameToViewModelMapper;
    }

    @Override
    public void search(String keywords) {
        compositeDisposable.clear();
        compositeDisposable.add(gameDisplayRepository.getSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PaginatedResponse<Game>>() {

                    @Override
                    public void onSuccess(PaginatedResponse<Game> searchResponse) {
                        // work with the resulting todos
                        System.out.print("KKKKKKKKKKKKKKK");
                        System.out.print(searchResponse.getResults().size());
                        view.display(gameToViewModelMapper.map(searchResponse.getResults()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case todos
                        System.out.println(e.toString());
                    }
                }));

    }

    @Override
    public void addToFavorite(int gameId) {
        compositeDisposable.add(gameDisplayRepository.addToFavorites(gameId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onAddedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void removeFromFavorites(int gameId) {
        compositeDisposable.add(gameDisplayRepository.removeFromFavorites(gameId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onRemovedFromFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void attachView(GameSearchContract.View view) {
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
