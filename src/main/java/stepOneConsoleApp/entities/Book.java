package stepOneConsoleApp.entities;

import java.util.List;

public class Book {

    private String name;

    private List<Author> authors;

    private Publisher publisher;

    public Book(String name) { }

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

}
