
package library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {

    private final String isbn;
    private final String title;
    private final int year;

    private final List<Author> authors = new ArrayList<>();
    private final List<Copy> copies = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public Book(String isbn, String title, int year) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<Author> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public List<Copy> getCopies() {
        return Collections.unmodifiableList(copies);
    }

    public int availableCopies() {
        return (int) copies.stream()
                .filter(Copy::isAvailable)
                .count();
    }

    void addAuthor(Author author) {
        if (!authors.contains(author)) {
            authors.add(author);
        }
    }

    void addCopy(Copy copy) {
        if (!copies.contains(copy)) {
            copies.add(copy);
        }
    }

    void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Reservation firstReservation() {
        return reservations.isEmpty()
                ? null : reservations.get(0);
    }
}
