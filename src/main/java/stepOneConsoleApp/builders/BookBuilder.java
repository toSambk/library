package stepOneConsoleApp.builders;

import stepOneConsoleApp.entities.Author;
import stepOneConsoleApp.entities.Book;
import stepOneConsoleApp.entities.Publisher;

import java.util.List;

public class BookBuilder implements Builder {

    private String name;

    private List<Author> authors;

    private Publisher publisher;

    @Override
    public BookBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder setAuthors(final List<Author> authors){
        this.authors = authors;
        return this;
    }

    public BookBuilder setPublisher(final Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public Book getResult() {

        if(name == null || authors == null || publisher == null) throw new IllegalArgumentException();

        return new Book(name, authors, publisher);

    }




}
