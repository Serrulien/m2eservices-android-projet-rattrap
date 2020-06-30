package android.eservices.rawgytbmonitor.presentation.favorite.fragment;

import android.eservices.rawgytbmonitor.R;
import android.eservices.rawgytbmonitor.data.di.FakeDependencyInjection;
import android.eservices.rawgytbmonitor.presentation.favorite.BookFavoriteContract;
import android.eservices.rawgytbmonitor.presentation.favorite.BookFavoritePresenter;
import android.eservices.rawgytbmonitor.presentation.favorite.adapter.BookDetailActionInterface;
import android.eservices.rawgytbmonitor.presentation.favorite.adapter.BookDetailAdapter;
import android.eservices.rawgytbmonitor.presentation.favorite.adapter.BookDetailViewModel;
import android.eservices.rawgytbmonitor.presentation.favorite.mapper.BookEntityToDetailViewModelMapper;
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

public class FavoriteFragment extends Fragment implements BookFavoriteContract.View, BookDetailActionInterface {

    public static final String TAB_NAME = "Favorites";
    private View rootView;
    BookFavoriteContract.Presenter bookFavoritePresenter;
    private RecyclerView recyclerView;
    private BookDetailAdapter bookAdapter;

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

        bookFavoritePresenter = new BookFavoritePresenter(FakeDependencyInjection.getBookDisplayRepository(), new BookEntityToDetailViewModelMapper());
        bookFavoritePresenter.attachView(this);
        bookFavoritePresenter.getFavorites();
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        bookAdapter = new BookDetailAdapter(this);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayFavorites(List<BookDetailViewModel> bookDetailViewModelList) {
        bookAdapter.bindViewModels(bookDetailViewModelList);

    }

    @Override
    public void onRemoveFavorite(String bookId) {
        bookFavoritePresenter.removeBookFromFavorites(bookId);
        System.out.println("Remove book " + bookId);
    }

    @Override
    public void onBookRemoved() {
        //Do nothing yet
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bookFavoritePresenter.detachView();
    }
}
