package ma.org.licence.pfe.services;

import ma.org.licence.pfe.dto.user.UserDto;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.entities.Client;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.requests.BarberRegisterRequest;
import ma.org.licence.pfe.requests.BookRequest;

import java.util.List;

public interface AdminService {

    /*** User Methods ***/
    public List<User> getAllUsers();
    public User addUser(User user);
    public UserDto getUser(String email);
    public User updateUser(String email, User newUser);
    public void deleteUser(String email);

    /*** Barber Methods ***/
    public Barber addBarber(BarberRegisterRequest request);
    public Barber getBarber(String barberName);
    public Barber updateBarber(String barberName, Barber newBarber);

    /*** Client Methods ***/
    public Client addClient(Client client);
    public Client getClient(String email);
    public Client updateClient(String email);


    /*** Bookings Methods ***/
    public Book addBook(BookRequest book, String email);
    public Book getBook(String email);
    public Book updateBook(String id, Book newBooking);
    public void deleteBook(String bookId);


}
