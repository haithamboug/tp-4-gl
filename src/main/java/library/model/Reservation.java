
package library.model;

import java.time.LocalDate;

public class Reservation {

    private final LocalDate date;
    private final Member member;
    private final Book book;

    Reservation(Member member, Book book, LocalDate date) {
        this.member = member;
        this.book = book;
        this.date = date;

        member.addReservation(this);
        book.addReservation(this);
    }

    public LocalDate getDate() {
        return date;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }
}
