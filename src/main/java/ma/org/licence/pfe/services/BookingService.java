package ma.org.licence.pfe.services;

import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.requests.BookRequest;

import java.util.List;

public interface BookingService {
    public Book clientBook(BookRequest request);
    public List<Book> getBookByUser(String username);
    public List<Book> getBookByBarber(String barberName);
}
