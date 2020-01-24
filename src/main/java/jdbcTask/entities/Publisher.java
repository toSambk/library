package jdbcTask.entities;

public class Publisher {

    private int id;

    private Book book;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Publisher() {
    }

    public Publisher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Publisher(int id, String name, Book book) {
        this.id = id;
        this.name = name;
        this.book = book;
    }

    private String name;

    public Book getBook() {
        return book;
    }
}
