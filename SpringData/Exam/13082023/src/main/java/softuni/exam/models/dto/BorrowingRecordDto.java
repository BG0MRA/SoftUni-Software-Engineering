package softuni.exam.models.dto;


import java.time.LocalDate;

public class BorrowingRecordDto {
    private BookBasicInfo book;
    private LocalDate borrowDate;

    private LibraryMemberBasicInfo member;

    public BorrowingRecordDto() {
    }

    public BookBasicInfo getBook() {
        return book;
    }

    public void setBook(BookBasicInfo book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LibraryMemberBasicInfo getMember() {
        return member;
    }

    public void setMember(LibraryMemberBasicInfo member) {
        this.member = member;
    }

    @Override
    public String toString() {
        String FORMAT =
                "Book title: %s\n" +
                        "*Book author: %s\n" +
                        "**Date borrowed: %s\n" +
                        "***Borrowed by: %s %s\n";

        return String.format(
                FORMAT,
                book.getTitle(),
                book.getAuthor(),
                borrowDate.toString(),
                member.getFirstName(),
                member.getLastName()
        );
    }
}
