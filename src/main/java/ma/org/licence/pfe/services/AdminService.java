package ma.org.licence.pfe.services;

import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.entities.Client;
import ma.org.licence.pfe.entities.User;

public interface AdminService {

    /*** User Methods ***/
    public User addUser(User user);
    public User getUser(String email);
    public User updateUser(String email);
    public User deleteUser(String email);

    /*** Barber Methods ***/
    public Barber addBarber(Barber barber);
    public Barber getBarber(String email);
    public Barber updateBarber(String email);
    public Barber deleteBarber(String email);

    /*** Client Methods ***/
    public Client addClient(Client client);
    public Client getClient(String email);
    public Client updateClient(String email);
    public Client deleteClient(String email);


    /*** Bookings Methods ***/
    public Book addBook(Book book);
    public Book getBook(String email);
    public Book updateBook(String email);
    public Book deleteBook(String bookId);


}
