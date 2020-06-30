package android.eservices.rawgytbmonitor.presentation.favorite;

import android.eservices.rawgytbmonitor.data.entity.GameEntity;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.GameDisplayRepository;
import android.eservices.rawgytbmonitor.presentation.favorite.mapper.GameEntityToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class GameFavoritePresenter implements GameFavoriteContract.Presenter {

    private GameDisplayRepository gameDisplayRepository;
    private GameFavoriteContract.View view;
    private CompositeDisposable compositeDisposable;
    private GameEntityToViewModelMapper gameEntityToViewModelMapper;

    public GameFavoritePresenter(GameDisplayRepository gameDisplayRepository, GameEntityToViewModelMapper gameEntityToViewModelMapper) {
        this.gameDisplayRepository = gameDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.gameEntityToViewModelMapper = gameEntityToViewModelMapper;
    }

    @Override
    public void loadFavorites() {
        compositeDisposable.add(gameDisplayRepository.getFavorite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<GameEntity>>() {
                    @Override
                    public void onNext(List<GameEntity> gameEntityList) {
                        view.display(gameEntityToViewModelMapper.map(gameEntityList));
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
    public void attachView(GameFavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
