package org.example;

import java.awt.print.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
enum BookingStatus {
    REQUESTED, PENDING, CONFIRMED, CHECKED_IN, CANCELED, ABANDONED
}

enum SeatType {
    REGULAR, PREMIUM, ACCESSIBLE, SHIPPED, EMERGENCY_EXIT, OTHER
}

enum AccountStatus {
    ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
}

enum PaymentStatus {
    UNPAID, PENDING, COMPLETED, FILLED, DECLINED, CANCELLED, ABANDONED, SETTLING, SETTLED, REFUNDED
}

class Address {

    public String city;
    public String streetAddress;
    public String state;
    public String zipCode;
    public String country;

    public Address(String city) {
        this.city = city;
    }
}

// For simplicity, we are not defining getter and setter functions. The reader can
// assume that all class attributes are public and accessed through their respective
// getter method and modified only through their setter method.

class Account {

    public String id;
    public String password;
    public AccountStatus status;

    boolean resetPassword() {
        System.out.println("Your password is reset.");
        return true;
    }
}

abstract class Person {

    public String name;
    public Address address;
    public String email;
    public String phone;

    public Account account;
}

class Customer extends Person {
    List<Booking> bookings = new ArrayList<>();
    BookMyShowSystem bms = BookMyShowSystem.getInstance();

    void makeBooking(Booking booking) {
        bookings.add(booking);
        bms.makeBooking(booking, UserRole.CUSTOMER);
    }

    List<Booking> getBookings() {
        return bookings;
    }

    public void showMovieTitles() {
        List<Movie> movies = bms.catalog.searchByCity(address.city);
        for (Movie movie : movies) {
            System.out.println(movie.title);
        }
    }

    public Movie selectMovie(String title) {
        List<Movie> movies = bms.catalog.searchByTitle(title);
        if (movies.isEmpty()) {
            System.out.println("No movie found.");
            return null;
        }
        return movies.get(0);
    }

    public Show selectShow(Movie bookMovie) {
        List<Show> shows = bms.movieShowMap.get(bookMovie);
        if (shows.isEmpty()) {
            System.out.println("No show found.");
            return null;
        }
        return shows.get(0);
    }

    // Select seats for booking with only the number of seats which are passed in parameter
    public List<ShowSeat> selectSeats(Show bookShow, Integer numberOfSeat) {
        List<ShowSeat> seats = new ArrayList<>();
        for (ShowSeat seat : bookShow.showSeats) {
            if (!seat.isReserved) {
                seats.add(seat);
                numberOfSeat--;
            }
            if (numberOfSeat == 0) {
                break;
            }
        }
        return seats;
    }
}

class BookMyShowSystem {

    Catalog catalog;

    List<Cinema> cinemas = new ArrayList<>();
    List<Show> shows = new ArrayList<>();
    Map<Movie, List<Show>> movieShowMap = new HashMap<>();

    public static BookMyShowSystem _instance = null;
    public static BookMyShowSystem getInstance() {
        if(_instance == null) {
            _instance = new BookMyShowSystem();
        }
        return _instance;
    }

    public void addMovie(Movie movie, UserRole role) {
        if (role != UserRole.ADMIN) {
            System.out.println("You are not authorized to add movie.");
            return;
        }
        catalog.addMovie(movie);
    }

    BookMyShowSystem() {
        catalog = Catalog.getInstance();
    }

    public void addShow(Show show, UserRole role) {
        if (role != UserRole.ADMIN) {
            System.out.println("You are not authorized to add show.");
            return;
        }
        shows.add(show);
        movieShowMap.computeIfAbsent(show.movie, k -> new ArrayList<>()).add(show);
    }

    public void makeBooking(Booking booking, UserRole role) {
        if (role == UserRole.GUEST) {
            System.out.println("You are not authorized to make booking.");
            return;
        }
        booking.assignSeats(booking.seats);
        booking.status = BookingStatus.CONFIRMED;
    }
}

class Admin extends Person {

    BookMyShowSystem bms = BookMyShowSystem.getInstance();

    void addCinema(Cinema cinema) {
        bms.cinemas.add(cinema);
    }

    void addMovie(Movie movie) {
        bms.addMovie(movie, UserRole.ADMIN);
    }

    void addShow(Show show) {
        bms.addShow(show, UserRole.ADMIN);
    }

    boolean blockUser(Customer customer) {
        customer.account.status = AccountStatus.BLOCKED;
        return true;
    }
}

class FrontDeskOfficer extends Person {
    BookMyShowSystem bms = BookMyShowSystem.getInstance();
    void createBooking(Booking booking) {
        bms.makeBooking(booking, UserRole.FRONT_DESK_OFFICER);
    }
}

enum UserRole {
    ADMIN, FRONT_DESK_OFFICER, CUSTOMER, GUEST
}

class Guest {

    private static final UserRole role = UserRole.GUEST;

    BookMyShowSystem bms = BookMyShowSystem.getInstance();

    void registerAccount() {

    }
}


class Show {

    public static int showId = 0;
    public Date createdOn;
    public Date startTime;
    public Date endTime;
    public CinemaHall playedAt;
    public Movie movie;

    public Show(Date createdOn, Date startTime, Date endTime, CinemaHall playedAt,
        Movie movie) {
        showId++;
        this.createdOn = createdOn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playedAt = playedAt;
        this.movie = movie;
    }

    List<ShowSeat> showSeats = new ArrayList<>();

    public void initializeSeats(CinemaHall cinemaHall1, double price) {
        List<CinemaHallSeat> seats = cinemaHall1.seats;
        for (CinemaHallSeat seat : seats) {
            showSeats.add(new ShowSeat(seat.seatType, showId, false, price));
        }
        playedAt.shows.add(this);
    }
}

class Movie {

    public String title;
    public String description;
    public int movieDuration;
    public String language;
    public Date releaseDate;
    public String city;
    public String genre;
    public Admin movieAddedBy;


    public List<Show> shows;

    List<Show> getShows() {
        return shows;
    }

    public Movie(String title, String description, int movieDuration, String language,
        Date releaseDate, String city, String genre, Admin movieAddedBy) {
        this.title = title;
        this.description = description;
        this.movieDuration = movieDuration;
        this.language = language;
        this.releaseDate = releaseDate;
        this.city = city;
        this.genre = genre;
        this.movieAddedBy = movieAddedBy;
    }
}

class Booking {

    public String bookingNumber;
    public int numberOfSeats;
    public Date createdOn;
    public BookingStatus status;

    public Show show;
    public List<ShowSeat> seats;
    public Payment payment;

    public Booking(int numberOfSeats, Show show, List<ShowSeat> seats, Payment payment) {
        this.numberOfSeats = numberOfSeats;
        this.show = show;
        this.seats = seats;
        this.payment = payment;
        this.createdOn = new Date();
        this.bookingNumber = UUID.randomUUID().toString();
    }

    public boolean makePayment(Payment payment) {
        this.payment = payment;
        return true;
    }

    public boolean cancel() {
        for (ShowSeat seat : seats) {
            seat.isReserved = false;
        }
        payment.status = PaymentStatus.CANCELLED;
        return true;
    }

    void assignSeats(List<ShowSeat> seats) {
        if (payment.status != PaymentStatus.COMPLETED) {
            System.out.println("Payment is not completed.");
            return;
        }
        for (ShowSeat seat : seats) {
            seat.isReserved = true;
        }
    }
}

class CinemaHallSeat {

    SeatType seatType;

    CinemaHallSeat(SeatType seatType) {
        this.seatType = seatType;
    }
}

class ShowSeat extends CinemaHallSeat {

    public int showSeatId;
    public boolean isReserved;
    public double price;

    ShowSeat(SeatType seatType, int showSeatId, boolean isReserved, double price) {
        super(seatType);
        this.showSeatId = showSeatId;
        this.isReserved = isReserved;
        this.price = price;
    }
}

class Payment {

    public double amount;
    public Date createdOn;
    public int transactionId;

    public Payment(double amount, Date createdOn, int transactionId, PaymentStatus status) {
        this.amount = amount;
        this.createdOn = createdOn;
        this.transactionId = transactionId;
        this.status = status;
    }

    public PaymentStatus status;
}


class City {

    public String name;
    public String state;
    public String zipCode;

    public City(String name, String state, String zipCode) {
        this.name = name;
        this.state = state;
        this.zipCode = zipCode;
    }
}

class Cinema {

    public String name;
    public Address location;


    public int totalCinemaHalls;

    public Cinema(String name, Address location, int totalCinemaHalls) {
        this.name = name;
        this.location = location;
        this.totalCinemaHalls = totalCinemaHalls;
    }

    public List<CinemaHall> halls;

}

class CinemaHall {

    public String name;
    public int totalSeats;

    public CinemaHall(String name, int totalSeats) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.seats = new ArrayList<>();
        this.shows = new ArrayList<>();
    }

    public List<CinemaHallSeat> seats;
    public List<Show> shows;

    public void addSeat(CinemaHallSeat cinemaHallSeat) {
        seats.add(cinemaHallSeat);
    }
}

interface Search {

    List<Movie> searchByTitle(String title);

    List<Movie> searchByLanguage(String language);

    List<Movie> searchByGenre(String genre);

    List<Movie> searchByReleaseDate(Date relDate);

    List<Movie> searchByCity(String cityName);
}

class Catalog implements Search {

    Map<String, List<Movie>> movieTitles;
    Map<String, List<Movie>> movieLanguages;
    Map<String, List<Movie>> movieGenres;
    Map<Date, List<Movie>> movieReleaseDates;
    Map<String, List<Movie>> movieCities;

    Catalog() {
        movieTitles = new HashMap<>();
        movieLanguages = new HashMap<>();
        movieGenres = new HashMap<>();
        movieReleaseDates = new HashMap<>();
        movieCities = new HashMap<>();
    }

    public static Catalog _instance = null;

    public static Catalog getInstance() {
        if(_instance == null) {
            _instance = new Catalog();
        }
        return _instance;
    }

    public List<Movie> searchByTitle(String title) {
        return movieTitles.get(title);
    }

    public List<Movie> searchByLanguage(String language) {
        return movieLanguages.get(language);
    }

    @Override
    public List<Movie> searchByGenre(String genre) {
        return movieGenres.get(genre);
    }

    @Override
    public List<Movie> searchByReleaseDate(Date relDate) {
        return movieReleaseDates.get(relDate);
    }

    public List<Movie> searchByCity(String cityName) {
        return movieCities.get(cityName);
    }

    public void addMovie(Movie movie) {
        movieTitles.computeIfAbsent(movie.title, k -> new ArrayList<>()).add(movie);
        movieLanguages.computeIfAbsent(movie.language, k -> new ArrayList<>()).add(movie);
        movieGenres.computeIfAbsent(movie.genre, k -> new ArrayList<>()).add(movie);
        movieReleaseDates.computeIfAbsent(movie.releaseDate, k -> new ArrayList<>()).add(movie);
        movieCities.computeIfAbsent(movie.city, k -> new ArrayList<>()).add(movie);
    }
}


public class Main {

    public static void main(String[] args) {
        CinemaHallSeat cinemaHallSeat1 = new CinemaHallSeat(SeatType.PREMIUM);
        CinemaHallSeat cinemaHallSeat2 = new CinemaHallSeat(SeatType.PREMIUM);
        CinemaHallSeat cinemaHallSeat3 = new CinemaHallSeat(SeatType.PREMIUM);
        CinemaHallSeat cinemaHallSeat4 = new CinemaHallSeat(SeatType.PREMIUM);
        CinemaHallSeat cinemaHallSeat5 = new CinemaHallSeat(SeatType.PREMIUM);
        CinemaHallSeat cinemaHallSeat6 = new CinemaHallSeat(SeatType.PREMIUM);
        CinemaHallSeat cinemaHallSeat7 = new CinemaHallSeat(SeatType.PREMIUM);
        CinemaHallSeat cinemaHallSeat8 = new CinemaHallSeat(SeatType.PREMIUM);
        CinemaHall cinemaHall1 = new CinemaHall("Screen-1", 2);
        cinemaHall1.addSeat(cinemaHallSeat1);
        cinemaHall1.addSeat(cinemaHallSeat2);
        CinemaHall cinemaHall2 = new CinemaHall("Screen-2", 2);
        cinemaHall2.addSeat(cinemaHallSeat3);
        cinemaHall2.addSeat(cinemaHallSeat4);
        CinemaHall cinemaHall3 = new CinemaHall("Screen-1", 2);
        cinemaHall3.addSeat(cinemaHallSeat5);
        cinemaHall3.addSeat(cinemaHallSeat6);
        CinemaHall cinemaHall4 = new CinemaHall("Screen-2", 2);
        cinemaHall4.addSeat(cinemaHallSeat7);
        cinemaHall4.addSeat(cinemaHallSeat8);
        // Create and add cinemas to the system
        Address address = new Address("Noida");
        Cinema cinema1 = new Cinema("PVR", address, 2);
        Cinema cinema2 = new Cinema("INOX", address, 2);

        // Create an admin to manage the system
        Admin admin1 = new Admin();

        // Add cinemas to the system
        admin1.addCinema(cinema1);
        admin1.addCinema(cinema2);

        // Add movies to the catalog
        Movie movie1 = new Movie("Interstellar", "A captivating sci-fi adventure.", 169, "English",
            new Date(), "Noida", "Sci-Fi", admin1);

        Movie movie2 = new Movie("Inception", "A mind-bending heist thriller.", 148, "English",
            new Date(), "Noida", "Sci-Fi", admin1);

        // Add movies to the catalog
        admin1.addMovie(movie1);
        admin1.addMovie(movie2);



        // Create SimpleDateFormat for showtime
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Show show1 = new Show(new Date(), dateFormat.parse("2023-11-05 15:00"),
                dateFormat.parse("2023-11-05 17:30"), cinemaHall1, movie1);
            show1.initializeSeats(cinemaHall1, 10);
            Show show2 = new Show(new Date(), dateFormat.parse("2023-11-05 18:00"),
                dateFormat.parse("2023-11-05 20:30"), cinemaHall1, movie2);
            Show show3 = new Show(new Date(), dateFormat.parse("2023-11-06 14:30"),
                dateFormat.parse("2023-11-06 17:00"), cinemaHall2, movie1);

            // Add shows to the catalog
            admin1.addShow(show1);
            admin1.addShow(show2);
            admin1.addShow(show3);

            Customer customer1 = new Customer();
            customer1.address = address;
            customer1.showMovieTitles();

            Movie bookMovie = customer1.selectMovie("Interstellar");

            Show bookShow = customer1.selectShow(bookMovie);
            List<ShowSeat> bookingSeats = customer1.selectSeats(bookShow, 1);

            Payment payment = new Payment(500, new Date(), 1, PaymentStatus.COMPLETED);
            Booking booking1 = new Booking(1, bookShow, bookingSeats, payment);
            customer1.makeBooking(booking1);

            // Get the list of bookings for the customer
            List<Booking> customerBookings = customer1.getBookings();

            // Output the customer's bookings
            System.out.println("Customer Bookings:");
            for (Booking booking : customerBookings) {
                System.out.println("Booking Number: " + booking.bookingNumber);
                System.out.println("Status: " + booking.status);
                System.out.println("Show: " + booking.show.movie.title);
                System.out.println("Number of Seats: " + booking.numberOfSeats);
                System.out.println("Showtime: " + booking.show.startTime.toString());
                System.out.println();
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format." + e.getMessage());
        }
    }
}