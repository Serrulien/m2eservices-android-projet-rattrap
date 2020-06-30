package android.eservices.rawgytbmonitor.presentation.search.mapper;

import android.eservices.rawgytbmonitor.data.api.model.Book;
import android.eservices.rawgytbmonitor.presentation.search.adapter.BookItemViewModel;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class BookToViewModelMapper {

    private BookItemViewModel map(Book book) {
        BookItemViewModel bookItemViewModel = new BookItemViewModel();
        bookItemViewModel.setBookTitle(book.getVolumeInfo().getTitle());
        bookItemViewModel.setBookId(book.getId());
        if (book.getVolumeInfo().getImageLinks() != null) {
            bookItemViewModel.setIconUrl(book.getVolumeInfo().getImageLinks().getThumbnail());
        }
        bookItemViewModel.setFavorite(book.isFavorite());
        if (book.getVolumeInfo().getAuthorList() == null) {
            bookItemViewModel.setBookAuthors("N.C.");
        } else {
            bookItemViewModel.setBookAuthors(TextUtils.join(", ", book.getVolumeInfo().getAuthorList()));
        }

        return bookItemViewModel;
    }

    public List<BookItemViewModel> map(List<Book> bookList) {
        List<BookItemViewModel> bookItemViewModelList = new ArrayList<>();
        for (Book book : bookList) {
            bookItemViewModelList.add(map(book));
        }
        return bookItemViewModelList;
    }
}
