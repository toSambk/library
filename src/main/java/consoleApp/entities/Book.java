package consoleApp.entities;

import java.util.List;
import java.util.Objects;

public class Book {

    private String name;

    private List<Author> authors;

    private Publisher publisher;

    public Book(String name) {}

    public Book(String name, List<Author> authors, Publisher publisher) {
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
