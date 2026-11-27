
package library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Member {

    private final String memberNumber;
    private final String name;
    private final String email;

    private final List<Loan> loans = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    protected Member(String memberNumber, String name, String email) {
        this.memberNumber = memberNumber;
        this.name = name;
        this.email = email;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Loan> getLoans() {
        return Collections.unmodifiableList(loans);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public abstract int maxLoans();

    public boolean canBorrow() {
        long activeLoans = loans.stream()
                .filter(loan -> !loan.isClosed())
                .count();

        return activeLoans < maxLoans();
    }

    public Loan borrow(Copy copy, LocalDate startDate) {
        if (!canBorrow())
            throw new IllegalStateException("Maximum loans reached");

        if (!copy.isAvailable())
            throw new IllegalStateException("Copy is not available");

        return new Loan(this, copy, startDate);
    }

    public Reservation reserve(Book book, LocalDate date) {
        return new Reservation(this, book, date);
    }

    void addLoan(Loan loan) {
        loans.add(loan);
    }

    void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}
