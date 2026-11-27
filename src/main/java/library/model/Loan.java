
package library.model;

import java.time.LocalDate;

public class Loan {

    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    private final Member member;
    private final Copy copy;

    Loan(Member member, Copy copy, LocalDate loanDate) {
        this.member = member;
        this.copy = copy;
        this.loanDate = loanDate;
        this.dueDate = loanDate.plusDays(14);

        member.addLoan(this);
        copy.addLoan(this);
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Member getMember() {
        return member;
    }

    public Copy getCopy() {
        return copy;
    }

    public boolean isClosed() {
        return returnDate != null;
    }

    public boolean isLate(LocalDate today) {
        LocalDate effectiveDate =
                returnDate == null ? today : returnDate;

        return effectiveDate.isAfter(dueDate);
    }

    public void close(LocalDate date) {
        if (isClosed())
            throw new IllegalStateException("Loan already closed");

        returnDate = date;
        copy.makeAvailable();
    }
}
