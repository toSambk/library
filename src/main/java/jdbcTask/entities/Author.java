package jdbcTask.entities;

import java.util.ArrayList;
import java.util.List;

public class Author {

    private int id;

    private String name;

    private List<Book> books = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Author() {
    }

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }





}
