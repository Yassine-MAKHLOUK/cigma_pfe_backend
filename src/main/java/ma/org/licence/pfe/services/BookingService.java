package ma.org.licence.pfe.services;

import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.entities.Client;
import ma.org.licence.pfe.security.BookRequest;

public interface BookingService {
    public Book clientBook(BookRequest request);
}
