package android.eservices.rawgytbmonitor.presentation.search.fragment;

import android.eservices.rawgytbmonitor.R;
import android.eservices.rawgytbmonitor.data.di.FakeDependencyInjection;
import android.eservices.rawgytbmonitor.presentation.search.GameSearchContract;
import android.eservices.rawgytbmonitor.presentation.search.GameSearchPresenter;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameActionInterface;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameAdapter;
import android.eservices.rawgytbmonitor.presentation.search.adapter.GameItemViewModel;
import android.eservices.rawgytbmonitor.presentation.search.mapper.GameToViewModelMapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/*
 * TODO : uncheck favorite selection in search results when favorite unchecked from Favorite fragment
 */
public class SearchFragment extends Fragment implements GameSearchContract.View, GameActionInterface {

    private View rootView;
    GameSearchContract.Presenter searchPresenter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private ProgressBar progressBar;

    private SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        setupRecyclerView();
        progressBar = rootView.findViewById(R.id.progress_bar);

        searchPresenter = new GameSearchPresenter(FakeDependencyInjection.getGameDisplayRepository(), new GameToViewModelMapper());
        searchPresenter.attachView(this);
    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    searchPresenter.cancelSubscription();
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    timer.cancel();
                    timer = new Timer();
                    // todos pour debug
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            searchPresenter.search(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        gameAdapter = new GameAdapter(this);
        recyclerView.setAdapter(gameAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void display(List<GameItemViewModel> gameItemViewModelList) {
        progressBar.setVisibility(View.GONE);
        gameAdapter.bindViewModels(gameItemViewModelList);
    }

    @Override
    public void onFavoriteToggle(int gameId, boolean isFavorite) {
        if (isFavorite) {
            searchPresenter.addToFavorite(gameId);
        } else {
            searchPresenter.removeFromFavorites(gameId);
        }
    }

    @Override
    public void onAddedToFavorites() {
        //Do nothing
    }

    @Override
    public void onRemovedFromFavorites() {
        //Do nothing
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchPresenter.detachView();
    }
}
