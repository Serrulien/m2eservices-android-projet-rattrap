package android.eservices.rawgytbmonitor.data.repository.bookdisplay.mapper;

import android.eservices.rawgytbmonitor.data.api.model.Book;
import android.eservices.rawgytbmonitor.data.entity.BookEntity;
import android.text.TextUtils;

public class BookToBookEntityMapper {

    public BookEntity map(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(book.getVolumeInfo().getTitle());
        if (book.getVolumeInfo().getAuthorList() == null) {
            bookEntity.setAuthors("N.C.");
        } else {
            bookEntity.setAuthors(TextUtils.join(", ", book.getVolumeInfo().getAuthorList()));
        }
        bookEntity.setDescription(book.getVolumeInfo().getDescription());
        bookEntity.setId(book.getId());
        bookEntity.setLanguage(book.getVolumeInfo().getLanguage());
        bookEntity.setPublishedDate(book.getVolumeInfo().getPublishedDate());
        bookEntity.setThumbUrl(book.getVolumeInfo().getImageLinks().getThumbnail());
        return bookEntity;
    }
}
