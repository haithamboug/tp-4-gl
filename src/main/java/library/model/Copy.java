
package library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Copy {

    private final String inventoryNumber;
    private CopyStatus status;

    private final Book book;
    private final List<Loan> loans = new ArrayList<>();

    public Copy(String inventoryNumber, Book book) {
        this.inventoryNumber = inventoryNumber;
        this.book = book;
        this.status = CopyStatus.AVAILABLE;

        book.addCopy(this);
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public Book getBook() {
        return book;
    }

    public List<Loan> getLoans() {
        return Collections.unmodifiableList(loans);
    }

    public boolean isAvailable() {
        return status == CopyStatus.AVAILABLE;
    }

    public Loan getCurrentLoan() {
        return loans.stream()
                .filter(loan -> !loan.isClosed())
                .findFirst()
                .orElse(null);
    }

    void addLoan(Loan loan) {
        loans.add(loan);
        status = CopyStatus.BORROWED;
    }

    public void makeAvailable() {
        if (status != CopyStatus.LOST) {
            status = CopyStatus.AVAILABLE;
        }
    }

    public void markLost() {
        status = CopyStatus.LOST;
    }
}
