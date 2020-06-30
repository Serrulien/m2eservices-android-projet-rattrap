package android.eservices.rawgytbmonitor.presentation.favorite.fragment;

import android.eservices.rawgytbmonitor.R;
import android.eservices.rawgytbmonitor.data.di.FakeDependencyInjection;
import android.eservices.rawgytbmonitor.presentation.favorite.GameFavoriteContract;
import android.eservices.rawgytbmonitor.presentation.favorite.GameFavoritePresenter;
import android.eservices.rawgytbmonitor.presentation.favorite.mapper.GameEntityToViewModelMapper;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameActionInterface;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameAdapter;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameItemViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteFragment extends Fragment implements GameFavoriteContract.View, GameActionInterface {

    private View rootView;
    GameFavoriteContract.Presenter gameFavoritePresenter;
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;

    private FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();

        gameFavoritePresenter = new GameFavoritePresenter(FakeDependencyInjection.getGameDisplayRepository(), new GameEntityToViewModelMapper());
        gameFavoritePresenter.attachView(this);
        gameFavoritePresenter.loadFavorites();
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        gameAdapter = new GameAdapter(this);
        recyclerView.setAdapter(gameAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void display(List<GameItemViewModel> bookDetailViewModelList) {
        gameAdapter.bindViewModels(bookDetailViewModelList);

    }

    @Override
    public void onRemovedFromFavorites() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gameFavoritePresenter.detachView();
    }

    @Override
    public void onFavoriteToggle(int gameId, boolean isFavorite) {

    }
}
