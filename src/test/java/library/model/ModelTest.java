
package library.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void testStudentMember() {
        StudentMember student = new StudentMember(
                "M01", "Haitham", "haitham@gmail.com", "L3"
        );

        assertEquals(5, student.maxLoans());
        assertTrue(student.canBorrow());
    }

    @Test
    void testTeacherMember() {
        TeacherMember teacher = new TeacherMember(
                "M02", "Sara", "sara@gmail.com", "Computer Science"
        );

        assertEquals(10, teacher.maxLoans());
        assertTrue(teacher.canBorrow());
    }

    @Test
    void testBookAndCopy() {
        Book book = new Book("12345", "Java", 2026);
        Copy copy = new Copy("C01", book);

        assertTrue(copy.isAvailable());
        assertEquals(1, book.availableCopies());
    }

    @Test
    void testBorrowAndReturn() {
        Book book = new Book("12345", "Java", 2026);
        Copy copy = new Copy("C01", book);

        StudentMember student = new StudentMember(
                "M01", "Haitham", "haitham@gmail.com", "L3"
        );

        Loan loan = student.borrow(copy, LocalDate.of(2026, 10, 1));

        assertFalse(copy.isAvailable());
        assertEquals(LocalDate.of(2026, 10, 15), loan.getDueDate());

        loan.close(LocalDate.of(2026, 10, 10));

        assertTrue(copy.isAvailable());
        assertTrue(loan.isClosed());
    }

    @Test
    void testReservation() {
        Book book = new Book("12345", "Java", 2026);

        TeacherMember teacher = new TeacherMember(
                "M02", "Sara", "sara@gmail.com", "Computer Science"
        );

        Reservation reservation = teacher.reserve(
                book, LocalDate.of(2026, 10, 2)
        );

        assertEquals(book, reservation.getBook());
        assertEquals(teacher, reservation.getMember());
        assertEquals(reservation, book.firstReservation());
    }
}
