package jdbcTask.entities;

public class BookInfo {

    public String getBookName() {
        return bookName;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    String bookName;

    String authors;

    String publisher;

    public BookInfo(String bookName, String authors, String publisher) {
        this.bookName = bookName;
        this.authors = authors;
        this.publisher = publisher;
    }


}
